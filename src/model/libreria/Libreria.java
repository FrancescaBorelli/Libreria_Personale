package model.libreria;

import model.strategy.*;
import view.LibreriaGrafica;
import view.Observer;

import java.util.LinkedList;
import java.util.List;

//pattern Bridge
public class Libreria {
    private LibreriaImpl lib;
    private List<Libro> libriVisualizzati;
    private Observer observer;
    private StrategiaOrdinamento strategy;


    public Libreria(){
        this.lib= new LibreriaSqLite();
        lib.caricaDati();
        libriVisualizzati=lib.getAllLibri();
    }//Libreria

    public List<Libro> getLibriDaVisualizzare(){
        return libriVisualizzati;
    }//getLibriDaVisualizzare


    public void getLibri(){
        libriVisualizzati=lib.getAllLibri();
        observer.notifica();
    }//getLibri

    public void getLibro(String ISBN){
        libriVisualizzati=this.lib.cercaLibro_ISBN(ISBN);
        observer.notifica();
    }//getLibro

    public void getLibroByAutore(String autore){
        libriVisualizzati=this.lib.cercaLibro_autore(autore);
        observer.notifica();
    }//getLibroByAutore

    public void getLibroByTitolo(String titolo){
        libriVisualizzati=this.lib.cercaLibro_titolo(titolo);
        observer.notifica();
    }//getLibroByTitolo

    public void getLibroByGenere(String genere){
        libriVisualizzati=this.lib.cercaLibro_genere(genere);
        observer.notifica();
    }//getLibro

    public void getLibroByStato(StatoLettura stato){
         libriVisualizzati=this.lib.cercaLibro_statoLettura(stato);
         observer.notifica();
    }//getLibroByStato

    public boolean aggiungi(Libro l){
        boolean res= this.lib.inserisciLibro(l);
       if(res) {
           libriVisualizzati.add(l);
           observer.notifica();
       }
       return res;
    }//aggiungi

    public boolean elimina(Libro l){
        boolean res= this.lib.eliminaLibro(l);
        if(res) {
            libriVisualizzati.remove(l);
            observer.notifica();
        }
        return res;
    }//elimina

    public boolean inserisciValutazione(Libro l, Integer v){
       boolean res= this.lib.modificaValutazione(l,v);
        if(res)
            observer.notifica();
        return res;
    }//inserisciValutazione

    public boolean inserisciStato(Libro l, StatoLettura s){
        boolean res= this.lib.modificaStato(l,s);
        if(res)
            observer.notifica();
        return res;
    }//modificaStato


    public void cambiaOrdinamento(String s){
        strategy=setStrategy(s);
        strategy.ordina(libriVisualizzati);
        observer.notifica();
    }//cambiaOrdinamento

    private StrategiaOrdinamento setStrategy(String s) {
        StrategiaOrdinamento res = null;
        switch (s) {
            case ("Titolo"): {
                res = new OrdinaByTitolo();
                break;
            }
            case ("Autore"): {
                res = new OrdinaByAutore();
                break;
            }
            case ("Valutazione"): {
                res = new OrdinaByValutazione();
                break;
            }
            default: {
                res = new OrdinamentoDefault();
                break;
            }
        }
        return res;
    }//setSteategy

    public void attach(LibreriaGrafica grafica) {
        observer=grafica;
    }
}//Libreria
