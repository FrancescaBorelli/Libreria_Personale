package model;

import java.sql.SQLException;
import java.util.List;

public interface Libreria {

    void caricaDati() throws SQLException;
    Libro cercaLibro_ISBN(String cod);
    List<Libro> cercaLibro_autore(String autore);
    List<Libro> cercaLibro_titolo(String titolo);
    List<Libro> cercaLibro_genere(String genere);
    List<Libro> cercaLibro_statoLettura(StatoLettura stato);



}//Libreria
