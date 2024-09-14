package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.Collections;

public class EsercizioDifficileJ3  extends BaseController{
	@FXML
    private ListView<String> Lista3;
	
	// Lista di riferimento con l'ordine corretto per questo esercizio
    private final ObservableList<String> ordineCorretto = FXCollections.observableArrayList(
    		"INIZIO",
    	    "DICHIARA una variabile \"sequenza\" con 5 numeri casuali",
    	    "DICHIARA una variabile \"tentativi\"=0",
    	    "Stampa \"Memorizza questa sequenza:\", sequenza",
    	    "Pausa di 5 secondi (simulata)",
    	    "RIPETI fino a quando \"tentativi\" è minore di 3",
    	    "DICHIARA una variabile \"risposta\"",
    	    "Leggi \"risposta\" dall'utente",
    	    "SE \"risposta\" è uguale a \"sequenza\"",
    	    "Stampa \"Hai vinto!\"",
    	    "Esci dal ciclo",
    	    "ALTRIMENTI",
    	    "Incrementa \"tentativi\" di 1",
    	    "SE \"tentativi\" è uguale a 3",
    	    "Stampa \"Hai esaurito i tentativi. Hai perso.\"",
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
        boolean conferma = Chiusura.confermaChiusuraDati(stage);
        if (conferma) {
        	chiusuraApplicazione();
        	}
    	}
}
