package view;

import model.libreria.Libreria;
import model.libreria.StatoLettura;

import javax.swing.*;
import java.awt.*;


public class MenuLaterale extends JPanel {
    private Libreria lib;
    private JButton bottoneAggiungi;
    private ButtonGroup menuBottoniOrdinamento;
    private FinestraDialogo finestraAggiungi;
    private ButtonGroup menuBottoniStatoLettura;

    public MenuLaterale(Libreria l, JFrame parent){
        lib=l;
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Opzioni"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        //bottone aggIungi
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0.0;
        bottoneAggiungi = new JButton("Aggiungi Libro");
        bottoneAggiungi.addActionListener(e -> {
            finestraAggiungi= new FinestraDialogo(lib, parent);
            finestraAggiungi.setVisible(true);
        });


        add(bottoneAggiungi, gbc);





        //Separatore
        gbc.gridy = 1;
        add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("Ordina Per:"), gbc);



        menuBottoniOrdinamento= new ButtonGroup();

        //creo i bottoni di ordinamento
        //default: ordine casuale
        JRadioButton opzioneDefault= new JRadioButton ("Default");
        JRadioButton opzioneTitolo= new JRadioButton ("Titolo");
        JRadioButton  opzioneAutore= new JRadioButton ("Autore");
        JRadioButton opzioneVoto= new JRadioButton("Valutazione");

        opzioneDefault.setSelected(true);
        opzioneDefault.addActionListener(e ->{
            if(opzioneDefault.isSelected()){
                lib.cambiaOrdinamento(opzioneDefault.getText());
            }
        });//actionPerformed
        menuBottoniOrdinamento.add(opzioneDefault);
        //add(opzioneDefault);


        //titolo
        opzioneTitolo.addActionListener(e_->{
            if(opzioneTitolo.isSelected()){
                lib.cambiaOrdinamento(opzioneTitolo.getText());
            }
        });
        menuBottoniOrdinamento.add(opzioneTitolo);
        //add(opzioneTitolo);



        //autore
        opzioneAutore.addActionListener(e_->{
            if(opzioneAutore.isSelected()){
                lib.cambiaOrdinamento(opzioneAutore.getText());
            }
        });
        menuBottoniOrdinamento.add(opzioneAutore);
        //add(opzioneAutore);



        //valutazione
        opzioneVoto.addActionListener(e_->{
            if(opzioneVoto.isSelected()){
                lib.cambiaOrdinamento(opzioneVoto.getText());
            }
        });
        menuBottoniOrdinamento.add(opzioneVoto);
        gbc.gridy = 3;
        add(opzioneDefault, gbc);
        gbc.gridy = 4;
        add(opzioneTitolo, gbc);
        gbc.gridy = 5;
        add(opzioneAutore, gbc);
        gbc.gridy = 6;
        add(opzioneVoto, gbc);


       //filtro per cercare in base allo stato lettura
        menuBottoniStatoLettura= new ButtonGroup();
        //creo i bottoni di filtraggio
        //default: mostra tutti i libri
        JRadioButton tutti= new JRadioButton ("TUTTI I LIBRI");
        tutti.addActionListener(e -> {
            lib.getLibri();
        });
        menuBottoniStatoLettura.add(tutti);
        tutti.setSelected(true);//inizialmente mostro tutti i libri

        JRadioButton da_leggere= new JRadioButton ("DA LEGGERE");
        da_leggere.addActionListener(e -> {
            lib.getLibroByStato(StatoLettura.DA_LEGGERE);
        });
        menuBottoniStatoLettura.add(da_leggere);

        JRadioButton letto= new JRadioButton ("LETTO");
        letto.addActionListener(e->{
            lib.getLibroByStato(StatoLettura.LETTO);
        });
        menuBottoniStatoLettura.add(letto);

        JRadioButton in_lettura= new JRadioButton("IN LETTURA");
        in_lettura.addActionListener(e-> {
            lib.getLibroByStato(StatoLettura.IN_LETTURA);
        });
        menuBottoniStatoLettura.add(in_lettura);


        //Separatore
        gbc.gridy = 7;
        add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("Filtra:"), gbc);




        gbc.gridy = 9;
        add(tutti, gbc);
        gbc.gridy = 10;
        add(da_leggere, gbc);
        gbc.gridy = 11;
        add(in_lettura, gbc);
        gbc.gridy = 12;
        add(letto, gbc);


        gbc.gridy=13;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;

        add(Box.createVerticalGlue(), gbc);
    }


}//MenuOrdinamento



