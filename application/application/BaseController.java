package application;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public abstract class BaseController {

    protected static String livelloAttivo; // Campo statico per il livello attivo

    public static void setLivelloAttivo(String livello) {
        livelloAttivo = livello;
    }

    protected void controllaOrdine(ListView<String> listView, ObservableList<String> ordineCorretto, String livello) {
        ObservableList<String> elementiAttuali = listView.getItems();

        // Debug: stampa gli elementi attuali e l'ordine corretto
        System.out.println("Elementi attuali: " + elementiAttuali);
        System.out.println("Ordine corretto: " + ordineCorretto);

        // Aggiunge il punteggio in base all'esito del controllo e al livello
        if (elementiAttuali.equals(ordineCorretto)) {
            aggiornaPunteggio(livello, 1);  // Incrementa il punteggio di 1 se l'ordine è corretto
        }

        // Stampa il punteggio corrente nella console (puoi gestirlo diversamente)
        System.out.println("Punteggio corrente per " + livello + ": " + calcolaPunteggio(livello));
    }

    private void aggiornaPunteggio(String livello, int valore) {
        PunteggioManager punteggioManager = PunteggioManager.getInstance();
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

    protected void setupListView(ListView<String> listView, ObservableList<String> elementi) {
        listView.setItems(elementi);

        listView.setCellFactory(lv -> {
            ListCell<String> cella = new ListCell<>();
            cella.textProperty().bind(cella.itemProperty());

            cella.setOnDragDetected(evento -> {
                if (cella.getItem() != null) {
                    Dragboard db = cella.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent contenuto = new ClipboardContent();
                    contenuto.putString(cella.getItem());
                    db.setContent(contenuto);
                    evento.consume();
                }
            });

            cella.setOnDragOver(evento -> {
                if (evento.getGestureSource() != cella && evento.getDragboard().hasString()) {
                    evento.acceptTransferModes(TransferMode.MOVE);
                }
                evento.consume();
            });

            cella.setOnDragDropped(evento -> {
                Dragboard db = evento.getDragboard();
                if (db.hasString()) {
                    int indiceTrascinato = listView.getItems().indexOf(db.getString());
                    int questoIndice = cella.getIndex();

                    String itemTrascinato = listView.getItems().remove(indiceTrascinato);

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
 // Metodo per salvare i risultati
    protected void salvaRisultato(String nomeEsercizio, String livello, int punteggio, String stato) {
        String username = Sessione.getUsername();
        if (username == null) {
            username = "Utente sconosciuto"; // Valore di fallback
        }

        try (FileWriter writer = new FileWriter("src/application/risultati.txt", true)) { // Apre il file in modalità append
            writer.write(String.format("Data: %s\n", LocalDateTime.now()));
            writer.write(String.format("Username: %s\n", username));
            writer.write(String.format("Nome Esercizio: %s\n", nomeEsercizio));
            writer.write(String.format("Livello: %s\n", livello));
            writer.write(String.format("Punteggio: %d\n", punteggio));
            writer.write(String.format("Stato: %s\n", stato));
            writer.write("====================================\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
 // Aggiungi un metodo per gestire la chiusura dell'applicazione
    protected void setupApplicationCloseHandler(Stage stage) {
        stage.setOnCloseRequest((WindowEvent event) -> {
            // Salva i risultati con "fallimento" se non è stato completato correttamente
            salvaRisultato("Esercizio Non Completato", "N/A", 0, "fallimento");
            Platform.exit();
        });
    }
}
