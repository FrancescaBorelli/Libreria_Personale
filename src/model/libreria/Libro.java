package model.libreria;

public class Libro {
    private String titolo, autore, ISBN, genere;
    private StatoLettura statoLettura;
    private Integer valutazione;


    public Libro(String cod, String titolo, String autore , String genere, StatoLettura stato, Integer voto){
        this.ISBN=cod;
        this.titolo=titolo;
        this.autore=autore;
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



    public void setStatoLettura(StatoLettura statoLettura) {
        this.statoLettura = statoLettura;
    }//setStatoLettura

    public void setValutazione(Integer valutazione) {
        this.valutazione = valutazione;
    }//setValutazione


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
