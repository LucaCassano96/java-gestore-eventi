package eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {

    //ATTRIBUTI

    public static int POSTI_PRENOTATI = 0;

    private String titolo;
    private LocalDate data;
    private int postiTotali;

    private int postiPrenotati;


    // COSTRUTTORE
    public Evento(String titolo, LocalDate data, int postiTotali, int postiPrenotati) throws IllegalArgumentException {
        this.titolo = titolo;
        this.data = data;

        LocalDate currentDate = LocalDate.now();

        // Verifica se la data è passata
        if (data.isBefore(currentDate)) {
            throw new IllegalArgumentException("La data dell'evento è già passata.");
        }

        if ( postiTotali <= 10) {
            throw new IllegalArgumentException("Il numero di posti deve essere maggiore di 10");
        }
        else {
            this.postiTotali = postiTotali;
        }

        this.postiPrenotati = POSTI_PRENOTATI;
    }


    //GETTER

    public String getTitolo() {
        return titolo;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public LocalDate getData() {
        return data;
    }

    //SETTER

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    //METODI


    //prenota
    public int prenota(int numeroDiPostiDaPrenotare)throws IllegalStateException{

        LocalDate currentDate = LocalDate.now();

        // Verifica se l'evento è già passato
        if (data.isBefore(currentDate)) {
            throw new IllegalStateException("Impossibile prenotare per un evento passato.");
        }

        if (getPostiPrenotati() + numeroDiPostiDaPrenotare > getPostiTotali() ) {
            throw new IllegalStateException("Purtroppo non c'è più posto libero ");
        }

        postiPrenotati += numeroDiPostiDaPrenotare;
        return numeroDiPostiDaPrenotare;
    }

    //disdici

    public int disdici(int numeroDiPostiDaDisdire)throws IllegalStateException{

        LocalDate currentDate = LocalDate.now();

        // Verifica se l'evento è già passato
        if (data.isBefore(currentDate)) {
            throw new IllegalStateException("Impossibile prenotare per un evento passato.");
        }

        if (getPostiPrenotati() == 0) {
            throw new IllegalStateException("Non ci sono posti prenotati non puoi disdire");
        }

        postiPrenotati -= numeroDiPostiDaDisdire;
        return numeroDiPostiDaDisdire;
    }


    // To String
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Personalizza il formato della data
        String dataFormattata = data.format(formatter);
        return dataFormattata + " - " + titolo;
    }
}




