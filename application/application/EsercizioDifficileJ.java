package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.Collections;

public class EsercizioDifficileJ  extends BaseController{
	@FXML
    private ListView<String> Lista;
	
	// Lista di riferimento con l'ordine corretto per questo esercizio
    private final ObservableList<String> ordineCorretto = FXCollections.observableArrayList(
    		"INIZIO",
    	    "DICHIARA una lista \"studenti\"",
    	    "RIPETI fino a quando \"comando\" è diverso da \"esci\"",
    	    "Leggi \"comando\" dall'utente",
    	    "SE \"comando\" è uguale a \"aggiungi studente\"",
    	    "Leggi \"nome\" dello studente",
    	    "Aggiungi \"nome\" a \"studenti\"",
    	    "ALTRIMENTI SE \"comando\" è uguale a \"aggiungi voto\"",
    	    "Leggi \"nome\" dello studente",
    	    "Leggi \"voto\" e aggiungi \"voto\" alla lista dei voti dello studente",
    	    "ALTRIMENTI SE \"comando\" è uguale a \"calcola media\"",
    	    "Leggi \"nome\" dello studente",
    	    "Calcola la media dei voti dello studente",
    	    "Stampa \"La media di\", \"nome\", \"è\", media",
    	    "ALTRIMENTI SE \"comando\" è uguale a \"stampa lista\"",
    	    "Stampa la lista degli studenti con le loro medie",
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
        boolean conferma = Chiusura.confermaChiusuraDati(stage);
        if (conferma) {
        	chiusuraApplicazione();
        	}
    	}
}
