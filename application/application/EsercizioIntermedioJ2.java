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

public class EsercizioIntermedioJ2 extends BaseController {

    @FXML
    private ListView<String> Lista2;  // ListView per visualizzare e gestire gli elementi da ordinare

    // Lista di riferimento con l'ordine corretto per questo esercizio
    private final ObservableList<String> ordineCorretto = FXCollections.observableArrayList(
    		"INIZIO",
    	    "DICHIARA una variabile \"numero\"",
    	    "DICHIARA una variabile \"somma\" = 0",
    	    "DICHIARA una variabile \"conteggio\" = 0",
    	    "RIPETI fino a quando \"numero\" è diverso da -1",
    	    "Leggi \"numero\" dall'utente",
    	    "SE \"numero\" è diverso da -1",
    	    "Incrementa \"somma\" di \"numero\"",
    	    "Incrementa \"conteggio\" di 1",
    	    "FINE SE",
    	    "FINE RIPETI",
    	    "SE \"conteggio\" è maggiore di 0",
    	    "Calcola \"media\" = \"somma\" / \"conteggio\"",
    	    "Stampa \"Somma: \", \"somma\"",
    	    "Stampa \"Media: \", \"media\"",
    	    "ALTRIMENTI",
    	    "Stampa \"Nessun numero valido inserito.\"",
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
    
    // Metodo per gestire il fallimento dell'esercizio
    @FXML
    public void ScenaFalimento(ActionEvent event) {
        salvaRisultato("Pseudocodifica Ordinata", "intermedio", 0, "fallimento");
    }

    // Metodo per tornare all'esercizio precedente
    @FXML
    public void ScenaEsercizioIndietro(ActionEvent event) throws IOException {
        SceneManager.cambiaScena("EsercizioIntermedioJ.fxml", event);
    }

    // Metodo per passare all'esercizio successivo
    @FXML
    public void ScenaEsercizioAvanti3(ActionEvent event) throws IOException {
        controllaOrdine(Lista2, ordineCorretto, "intermedio");        
        SceneManager.cambiaScena("EsercizioIntermedioJ3.fxml", event);
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
