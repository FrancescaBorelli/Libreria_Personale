package model.strategy;

import model.libreria.Libro;
import java.util.Comparator;
import java.util.List;

public class OrdinamentoDefault implements StrategiaOrdinamento{
    @Override
    public void ordina(List<Libro> lista) {
       lista.sort(Comparator.comparing(Libro::getISBN));
    }//ordina
}//OrdinamentoDefault
