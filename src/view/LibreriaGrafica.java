package view;

import model.libreria.Libreria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class LibreriaGrafica extends JFrame implements Observer {
    private Libreria soggetto;
    private ModelloTabella modello;
    private TabellaLibri tabella;
    private PannelloRicerca pannello;
    private MenuLaterale menuLaterale;



    public LibreriaGrafica(Libreria l ) {
        this.soggetto = l;
        modello = new ModelloTabella(soggetto);
        tabella = new TabellaLibri(soggetto, modello);
        pannello = new PannelloRicerca(soggetto);
        menuLaterale = new MenuLaterale(soggetto, this);

        setTitle("Gestione Libreria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 800);


        setLayout(new BorderLayout());
        add(pannello, BorderLayout.NORTH);
        add(new JScrollPane(tabella), BorderLayout.CENTER);
        add(menuLaterale, BorderLayout.WEST);


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    soggetto.chiudiFile();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LibreriaGrafica.this,
                            "Errore durante la chiusura dell'app : ",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }//LibreriaGrafica




    @Override
    public void aggiorna() {
        aggiornaGui();
    }//aggiorna


    private void aggiornaGui() {
        modello.fireTableDataChanged();
    }//aggiornaGui

}//libreriaGrafica
