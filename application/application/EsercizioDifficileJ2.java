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

public class EsercizioDifficileJ2  extends BaseController {
	@FXML
    private ListView<String> Lista2;
	
	// Lista di riferimento con l'ordine corretto per questo esercizio
    private final ObservableList<String> ordineCorretto = FXCollections.observableArrayList(
    		"INIZIO",
            "DICHIARA una variabile messaggio",
            "RIPETI fino a quando messaggio è uguale a \"esci\":",
            "Leggi messaggio dall'utente",
            "Se messaggio contiene almeno una parola palindroma",
            "   e messaggio contiene almeno un carattere speciale",
            "   e messaggio ha una lunghezza compresa tra 15 e 30 caratteri, stampa messaggio",
            "FINE RIPETI",
            "FINE"
    );

    @FXML
    private void initialize() {
    	ObservableList<String> elementiMescolati = FXCollections.observableArrayList(ordineCorretto);
        Collections.shuffle(elementiMescolati);
        
        // Imposta la ListView con l'ordine mescolato
        setupListView(Lista2, elementiMescolati);
    }
    
    @FXML
    public void ScenaFalimento(ActionEvent event) {
    	salvaRisultato("Pseudocodifica Ordinata", "difficile", 0, "fallimento");
    }
    
    // Cambia scena per tornare alla scena 'EsercizioDifficileJ'.
    @FXML
    public void ScenaEsercizioIndietro(ActionEvent event) {
        SceneManager.cambiaScena("EsercizioDifficileJ.fxml", event);
    }
    
    // Cambia scena per passare alla scena 'EsercizioDifficileJ3'.
    @FXML
    public void ScenaEsercizioAvanti3(ActionEvent event) {
    	controllaOrdine(Lista2, ordineCorretto, "difficile");
        SceneManager.cambiaScena("EsercizioDifficileJ3.fxml", event);
    }

    // Chiude l'applicazione confermando la chiusura.
    @FXML
    public void ScenaChiusura(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Chiusura.confermaChiusura(stage);
    }
}
