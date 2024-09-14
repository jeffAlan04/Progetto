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

public class EsercizioDifficileJ3  extends BaseController {
	@FXML
    private ListView<String> Lista3;
	
	// Lista di riferimento con l'ordine corretto per questo esercizio
    private final ObservableList<String> ordineCorretto = FXCollections.observableArrayList(
    		"INIZIO",
            "DICHIARA una variabile messaggio",
            "RIPETI fino a quando messaggio è uguale a \"stop\":",
            "Leggi messaggio dall'utente",
            "Se messaggio contiene almeno tre numeri distinti",
            "e messaggio contiene una parola che inizia con una lettera maiuscola e termina con una lettera minuscola",
            "e messaggio non contiene la parola 'escludi', stampa messaggio",
            "FINE RIPETI",
            "FINE"
    );

    @FXML
    private void initialize() {
    	ObservableList<String> elementiMescolati = FXCollections.observableArrayList(ordineCorretto);
        Collections.shuffle(elementiMescolati);
        
        // Imposta la ListView con l'ordine mescolato
        setupListView(Lista3, elementiMescolati);
    }
    @FXML
    public void ScenaFalimento(ActionEvent event) {
    	salvaRisultato("Pseudocodifica Ordinata", "difficile", 0, "fallimento");
    }
    @FXML
    public void ScenaEsercizioIndietro(ActionEvent event) {
        SceneManager.cambiaScena("EsercizioDifficile2J.fxml", event);
    }

    // Cambia scena per passare alla scena 'RisultatoJ'.
    @FXML
    public void ScenaRisultato(ActionEvent event) {
    	controllaOrdine(Lista3, ordineCorretto, "difficile");
    	BaseController.setLivelloAttivo("difficile");
        SceneManager.cambiaScena("RisultatoJ.fxml", event);
    }

    // Chiude l'applicazione confermando la chiusura.
    @FXML
    public void ScenaChiusura(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Chiusura.confermaChiusura(stage);
    }
}
