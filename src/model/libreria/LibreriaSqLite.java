package model.libreria;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LibreriaSqLite implements LibreriaImpl {
    private static List<Libro> libri;
    private static GestioneDatabase db;


    public LibreriaSqLite() {
        libri = new LinkedList<>();
        db = GestioneDatabase.getInstance();

    }//LibreriaImpl


    public List<Libro> getLibri() {
        return List.copyOf(libri);
    }//getLibri

    @Override
    public void caricaDati() {
        String query = "SELECT * FROM libri";

        try {
            PreparedStatement statement = db.getConnessione().prepareStatement(query);
            ResultSet rs = statement.executeQuery();

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
        } catch (SQLException se) {

        }

    }//caricaDati

    @Override
    public List<Libro> getAllLibri() {
        return this.libri;
    }


    @Override
    public List<Libro> cercaLibro_ISBN(String cod) {
       List<Libro> res= new LinkedList<>();
        for (Libro libro : libri) {
            if (libro.getISBN().equals(cod)) res.add(libro);
        }
        return res;
    }//cercaLibro_ISBN

    @Override
    public List<Libro> cercaLibro_autore(String autore) {
        LinkedList<Libro> res = new LinkedList<>();
        for (Libro libro : libri)
            if (libro.getAutore().equals(autore)) res.add(libro);
        return res;
    }

    @Override
    public List<Libro> cercaLibro_titolo(String titolo) {
        LinkedList<Libro> res = new LinkedList<>();
        for (Libro libro : libri)
            if (libro.getTitolo().equals(titolo)) res.add(libro);
        return res;
    }


    @Override
    public List<Libro> cercaLibro_genere(String genere) {
        return List.of();
    }

    @Override
    public List<Libro> cercaLibro_statoLettura(StatoLettura stato) {
        return List.of();
    }

    @Override
    public boolean inserisciLibro(Libro l) {
        String query = "INSERT INTO libri(isbn,titolo,autore,genere,valutazione,stato_lettura) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement statement = db.getConnessione().prepareStatement(query);
            statement.setString(1, l.getISBN());
            statement.setString(2, l.getTitolo());
            statement.setString(3, l.getAutore());
            statement.setString(4, l.getGenere());
            statement.setInt(5, l.getValutazione());
            statement.setObject(6, l.getStatoLettura());
            statement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Non è possibile inserire il libro! Controlla i campi nuovamente");
            return false;
        }
        libri.add(l);
        return true;
    }//inserisciLibri

    @Override
    public void eliminaLibro(Libro l) {
        String query = "DELETE FROM libri WHERE isbn=?";
        try {
            PreparedStatement statement = db.getConnessione().prepareStatement(query);
            statement.setString(1, l.getISBN());
        }catch(SQLException se){

        }
        libri.remove(l);

    }

    @Override
    public void modificaValutazione(Libro l, Integer val) {
        String query = "UPDATE FROM libri SET valutazione=? WHERE isbn=";
        try {
            PreparedStatement statement = db.getConnessione().prepareStatement(query);
            statement.setInt(1, l.getValutazione());
            statement.setString(2, l.getISBN());
            statement.executeUpdate();
        }catch(SQLException se){

        }
        l.setValutazione(val);
    }

    @Override
    public void modificaStato(Libro l, StatoLettura stato) {
        String query = "UPDATE FROM libri SET stato_lettura=? WHERE isbn=";
        try {
            PreparedStatement statement = db.getConnessione().prepareStatement(query);
            statement.setObject(1, l.getStatoLettura());
            statement.setString(2, l.getISBN());
            statement.executeUpdate();
        }catch(SQLException se){

        }
        l.setStatoLettura(stato);
    }
}
