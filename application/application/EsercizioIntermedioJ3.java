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

import java.io.IOException;
import java.util.Collections;

public class EsercizioIntermedioJ3 extends BaseController {
	
	@FXML
    private ListView<String> Lista3;
	
	// Lista di riferimento con l'ordine corretto per questo esercizio
    private final ObservableList<String> ordineCorretto = FXCollections.observableArrayList(
    		"INIZIO",
            "DICHIARA una variabile messaggio",
            "RIPETI fino a quando messaggio è uguale a \"stop\"",
            "Leggi messaggio dall'utente",
            "Se messaggio non è uguale a \"stop\"",
            "e messaggio contiene almeno una lettera maiuscola",
            "e messaggio contiene almeno una lettera minuscola, stampa messaggio",
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
    	salvaRisultato("Pseudocodifica Ordinata", "intermedio", 0, "fallimento");
    }
    
    @FXML
    public void ScenaEsercizioIndietro(ActionEvent event) throws IOException {
        SceneManager.cambiaScena("EsercizioIntermedioJ2.fxml", event);
    }

    @FXML
    public void ScenaRisultato(ActionEvent event) throws IOException {
    	controllaOrdine(Lista3, ordineCorretto, "intermedio");
    	BaseController.setLivelloAttivo("intermedio");
        SceneManager.cambiaScena("RisultatoJ.fxml", event);
    }

    @FXML
    public void ScenaChiusura(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Chiusura.confermaChiusura(stage);
    }
}
