package model.libreria;

import java.util.List;

//pattern Bridge
public class Libreria {
    private LibreriaImpl lib;

    public Libreria(){
        this.lib= new LibreriaSqLite();
    }//Libreria

    public void prendiDati() throws Exception{
        this.lib.caricaDati();
    }//getTuttiLibri

    public List<Libro> getLibri(){
        return lib.getAllLibri();
    }//getLibri

    public Libro getLibro(String ISBN){
        return this.lib.cercaLibro_ISBN(ISBN);
    }//getLibro

    public List<Libro> getLibroByAutore(String autore){
        return this.lib.cercaLibro_autore(autore);
    }//getLibroByAutore

    public List<Libro> getLibroByTitolo(String titolo){
        return this.lib.cercaLibro_titolo(titolo);
    }//getLibroByTitolo

    public List<Libro> getLibroByGenere(String genere){
        return this.lib.cercaLibro_genere(genere);
    }//getLibro

    public List<Libro> getLibroByStato(StatoLettura stato){
        return this.lib.cercaLibro_statoLettura(stato);
    }//getLibroByStato

    public void aggiungi(Libro l){
        this.lib.inserisciLibro(l);
    }//aggiungi

    public void elimina(Libro l){
        this.lib.eliminaLibro(l);
    }//elimina

    public void inserisciValutazione(Libro l, Integer v){
        this.lib.modificaValutazione(l,v);
    }//inserisciValutazione

    public void inserisciStato(Libro l, StatoLettura s){
        this.lib.modificaStato(l,s);
    }//modificaStato

}//Libreria
