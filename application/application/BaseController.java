package application;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class BaseController {

    // Campo statico per mantenere il livello attivo
    protected static String livelloAttivo;

    // Metodo per impostare il livello attivo
    public static void setLivelloAttivo(String livello) {
        livelloAttivo = livello;
    }

    // Metodo che controlla se l'ordine degli elementi nella ListView è corretto
    // Se l'ordine è corretto, aggiorna il punteggio
    protected void controllaOrdine(ListView<String> listView, ObservableList<String> ordineCorretto, String livello) {
        ObservableList<String> elementiAttuali = listView.getItems();

        // Confronta gli elementi attuali con l'ordine corretto
        if (elementiAttuali.equals(ordineCorretto)) {
            // Incrementa il punteggio di 1 se l'ordine è corretto
            aggiornaPunteggio(livello, 1);
        }
    }

    // Metodo privato per aggiornare il punteggio in base al livello di difficoltà
    private void aggiornaPunteggio(String livello, int valore) {
        application.PunteggioManager punteggioManager = application.PunteggioManager.getInstance();
        switch (livello) {
            case "facile":
                punteggioManager.aggiungiPunteggioFacile(valore);
                break;
            case "intermedio":
                punteggioManager.aggiungiPunteggioIntermedio(valore);
                break;
            case "difficile":
                punteggioManager.aggiungiPunteggioDifficile(valore);
                break;
            default:
                System.out.println("Livello sconosciuto!");
        }
    }

    // Metodo per calcolare il punteggio corrente in base al livello
    protected int calcolaPunteggio(String livello) {
        PunteggioManager punteggioManager = PunteggioManager.getInstance();
        switch (livello) {
            case "facile":
                return punteggioManager.getPunteggioFacile();
            case "intermedio":
                return punteggioManager.getPunteggioIntermedio();
            case "difficile":
                return punteggioManager.getPunteggioDifficile();
            default:
                return 0;
        }
    }

    // Configura la ListView per consentire il drag-and-drop dei suoi elementi
    protected void setupListView(ListView<String> listView, ObservableList<String> elementi) {
        listView.setItems(elementi);

        // Imposta un ListCell personalizzato per supportare il drag-and-drop
        listView.setCellFactory(lv -> {
            ListCell<String> cella = new ListCell<>();
            cella.textProperty().bind(cella.itemProperty());

            // Rileva l'inizio del drag-and-drop
            cella.setOnDragDetected(evento -> {
                if (cella.getItem() != null) {
                    Dragboard db = cella.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent contenuto = new ClipboardContent();
                    contenuto.putString(cella.getItem());
                    db.setContent(contenuto);
                    evento.consume();
                }
            });

            // Consente il drag-and-drop sugli altri elementi
            cella.setOnDragOver(evento -> {
                if (evento.getGestureSource() != cella && evento.getDragboard().hasString()) {
                    evento.acceptTransferModes(TransferMode.MOVE);
                }
                evento.consume();
            });

            // Gestisce il drop dell'elemento trascinato
            cella.setOnDragDropped(evento -> {
                Dragboard db = evento.getDragboard();
                if (db.hasString()) {
                    int indiceTrascinato = listView.getItems().indexOf(db.getString());
                    int questoIndice = cella.getIndex();

                    // Rimuove l'elemento dalla posizione originale
                    String itemTrascinato = listView.getItems().remove(indiceTrascinato);

                    // Aggiunge l'elemento nella nuova posizione
                    if (questoIndice < 0 || questoIndice >= listView.getItems().size()) {
                        listView.getItems().add(itemTrascinato);
                        questoIndice = listView.getItems().size() - 1;
                    } else {
                        listView.getItems().add(questoIndice, itemTrascinato);
                    }

                    evento.setDropCompleted(true);
                    listView.getSelectionModel().select(questoIndice);
                } else {
                    evento.setDropCompleted(false);
                }
                evento.consume();
            });

            return cella;
        });
    }

    // Metodo per salvare i risultati dell'esercizio in un file
    protected void salvaRisultato(String nomeEsercizio, String livello, int punteggio, String stato) {
        // Ottieni il nome dell'utente dalla sessione
        String username = Sessione.getUsername();
        if (username == null) {
            username = "Utente sconosciuto"; // Valore di fallback se il nome utente non è presente
        }
        LocalDateTime now = LocalDateTime.now();
        
        // Formatta il LocalDateTime senza i secondi
       	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = now.format(formatter);
        // Tenta di scrivere i risultati in un file di testo
        try (FileWriter writer = new FileWriter("C:\\Users\\james\\Desktop\\application (2)\\application(2)\\application\\risultati.txt", true)) {
            writer.write(String.format("Data: %s\n", formattedDateTime)); // Scrive la data e l'ora
            writer.write(String.format("Username: %s\n", username));       // Scrive il nome utente
            writer.write(String.format("Nome Esercizio: %s\n", nomeEsercizio));  // Scrive il nome dell'esercizio
            writer.write(String.format("Livello: %s\n", livello));          // Scrive il livello
            writer.write(String.format("Punteggio: %d\n", punteggio));      // Scrive il punteggio
            writer.write(String.format("Stato: %s\n", stato));              // Scrive lo stato (es: completato, fallito)
            writer.write("====================================\n");
        } catch (IOException e) {
            e.printStackTrace(); // Stampa l'errore se si verifica un'eccezione
        }
    }
    
    public void logout() {
        // Recupera l'utente corrente
        String username = Sessione.getUsername();
        LocalDateTime now = LocalDateTime.now();
        
        // Formatta il LocalDateTime senza i secondi
       	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = now.format(formatter);
        
        // Scrivi i dati di logout su un file
        try (FileWriter writer = new FileWriter("C:\\Users\\james\\Desktop\\application (2)\\application(2)\\application\\login_data.txt", true)) {
            writer.write(String.format("Logout - Username: %s, Data e Orario: %s%n", username, formattedDateTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Pulisci la sessione dell'utente
        Sessione.setUsername(null);
    }
    
    public void chiusuraApplicazione() {
        // Recupera l'utente corrente
        String username = Sessione.getUsername();
        LocalDateTime now = LocalDateTime.now();
        
        // Formatta il LocalDateTime senza i secondi
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = now.format(formatter);
        
        // Scrivi i dati di chiusura su un file
        try (FileWriter writer = new FileWriter("C:\\Users\\james\\Desktop\\application (2)\\application(2)\\application\\login_data.txt", true)) {
            writer.write(String.format("App Closed - Username: %s, Data e Orario: %s%n", username,formattedDateTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Chiudi l'applicazione
        Platform.exit();
    }
}
