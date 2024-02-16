import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GestoreMagazzinoApp {
    public static void main(String[] args) {
        Magazzino magazzino = new Magazzino();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Aggiungi prodotto");
            System.out.println("2. Rimuovi prodotto");
            System.out.println("3. Visualizza inventario");
            System.out.println("4. Calcola valore totale del magazzino");  // Nuovo: Aggiunta dell'opzione
            System.out.println("5. Visualizza informazioni prodotto");  // Nuovo: Aggiunta dell'opzione
            System.out.println("6. Modifica prezzo unitario di un prodotto");  // Nuovo: Aggiunta dell'opzione
            System.out.println("7: Esiste il prodotto?");
            System.out.println("8: Visualizza prodotti con prezzo superiore a una soglia");
            System.out.println("9: Visualizza tutti i proddotti");
            System.out.println("10: Calcola la quantità totale di quel prodotto");
            System.out.println("0. Salva i dati con successo");

            System.out.print("Scelta: ");
            int scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    System.out.print("Nome prodotto: ");
                    String nomeProdotto = scanner.next();
                    System.out.print("Quantità: ");
                    int quantitaAggiunta = scanner.nextInt();
                    System.out.print("Prezzo unitario: ");
                    double prezzoUnitario = scanner.nextDouble();  // Nuovo: Inserimento del prezzo unitario
                    magazzino.aggiungiProdotto(nomeProdotto, quantitaAggiunta , prezzoUnitario);
                    break;
                case 2:
                    System.out.print("Nome prodotto: ");
                    String nomeProdottoRimozione = scanner.next();
                    System.out.print("Quantità: ");
                    int quantitaRimozione = scanner.nextInt();
                    magazzino.rimuoviProdotto(nomeProdottoRimozione, quantitaRimozione);
                    break;
                case 3:
                    magazzino.visualizzaInventario();
                    break;

                case 4:
                    double valoreTotale = magazzino.calcolaValoreTotale();
                    System.out.println("Valore totale del magazzino: " + valoreTotale);
                    break;

                case 5:  // Nuovo: Visualizza informazioni prodotto
                    System.out.print("Nome prodotto: ");
                    String nomeProdottoVisualizzazione = scanner.next();
                    magazzino.visualizzaProdotto(nomeProdottoVisualizzazione);
                    break;
                case 6:  // Nuovo: Modifica prezzo unitario di un prodotto
                    System.out.print("Nome prodotto: ");
                    String nomeProdottoModificaPrezzo = scanner.next();
                    System.out.print("Nuovo prezzo unitario: ");
                    double nuovoPrezzoUnitario = scanner.nextDouble();
                    magazzino.modificaPrezzoUnitario(nomeProdottoModificaPrezzo, nuovoPrezzoUnitario);
                    break;

                case 7:
                    System.out.println();
                    String nomeProdottoP = scanner.next();
                    if(magazzino.esisteProdotto(nomeProdottoP))
                        System.out.println("Si c'è");
                    else
                        System.out.println("No non c'è");
                    break;

                case 8:
                    System.out.print("Inserisci la soglia del prezzo: ");
                    double sogliaPrezzo = scanner.nextDouble();
                    magazzino.visualizzaProdottiConPrezzoSuperiore(sogliaPrezzo);
                    break;
                
                case 9:
                    magazzino.visualizzaTuttiProdotti();
                    break;

                case 10:
                    System.out.println("Inserisci il nome del prodotto: ");
                    String nomeP = scanner.next();
                    magazzino.calcolaQuantitaTotaleProdotto(nomeP);
                    break;
                case 0:
                    magazzino.salvaDatiSuFile("magazzino.txt");  // Nuovo: Salva dati su file prima di uscire
                    System.out.println("Grazie per aver usato l'applicazione. Arrivederci!");
                    System.exit(0);
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }
}
