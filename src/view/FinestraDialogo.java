package view;

import model.libreria.Libreria;
import model.libreria.Libro;
import model.libreria.StatoLettura;

import javax.swing.*;
import java.awt.*;

public class FinestraDialogo extends JDialog {
    private JTextField isbnField;
    private JTextField autoreField;
    private JTextField genereField;
    private JTextField titoloField;
    private JComboBox<StatoLettura> statoComboBox;
    private JComboBox<Integer> valutazioneComboBox;
    private Libreria l;


    public FinestraDialogo(Libreria lib, JFrame framePadre){
        super(framePadre, "Aggiungi Nuovo Libro", true);
        l=lib;
        setSize(450, 320);
        setLocationRelativeTo(framePadre);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel pannello= new JPanel();
        pannello.setLayout(new BoxLayout(pannello, BoxLayout.Y_AXIS));
        pannello.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        Integer[] ratings = {1, 2, 3, 4, 5};
        this.valutazioneComboBox = new JComboBox<>(ratings);
        this.statoComboBox = new JComboBox<>(StatoLettura.values());


        // Campi di testo
        pannello.add(creaForm("ISBN:", isbnField = new JTextField(20)));
        pannello.add(creaForm("Titolo:", titoloField = new JTextField(20)));
        pannello.add(creaForm("Autore:", autoreField = new JTextField(20)));
        pannello.add(creaForm("Genere:", genereField = new JTextField(20)));

        // Menu a tendina per Valutazione
        pannello.add(creaValutazioni("Valutazione", valutazioneComboBox));
        pannello.add(creaStatoLettura("StatoLettura", statoComboBox));


        // pulsanti di aggiungi e annulla operazione
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton aggiungiButton = new JButton("Aggiungi");
        aggiungiButton.addActionListener(e ->
        {
            if(lib.aggiungi(creaLibro(isbnField.getText(),titoloField.getText(), autoreField.getText(),
                genereField.getText(),(StatoLettura) statoComboBox.getSelectedItem(), (Integer)valutazioneComboBox.getSelectedItem()))){
                JOptionPane.showMessageDialog(this, "Libro aagiunto con successo!");
                l.getLibri();// quando aggiungo mi si devono visualizzare nuovamente tutti i libri
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante l'inserimento.", "Errore", JOptionPane.ERROR_MESSAGE);
                l.getLibri();// quando aggiungo mi si devono visualizzare nuovamente tutti i libri
            }
             dispose();});
        buttonPanel.add(aggiungiButton);
        JButton annullaButton = new JButton("Annulla");
        annullaButton.addActionListener(e -> dispose()); // Chiude la dialog
        buttonPanel.add(annullaButton);

        add(pannello, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        }

    private Libro creaLibro(String isbn, String titolo, String autore,String genere, StatoLettura stato, Integer valutazione) {
        return new Libro(isbn,titolo,autore,genere,stato,valutazione);
    }//creaLibro


    private JPanel creaForm(String labelText, JTextField textField) {
        JPanel pannelloForm = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0)); // FlowLayout per allineamento semplice
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(80, (int)label.getPreferredSize().getHeight()));
        pannelloForm.add(label);
        pannelloForm.add(textField);
        return pannelloForm;
    }//creaForm


    private JPanel creaValutazioni(String labelText, JComboBox<Integer> comboBox) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0)); // FlowLayout
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(80, (int)label.getPreferredSize().getHeight()));
        rowPanel.add(label);
        rowPanel.add(this.valutazioneComboBox);
        return rowPanel;
    }//creaValutazioni

    private JPanel creaStatoLettura(String labelText, JComboBox<StatoLettura> comboBox) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0)); // FlowLayout
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(80, (int)label.getPreferredSize().getHeight()));
        rowPanel.add(label);
        rowPanel.add(this.statoComboBox);
        //this.statoComboBox.setSelectedItem(2);
        return rowPanel;
    }//creaStatoLettura


}//FinestraDiaologo





