package model.strategy;

import model.libreria.Libro;

import java.util.Comparator;
import java.util.List;

public class OrdinaByAutore implements  StrategiaOrdinamento{

    @Override
    public void ordina(List<Libro> lista) {
        lista.sort(Comparator.comparing(Libro::getAutore));
    }//ordina

}//OrdinaByAutore
