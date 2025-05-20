package model;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LibreriaImpl implements Libreria{
    private static List<Libro>libri;
    private static GestioneDatabase db;


    public LibreriaImpl(){
        libri= new LinkedList<>();
        db=GestioneDatabase.getInstance();

    }//LibreriaImpl


    @Override
    public void caricaDati() throws SQLException {
        String sql = "SELECT id, titolo, autore, isbn, genere, valutazione, stato_lettura FROM libri";

        try (PreparedStatement statement = db.getConnessione().prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            // Itera attraverso ogni riga del ResultSet
            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getString("titolo"),
                        rs.getString("autore"),
                        rs.getString("isbn"),
                        rs.getString("genere"),
                        (StatoLettura) rs.getObject("stato_lettura"),
                        rs.getInt("valutazione")
                );
                libri.add(libro);
            }
        }

    }//caricaDati


    @Override
    public Libro cercaLibro_ISBN(String cod) {
        return null;
    }

    @Override
    public List<Libro> cercaLibro_autore(String autore) {
        return List.of();
    }

    @Override
    public List<Libro> cercaLibro_titolo(String titolo) {
        return List.of();
    }

    @Override
    public List<Libro> cercaLibro_genere(String genere) {
        return List.of();
    }

    @Override
    public List<Libro> cercaLibro_statoLettura(StatoLettura stato) {
        return List.of();
    }
}
