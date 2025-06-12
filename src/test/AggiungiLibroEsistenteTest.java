package test;

import model.libreria.LibreriaSqLite;
import model.libreria.Libro;
import model.libreria.StatoLettura;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AggiungiLibroEsistenteTest {
    private static LibreriaSqLite libreria;
    private static Libro l1;
    private static Libro l2;

    @BeforeAll
    public static void inizializza() {
        libreria = new LibreriaSqLite();
        l1 = new Libro("73773", "Speranze", "Francesca", "Narrativa", StatoLettura.DA_LEGGERE, 0);
        l2 = new Libro("73773", "Pinocchio", "Collodi", "Narrativa", StatoLettura.DA_LEGGERE, 0);
    }//inizializza

    @Test
        public void aggiungiLibro(){
        //inserisco prima l1
            int sizePrimaInserimento1= libreria.getAllLibri().size();
            boolean risultato1= libreria.inserisciLibro(l1);
            int sizeDopoInserimento1 = libreria.getAllLibri().size();
            assertTrue(risultato1);
            assertEquals(sizePrimaInserimento1+1, sizeDopoInserimento1);
        //mi aspetto che vada a buon fine, provo ad inserire l2
            boolean risultato2= libreria.inserisciLibro(l2);
            int sizeDopoInserimento2= libreria.getAllLibri().size();
            assertFalse(risultato2);
            assertEquals(sizeDopoInserimento1,sizeDopoInserimento2);

    }//aggiungiLibro


    @AfterAll
    public static void eliminaLibro(){
        libreria.eliminaLibro(l1);
    }







}
