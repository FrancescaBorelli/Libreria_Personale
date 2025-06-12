package test;

import model.libreria.LibreriaSqLite;
import model.libreria.Libro;
import model.libreria.StatoLettura;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RicercaModifica {

    private static LibreriaSqLite libreria;
    private static Libro l1;

    @BeforeAll
    public static void inizializza() {
        libreria = new LibreriaSqLite();
        l1 = new Libro("73773", "Speranze", "Francesca", "Narrativa", StatoLettura.DA_LEGGERE, 0);
    }//inizializza

    @Test
    public void aggiungiLibro(){
        libreria.inserisciLibro(l1);
        if (libreria.cercaLibro_autore("Francesca").contains(l1)){
            l1.setStatoLettura(StatoLettura.IN_LETTURA);
        }
        assertEquals(StatoLettura.IN_LETTURA, l1.getStatoLettura());

    }//aggiungiLibro


    @AfterAll
    public static void eliminaLibro(){
        libreria.eliminaLibro(l1);
    }

}
