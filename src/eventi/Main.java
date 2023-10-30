package eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci il titolo dell'evento: ");
        String titolo = scanner.nextLine();

        System.out.print("Inserisci la data dell'evento (dd MM yy): ");
        String dataString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yy");
        LocalDate data = LocalDate.parse(dataString, formatter);

        System.out.print("Inserisci il numero di posti totali: ");
        int postiTotali = scanner.nextInt();

        System.out.print("Inserisci il numero di posti prenotati: ");
        int postiPrenotati = scanner.nextInt();

        Evento nuovoEvento = null;
        try {
            nuovoEvento = new Evento(titolo, data, postiTotali, postiPrenotati);
            System.out.println("Nuovo evento creato con successo:");
            System.out.println(nuovoEvento);
        } catch (IllegalArgumentException e) {
            System.err.println("Errore nella creazione dell'evento: " + e.getMessage());
        }

        int sommaPrenotazioni = 0;

        // Prenotazioni
        System.out.print("Vuoi effettuare una prenotazione? (SI/NO): ");
        scanner.nextLine();
        String risposta = scanner.nextLine();
        while (risposta.toUpperCase(Locale.ROOT).equals("SI")) {
            System.out.print("Inserisci il numero di posti da prenotare: ");
            int postiDaPrenotare = scanner.nextInt();

            try {
                assert nuovoEvento != null;
                int postiPrenotatiEffettivi = nuovoEvento.prenota(postiDaPrenotare);
                sommaPrenotazioni += postiPrenotatiEffettivi;
                System.out.println("Prenotazione effettuata per " + postiPrenotatiEffettivi + " posti.");
            } catch (IllegalStateException e) {
                System.err.println("Errore nella prenotazione: " + e.getMessage());
            }

            System.out.print("Vuoi effettuare un'altra prenotazione? (SI/NO): ");
            scanner.nextLine();
            risposta = scanner.nextLine();

        }

        assert nuovoEvento != null;
        System.out.println("Posti prenotati: " + sommaPrenotazioni);
        System.out.println("Posti disponibili: " + (nuovoEvento.getPostiTotali() - postiPrenotati - sommaPrenotazioni));


        // disdire prenotazione

        int sommaPrenotazioniDisdette = 0;

        System.out.print("Vuoi disdire una prenotazione? (Si/No): ");
        String rispostaDisdetta = scanner.nextLine();

        while (rispostaDisdetta.equalsIgnoreCase("Si")) {
            System.out.print("Inserisci il numero di posti da disdire: ");
            int postiDisdetti = scanner.nextInt();

            try {
                int postiDisdettiEffettivi = nuovoEvento.disdici(postiDisdetti);
                sommaPrenotazioniDisdette += postiDisdettiEffettivi;
                System.out.println("Disdetta effettuata per " + postiDisdettiEffettivi + " posti.");
            } catch (IllegalStateException e) {
                System.err.println("Errore nella disdetta: " + e.getMessage());
            }
            System.out.print("Vuoi effettuare un'altra disdetta? (Si/No): ");
            scanner.nextLine();
            rispostaDisdetta = scanner.nextLine();
        }

        int postiPrenotatiTotali = sommaPrenotazioni - sommaPrenotazioniDisdette;
        int postiDisponibili = nuovoEvento.getPostiTotali() - postiPrenotatiTotali;

        System.out.println("Posti prenotati: " + postiPrenotatiTotali);
        System.out.println("Posti disponibili: " + postiDisponibili);
    }
}