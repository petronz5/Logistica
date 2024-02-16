import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Magazzino {

    private Map<String, Integer> inventario;
    private Map<String, Double> prezziUnitari;  // Nuovo: Mappa dei prezzi unitari

    public Magazzino() {
        this.inventario = new HashMap<>();
        this.prezziUnitari = new HashMap<>();  // Nuovo: Inizializzazione della mappa dei prezzi unitari
        caricaDaFile("magazzino.txt");
        
    }

    private void caricaDaFile(String nomeFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] parti = linea.split(",");
                String nomeProdotto = parti[0];
                int quantita = Integer.parseInt(parti[1]);
                double prezzoUnitario = Double.parseDouble(parti[2]);

                inventario.put(nomeProdotto, quantita);
                prezziUnitari.put(nomeProdotto, prezzoUnitario);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Errore durante il caricamento dei dati da " + nomeFile);
            e.printStackTrace();
        }
    }

    
    public void aggiungiProdotto(String nomeProdotto, int quantita , double prezzoUnitario) {
        inventario.put(nomeProdotto, inventario.getOrDefault(nomeProdotto, 0) + quantita);
        prezziUnitari.put(nomeProdotto, prezzoUnitario);  // Nuovo: Aggiunta del prezzo unitario
        System.out.println("Prodotto aggiunto con successo.");
    }

    public void rimuoviProdotto(String nomeProdotto, int quantita) {
        if (inventario.containsKey(nomeProdotto)) {
            int quantitaAttuale = inventario.get(nomeProdotto);
            if (quantita <= quantitaAttuale) {
                inventario.put(nomeProdotto, quantitaAttuale - quantita);
                System.out.println("Prodotto rimosso con successo.");
            } else {
                System.out.println("Quantità richiesta superiore a quella disponibile.");
            }
        } else {
            System.out.println("Prodotto non trovato nel magazzino.");
        }
    }

    public void visualizzaProdotto(String nomeProdotto) {
        if (inventario.containsKey(nomeProdotto)) {
            int quantita = inventario.get(nomeProdotto);
            double prezzoUnitario = prezziUnitari.get(nomeProdotto);
            System.out.println("Nome: " + nomeProdotto);
            System.out.println("Quantità disponibile: " + quantita);
            System.out.println("Prezzo unitario: " + prezzoUnitario);
        } else {
            System.out.println("Prodotto non trovato nel magazzino.");
        }
    }

    public void visualizzaInventario() {
        System.out.println("Inventario attuale:");
        for (Map.Entry<String, Integer> entry : inventario.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public double calcolaValoreTotale() {
        double valoreTotale = 0.0;

        for (Map.Entry<String, Integer> entry : inventario.entrySet()) {
            String nomeProdotto = entry.getKey();
            int quantita = entry.getValue();
            double prezzoUnitario = prezziUnitari.get(nomeProdotto);
            valoreTotale += quantita * prezzoUnitario;
        }

        return valoreTotale;
    }

    public void modificaPrezzoUnitario(String nomeProdotto, double nuovoPrezzoUnitario) {
        if (esisteProdotto(nomeProdotto)) {
            prezziUnitari.put(nomeProdotto, nuovoPrezzoUnitario);
            System.out.println("Prezzo unitario modificato con successo.");
        } else {
            System.out.println("Prodotto non trovato nel magazzino.");
        }
    }

    public boolean esisteProdotto(String nomeProdotto){
        if(inventario.containsKey(nomeProdotto))
            return true;
        else
            return false;
    }

    public void salvaDatiSuFile(String nomeFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFile))) {
            for (Map.Entry<String, Integer> entry : inventario.entrySet()) {
                String nomeProdotto = entry.getKey();
                int quantita = entry.getValue();
                double prezzoUnitario = prezziUnitari.get(nomeProdotto);
                writer.write(nomeProdotto + "," + quantita + "," + prezzoUnitario);
                writer.newLine();
            }
            System.out.println("Dati salvati con successo su " + nomeFile);
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio dei dati su file.");
            e.printStackTrace();
        }
    }

    public void visualizzaProdottiConPrezzoSuperiore(double sogliaPrezzo) {
        System.out.println("Prodotti con prezzo superiore a " + sogliaPrezzo + ":");
        for (Map.Entry<String, Double> entry : prezziUnitari.entrySet()) {
            String nomeProdotto = entry.getKey();
            double prezzoUnitario = entry.getValue();
            if (prezzoUnitario > sogliaPrezzo) {
                System.out.println(nomeProdotto + ": " + prezzoUnitario);
            }
        }
    }

    public void visualizzaTuttiProdotti() {
        System.out.println("Lista di tutti i prodotti nel magazzino:");
        for (Map.Entry<String, Integer> entry : inventario.entrySet()) {
            String nomeProdotto = entry.getKey();
            int quantita = entry.getValue();
            double prezzoUnitario = prezziUnitari.get(nomeProdotto);
            System.out.println(nomeProdotto + ": Quantità = " + quantita + ", Prezzo unitario = " + prezzoUnitario);
        }
    }

    public void calcolaQuantitaTotaleProdotto(String nomeProdotto) {
        if (inventario.containsKey(nomeProdotto)) {
            int quantitaTotale = inventario.get(nomeProdotto);
            System.out.println("Quantità totale di " + nomeProdotto + ": " + quantitaTotale);
        } else {
            System.out.println("Prodotto non trovato nel magazzino.");
        }
    }

}