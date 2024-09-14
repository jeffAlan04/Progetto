package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.Collections;

public class EsercizioFacileJ3 extends BaseController {

    @FXML
    private ListView<String> Lista3;  

    // Lista di riferimento con l'ordine corretto per questo esercizio
    private final ObservableList<String> ordineCorretto = FXCollections.observableArrayList(
        "INIZIO",
        "DICHIARA una variabile messaggio",
        "RIPETI fino a quando messaggio è uguale a 'stop':",
        "Leggi messaggio dall'utente",
        "Se messaggio contiene la parola 'ciao', stampa messaggio",
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

    // Metodo per gestire il fallimento dell'esercizio
    @FXML
    public void ScenaFalimento(ActionEvent event) {
        salvaRisultato("Pseudocodifica Ordinata", "facile", 0, "fallimento");
    }

    // Metodo per tornare alla scena precedente
    @FXML
    public void ScenaEsercizioIndietro(ActionEvent event) {
        SceneManager.cambiaScena("EsercizioFacileJ2.fxml", event);
    }

    // Metodo per passare alla scena dei risultati
    @FXML
    public void ScenaRisultato(ActionEvent event) {
        controllaOrdine(Lista3, ordineCorretto, "facile");
        BaseController.setLivelloAttivo("facile");
        SceneManager.cambiaScena("RisultatoJ.fxml", event);
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
