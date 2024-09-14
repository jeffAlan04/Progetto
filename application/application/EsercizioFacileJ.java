package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.Collections;

public class EsercizioFacileJ extends BaseController {

    @FXML
    private ListView<String> Lista;  
    
    // Lista di riferimento con l'ordine corretto per questo esercizio
    private final ObservableList<String> ordineCorretto = FXCollections.observableArrayList(
    		"INIZIO",
    	    "DICHIARA una variabile messaggio",
    	    "RIPETI fino a quando messaggio è uguale a 'esci':",
    	    "Stampa \"Ciao, Mondo!\"",
    	    "Leggi messaggio dall'utente",
    	    "FINE RIPETI",
    	    "FINE"
    );

    // Metodo chiamato dopo che il controller è stato caricato
    @FXML
    private void initialize() {
        ObservableList<String> elementiMescolati = FXCollections.observableArrayList(ordineCorretto);
        Collections.shuffle(elementiMescolati);
        
        // Imposta la ListView con l'ordine mescolato
        setupListView(Lista, elementiMescolati);
    }

    // Metodo per gestire la scena di fallimento
    @FXML
    public void ScenaFalimento(ActionEvent event) {
        salvaRisultato("Pseudocodifica Ordinata", "facile", 0, "fallimento");
    }

    // Metodo per controllare l'ordine e passare alla scena successiva
    @FXML
    public void ScenaEsercizioAvanti2(ActionEvent event) {
        controllaOrdine(Lista, ordineCorretto, "facile");
        SceneManager.cambiaScena("EsercizioFacileJ2.fxml", event);
    }

    // Metodo per gestire la chiusura della finestra con conferma
    @FXML
    public void ScenaChiusura(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        boolean conferma = Chiusura.confermaChiusuraDati(stage);
        if (conferma) {
        	chiusuraApplicazione();
        	}
    	}
    }

