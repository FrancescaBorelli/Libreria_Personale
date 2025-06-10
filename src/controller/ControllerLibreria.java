package controller;

import model.libreria.Libreria;
import view.LibreriaGrafica;

public class ControllerLibreria {

    public static void main(String [] args) throws Exception {
        Libreria libreria= new Libreria();
        LibreriaGrafica grafica= new LibreriaGrafica(libreria);
        libreria.attach(grafica);
        grafica.setVisible(true);
    }//main


}//ControllerLibreria
