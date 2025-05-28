package view;

import model.libreria.Libreria;
import model.libreria.Libro;
import model.libreria.StatoLettura;

import javax.swing.table.AbstractTableModel;


public class ModelloTabella extends AbstractTableModel {
   private Libreria l;
   private final String [] colonne={"ISBN", "Titolo", "Autore", "Genere", "Stato", "Valutazione"};

    public ModelloTabella(Libreria lib){
        l=lib;
    }

   @Override
    public int getRowCount() {
        return l.getLibriDaVisualizzare().size();
    }//getRowCount

    @Override
    public int getColumnCount() {
        return colonne.length;
    }//getColumnCount

    @Override
    public String getColumnName(int columnIndex) {
        return colonne[columnIndex];
    }//getColumnName

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case(4):
                return StatoLettura.class;
            case(5):
                return Integer.class;
            default:
                return String.class;
        }
    }//getColumnClass

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Libro libro= l.getLibriDaVisualizzare().get(rowIndex);
        switch(columnIndex){
            case(0):return libro.getISBN();
            case(1):return libro.getTitolo();
            case(2):return libro.getAutore();
            case(3):return libro.getGenere();
            case(4):return libro.getStatoLettura();
            case(5):return libro.getValutazione();
            default:return null;
        }
    }//getValueAt


    public Libro getLibroAt(int riga) {
        return l.getLibriDaVisualizzare().get(riga);
    }
}//ModelloTabella
