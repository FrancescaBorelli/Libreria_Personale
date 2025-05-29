package view;

import model.libreria.Libreria;
import model.libreria.Libro;
import model.libreria.StatoLettura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabellaLibri extends JTable {
    private Libreria lib;
    private ModelloTabella modello;


    public TabellaLibri(Libreria l, ModelloTabella m) {
        super(m);
        lib = l;
        modello = m;
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setRowHeight(50);
        setFont(new Font("Arial", Font.PLAIN, 14));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int riga = rowAtPoint(e.getPoint());
                int colonna = columnAtPoint(e.getPoint());
                Libro l = modello.getLibroAt(riga);
                if (colonna == 0) {
                    mostraFinestraEliminazione(l);
                } else if (colonna == 4 || colonna == 5) {
                    mostraFinestra(colonna, l);
                }


            }
        });


    }



    @Override
    public String getToolTipText(MouseEvent event) {
        Point p = event.getPoint();
        int riga = rowAtPoint(p);
        int colonna = columnAtPoint(p);

        if (riga >= 0 && colonna >= 0) {
            if (colonna == 0) {
                return "Clicca per eliminare il libro";
            } else if(colonna==4 || colonna==5) {
                return "Clicca per modificare il campo";
            }
        }
        return null;

    }//getToolTipText





    private void mostraFinestra(int c, Libro l) {
        switch (c) {
            case (4): {
                JPanel pannello = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                JLabel etichetta = new JLabel("Seleziona nuovo stato");
                pannello.add(etichetta);

                JComboBox<StatoLettura> opzioni = new JComboBox<>();
                for (StatoLettura stato : StatoLettura.values()) {
                    opzioni.addItem(stato);
                }
                pannello.add(opzioni);
                opzioni.setSelectedItem(l.getStatoLettura());

                int option = JOptionPane.showOptionDialog(
                        this, pannello, "Modifica stato lettura per il libro:\n" + "ISBN: " + l.getISBN(),
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (option == JOptionPane.OK_OPTION) {
                    if (lib.inserisciStato(l, (StatoLettura) opzioni.getSelectedItem())) {
                        JOptionPane.showMessageDialog(this, "Stato di lettura modificato con successo!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Errore durante la modifica dello stato nel database.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            }//case Stato Lettura
            case (5): {
                JPanel pannello = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                JLabel etichetta = new JLabel("Seleziona una valutazione");
                pannello.add(etichetta);

                JComboBox<Integer> opzioni = new JComboBox<>();
                for (int i = 0; i <= 5; i++) {
                    opzioni.addItem(i);
                }
                pannello.add(opzioni);
                opzioni.setSelectedItem(l.getValutazione());

                int opzione = JOptionPane.showOptionDialog(
                        this, pannello, "Modifica valutazione per il libro:\n" + "ISBN: " + l.getISBN(),
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (opzione == JOptionPane.OK_OPTION) {
                    if (lib.inserisciValutazione(l, (Integer) opzioni.getSelectedItem())) {
                        JOptionPane.showMessageDialog(this, "Valutazione modificata con successo!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Errore durante la modifica dello valutazione.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            }
        }
    }//mostraFinestra


    private void mostraFinestraEliminazione(Libro l) {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Sei sicuro di voler eliminare il libro?\n" + "ISBN: " + l.getISBN() + "\n" + "Titolo:" + l.getTitolo(),
                "Conferma Eliminazione",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            if (lib.elimina(l)) {
                JOptionPane.showMessageDialog(this, "Libro eliminato con successo!");
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione del libro.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//MostraFinestraEliminazione

}//TabellaLibri


