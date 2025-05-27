package model.libreria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


//pattern singleton
public class GestioneDatabase {
    private static GestioneDatabase singleton;
    private Connection connessione= null;
    private static final String url="jdbc:sqlite:./libreria2_database.db";

    private GestioneDatabase(){
        try {
            connessione = DriverManager.getConnection(url);
            creaTabella();

        } catch (SQLException e) {

        }
    }//GestioneDatabase


   public static GestioneDatabase getInstance(){
        if(singleton==null)singleton=new GestioneDatabase();
        return singleton;
   }//getIstance


   public static Connection getConnessione(){
        return singleton.connessione;
   }//getConnessione


    private void creaTabella() throws SQLException {
        String queryCreazione = "CREATE TABLE IF NOT EXISTS libri (" +
                "isbn TEXT PRIMARY KEY NOT NULL," +
                "titolo TEXT NOT NULL," +
                "autore TEXT NOT NULL," +
                "genere TEXT NOT NULL," +
                "stato_lettura TEXT NOT NULL," +
                "valutazione INTEGER" +
                ");";
        try (Statement stmt = connessione.createStatement()) {
            stmt.execute(queryCreazione);
            System.out.println("Tabella 'libri' creata ");
        }

    }//creaTabella

    public void chiudiConnessione(){
        try {
            if (singleton.connessione != null ) {
                singleton.connessione.close();
            }
        } catch (SQLException e) {

        }
    }//chiudiConnessione


}//GestioneDatabase
