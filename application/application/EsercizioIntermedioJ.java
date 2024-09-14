package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;

public class EsercizioIntermedioJ extends BaseController {

    @FXML
    private ListView<String> Lista;  // ListView per visualizzare e gestire gli elementi da ordinare

    // Lista di riferimento con l'ordine corretto per questo esercizio
    private final ObservableList<String> ordineCorretto = FXCollections.observableArrayList(
    		"INIZIO",
    	    "DICHIARA una variabile \"messaggio\"",
    	    "DICHIARA una variabile \"conteggio\" = 0",
    	    "RIPETI fino a quando \"messaggio\" è uguale a \"stop\"",
    	    "Leggi \"messaggio\" dall'utente",
    	    "SE \"messaggio\" contiene la parola \"ciao\"",
    	    "Stampa \"Hai detto ciao!\"",
    	    "ALTRIMENTI SE \"messaggio\" contiene la parola \"come va\"",
    	    "Stampa \"Spero tu stia bene!\"",
    	    "ALTRIMENTI",
    	    "Stampa \"Messaggio non riconosciuto.\"",
    	    "Incrementa \"conteggio\" di 1",
    	    "SE \"conteggio\" è uguale a 5",
    	    "Stampa \"Hai inserito 5 messaggi.\"",
    	    "Resetta \"conteggio\" a 0",
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

    // Metodo per gestire il fallimento dell'esercizio
    @FXML
    public void ScenaFalimento(ActionEvent event) {
        salvaRisultato("Pseudocodifica Ordinata", "intermedio", 0, "fallimento");
    }

    // Metodo per passare alla scena successiva
    @FXML
    public void ScenaEsercizioAvanti2(ActionEvent event) throws IOException {
        controllaOrdine(Lista, ordineCorretto, "intermedio");
        SceneManager.cambiaScena("EsercizioIntermedioJ2.fxml", event);
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
