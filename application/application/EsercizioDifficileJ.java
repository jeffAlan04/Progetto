package application;

import application.BaseController;
import application.Chiusura;
import application.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.Collections;

public class EsercizioDifficileJ  extends BaseController {
	@FXML
    private ListView<String> Lista;
	
	// Lista di riferimento con l'ordine corretto per questo esercizio
    private final ObservableList<String> ordineCorretto = FXCollections.observableArrayList(
    		"INIZIO",
    		"DICHIARA una variabile messaggio",
    		"RIPETI fino a quando messaggio è uguale a \"stop\":",
    		"Leggi messaggio dall'utente",
    		"Se messaggio contiene la parola \"salta\", continua il ciclo (senza stampare)",
    		"Se messaggio non è uguale a \"stop\", e:",
    		"messaggio ha più di 5 caratteri, e",
    		"messaggio contiene almeno un numero, stampa messaggio",
    		"FINE RIPETI",
    		"FINE"
    );

    @FXML
    private void initialize() {
    	ObservableList<String> elementiMescolati = FXCollections.observableArrayList(ordineCorretto);
        Collections.shuffle(elementiMescolati);
        
        // Imposta la ListView con l'ordine mescolato
        setupListView(Lista, elementiMescolati);
    }

    // Cambia scena per tornare alla scena 'EsercizioDifficileJ'.
    @FXML
    public void ScenaFalimento(ActionEvent event) {
    	salvaRisultato("Pseudocodifica Ordinata", "difficile", 0, "fallimento");
    }

    // Cambia scena per passare alla scena 'EsercizioDifficileJ2'.
    @FXML
    public void ScenaEsercizioAvanti2(ActionEvent event) {
    	controllaOrdine(Lista, ordineCorretto, "difficile");
        SceneManager.cambiaScena("EsercizioDifficileJ2.fxml", event);
    }

    // Chiude l'applicazione confermando la chiusura.
    @FXML
    public void ScenaChiusura(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Chiusura.confermaChiusura(stage);
    }
}
