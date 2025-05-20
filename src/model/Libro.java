package model;

public class Libro {
    private String titolo, autore, ISBN, genere;
    private StatoLettura statoLettura;
    private Integer valutazione;


    public Libro(String titolo, String autore, String cod, String genere, StatoLettura stato, Integer voto){
        this.titolo=titolo;
        this.autore=autore;
        this.ISBN=cod;
        this.genere=genere;
        this.statoLettura=stato;
        this.valutazione=voto;
    }//Libro

    public String getTitolo(){
        return this.titolo;
    }//getTitolo

    public String getAutore(){
        return this.autore;
    }//getAutore

    public String getISBN(){
        return this.ISBN;
    }//getISBN

    public String getGenere() {
        return this.genere;
    }//getGenere

    public StatoLettura getStatoLettura(){
        return this.statoLettura;
    }//getStatoLettura

    public Integer getValutazione(){
        return this.valutazione;
    }//getValutazione


    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }//setTitolo

    public void setStatoLettura(StatoLettura statoLettura) {
        this.statoLettura = statoLettura;
    }//setStatoLettura

    public void setValutazione(Integer valutazione) {
        this.valutazione = valutazione;
    }//setValutazione

    public void setGenere(String genere) {
        this.genere = genere;
    }//setGenere

    public void setAutore(String autore) {
        this.autore = autore;
    }//setAutore

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }//setISBN


    @Override
    public String toString() {
        return "Libro{" +
                "autore='" + autore + '\'' +
                ", titolo='" + titolo + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", genere='" + genere + '\'' +
                ", statoLettura=" + statoLettura +
                ", valutazione=" + valutazione +
                '}';
    }
}
