package model.libreria;

import model.strategy.*;
import view.Observer;

import java.util.List;

//pattern Bridge
public class Libreria {
    private LibreriaImpl lib;
    private List<Libro> libriVisualizzati;
    private Observer observer;
    private StrategiaOrdinamento strategy;


    public Libreria(){
        this.lib= new LibreriaSqLite();
    }//Libreria

    public List<Libro> getLibriDaVisualizzare(){
        return libriVisualizzati;
    }//getLibriDaVisualizzare

    public void prendiDati() throws Exception{
        this.lib.caricaDati();
    }//getTuttiLibri

    public void getLibri(){
        libriVisualizzati=lib.getAllLibri();
        observer.notifica();
    }//getLibri

    public void getLibro(String ISBN){
        libriVisualizzati=this.lib.cercaLibro_ISBN(ISBN);
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
    }//getLibro

    public void getLibroByStato(StatoLettura stato){
         libriVisualizzati=this.lib.cercaLibro_statoLettura(stato);
         notify();
    }//getLibroByStato

    public void aggiungi(Libro l){
        this.lib.inserisciLibro(l);
        libriVisualizzati.add(l);
        notify();
    }//aggiungi

    public void elimina(Libro l){
        this.lib.eliminaLibro(l);
        libriVisualizzati.remove(l);
        observer.notifica();
    }//elimina

    public void inserisciValutazione(Libro l, Integer v){
        this.lib.modificaValutazione(l,v);
        observer.notifica();
    }//inserisciValutazione

    public void inserisciStato(Libro l, StatoLettura s){
        this.lib.modificaStato(l,s);
        observer.notifica();
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

}//Libreria
