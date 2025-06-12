package test;

import model.libreria.GestioneDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SingletonTest {
    private GestioneDatabase db;
    private GestioneDatabase db1;

    @Test
    public void verificaSingleton(){
        db= GestioneDatabase.getInstance();
        db1= GestioneDatabase.getInstance();
       assertEquals(db, db1);
    }







}
