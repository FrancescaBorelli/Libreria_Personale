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


    @Override
    public void caricaDati() {
        String query = "SELECT * FROM libri";

        try {
            PreparedStatement statement = db.getConnessione().prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getString("isbn"),
                        rs.getString("titolo"),
                        rs.getString("autore"),
                        rs.getString("genere"),
                        StatoLettura.valueOf(rs.getString("stato_lettura")),
                        rs.getInt("valutazione")
                );
                libri.add(libro);
            }
        } catch (SQLException se) {

        }



    }//caricaDati

    @Override
    public List<Libro> getAllLibri() {
        return new LinkedList<>(libri);
    }


    @Override
    public List<Libro> cercaLibro_ISBN(String cod) {
       List<Libro> res= new LinkedList<>();
        for (Libro libro : libri) {
            if (libro.getISBN().equalsIgnoreCase(cod)) res.add(libro);
        }
        return res;
    }//cercaLibro_ISBN

    @Override
    public List<Libro> cercaLibro_autore(String autore) {
        LinkedList<Libro> res = new LinkedList<>();
        for (Libro libro : libri)
            if (libro.getAutore().equalsIgnoreCase(autore)) res.add(libro);
        return res;
    }

    @Override
    public List<Libro> cercaLibro_titolo(String titolo) {
        LinkedList<Libro> res = new LinkedList<>();
        for (Libro libro : libri)
            if (libro.getTitolo().equalsIgnoreCase(titolo)) res.add(libro);
        return res;
    }


    @Override
    public List<Libro> cercaLibro_genere(String genere) {
        LinkedList<Libro> res = new LinkedList<>();
        for (Libro libro : libri)
            if (libro.getGenere().equalsIgnoreCase(genere)) res.add(libro);
        return res;
    }


    @Override
    public List<Libro> cercaLibro_statoLettura(StatoLettura stato){
        LinkedList<Libro> res = new LinkedList<>();
        for (Libro libro : libri)
            if (libro.getStatoLettura().equals(stato)) res.add(libro);
        return res;
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
            return false;
        }
        libri.add(l);
        return true;
    }//inserisciLibri

    @Override
    public boolean eliminaLibro(Libro l) {
        String query = "DELETE FROM libri WHERE isbn=?";
        try {
            PreparedStatement statement = db.getConnessione().prepareStatement(query);
            statement.setString(1, l.getISBN());
            statement.executeUpdate();
        }catch(SQLException se){
            return false;
        }
        libri.remove(l);
        return true;
    }

    @Override
    public boolean modificaValutazione(Libro l, Integer val) {
        String query = "UPDATE libri SET valutazione=? WHERE isbn=?";
        try {
            PreparedStatement statement = db.getConnessione().prepareStatement(query);
            statement.setInt(1, l.getValutazione());
            statement.setString(2, l.getISBN());
            statement.executeUpdate();
        }catch(SQLException se){
            return false;
        }
        l.setValutazione(val);
        return true;
    }

    @Override
    public boolean modificaStato(Libro l, StatoLettura stato) {
        String query = "UPDATE libri SET stato_lettura=? WHERE isbn=?";
        try {
            PreparedStatement statement = db.getConnessione().prepareStatement(query);
            statement.setString(1,stato.toString());
            statement.setString(2, l.getISBN());
            statement.executeUpdate();
        }catch(SQLException se){
            return false;
        }
        l.setStatoLettura(stato);
        return true;
    }

    @Override
    public void chiudiConnessione() {
        db.chiudiConnessione();
    }
}
