package test;


import model.libreria.LibreriaSqLite;
import model.libreria.Libro;
import model.libreria.StatoLettura;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AggiungiRimuoviLibroTest {
    private static LibreriaSqLite libreria;
    private static Libro l;

@BeforeAll
    public static void inizializza(){
        libreria= new LibreriaSqLite();
        l=new Libro("73773", "Speranze", "Francesca",  "Narrativa", StatoLettura.DA_LEGGERE, 0);
    }//inizializza

@Test
    public void aggiungiLibro(){
        int sizePrimaInserimento= libreria.getAllLibri().size();
        boolean risultato= libreria.inserisciLibro(l);
        int sizeDopoInserimento = libreria.getAllLibri().size();
        assertTrue(risultato);
        assertEquals(sizePrimaInserimento+1, sizeDopoInserimento);
    }//aggiungiLibro

@Test
    public void rimuoviLibro(){
    int sizePrima= libreria.getAllLibri().size();
    boolean risultato= libreria.eliminaLibro(l);
    int sizeDopo= libreria.getAllLibri().size();
    assertTrue(risultato);
    assertEquals(sizePrima-1, sizeDopo);
    }//rimuoviLibro


}//AggiungiRimuovi
