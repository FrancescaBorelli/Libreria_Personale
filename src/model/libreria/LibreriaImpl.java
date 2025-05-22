package model.libreria;

import java.util.List;

public interface LibreriaImpl {

    void caricaDati();
    List<Libro> getAllLibri();
    List<Libro> cercaLibro_ISBN(String cod);
    List<Libro> cercaLibro_autore(String autore);
    List<Libro> cercaLibro_titolo(String titolo);
    List<Libro> cercaLibro_genere(String genere);
    List<Libro> cercaLibro_statoLettura(StatoLettura stato);
    boolean inserisciLibro(Libro l);
    void eliminaLibro(Libro l);
    void modificaValutazione(Libro l, Integer val);
    void modificaStato(Libro l, StatoLettura stato);




}//Libreria
