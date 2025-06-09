package view;

import model.libreria.Libreria;

import javax.swing.*;
import java.awt.*;

public class PannelloRicerca extends JPanel {
    private Libreria lib;
    private JTextField areaTesto;
    private JComboBox<String>comboBoxRicerca;
    private String selezioneCurr;
    private JButton bottoneCerca;


    public PannelloRicerca(Libreria l){
        this.lib=l;
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        setBorder(BorderFactory.createTitledBorder("Ricerca"));;
        areaTesto= new JTextField(20);
        bottoneCerca=new JButton("Cerca");
        //criteri di ricerca
        String[] criteriRicerca={"Titolo", "Genere", "Autore"};
        comboBoxRicerca= new JComboBox<>(criteriRicerca);
        selezioneCurr="Titolo";//deafult

        add(comboBoxRicerca);
        add(areaTesto);
        add(bottoneCerca);

        comboBoxRicerca.addActionListener(e->{
            selezioneCurr=(String)comboBoxRicerca.getSelectedItem();
            System.out.println(selezioneCurr);
            areaTesto.setEnabled(true);
            bottoneCerca.setEnabled(true);
        });

        bottoneCerca.addActionListener(e->{
            String testo=areaTesto.getText();
            switch(selezioneCurr){
                case("Titolo"):{
                    lib.getLibroByTitolo(testo);
                    break;
                } case("Genere"):{
                    lib.getLibroByGenere(testo);
                    break;
                } case("Autore"):{
                    lib.getLibroByAutore(testo);
                    break;
                }
            }
        });
    }






}//PannelloRicerca
