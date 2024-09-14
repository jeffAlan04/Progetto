package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.Collections;

public class EsercizioDifficileJ2  extends BaseController{
	@FXML
    private ListView<String> Lista2;
	
	// Lista di riferimento con l'ordine corretto per questo esercizio
    private final ObservableList<String> ordineCorretto = FXCollections.observableArrayList(
    		"INIZIO",
    	    "DICHIARA una lista \"libri\"",
    	    "RIPETI fino a quando \"comando\" è diverso da \"esci\"",
    	    "Leggi \"comando\" dall'utente",
    	    "SE \"comando\" è uguale a \"aggiungi libro\"",
    	    "Leggi \"titolo\" del libro",
    	    "Aggiungi \"titolo\" alla lista \"libri\" con stato \"disponibile\"",
    	    "ALTRIMENTI SE \"comando\" è uguale a \"prenota libro\"",
    	    "Leggi \"titolo\" del libro",
    	    "SE \"titolo\" è disponibile",
    	    "Cambia stato di \"titolo\" a \"prenotato\"",
    	    "ALTRIMENTI",
    	    "Stampa \"Il libro non è disponibile\"",
    	    "ALTRIMENTI SE \"comando\" è uguale a \"restituisci libro\"",
    	    "Leggi \"titolo\" del libro",
    	    "SE \"titolo\" è prenotato",
    	    "Cambia stato di \"titolo\" a \"disponibile\"",
    	    "ALTRIMENTI",
    	    "Stampa \"Il libro non è prenotato\"",
    	    "ALTRIMENTI SE \"comando\" è uguale a \"stampa lista\"",
    	    "Stampa la lista dei libri con i loro stati",
    	    "FINE RIPETI",
    	    "FINE"
    );

    // Metodo chiamato dopo che il controller è stato caricato
    @FXML
    private void initialize() {
        // Crea una copia dell'ordine corretto e mescola gli elementi
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
        boolean conferma = Chiusura.confermaChiusuraDati(stage);
        if (conferma) {
        	chiusuraApplicazione();
        	}
    	}
}
