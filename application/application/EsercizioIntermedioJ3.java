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

public class EsercizioIntermedioJ3 extends BaseController {

    @FXML
    private ListView<String> Lista3;  // ListView per visualizzare e gestire gli elementi da ordinare

    // Lista di riferimento con l'ordine corretto per questo esercizio
    private final ObservableList<String> ordineCorretto = FXCollections.observableArrayList(
    		"INIZIO",
    	    "DICHIARA una variabile \"parola\"",
    	    "RIPETI fino a quando \"parola\" è diverso da \"fine\"",
    	    "Leggi \"parola\" dall'utente",
    	    "DICHIARA una variabile \"palindromo\"=vero",
    	    "DICHIARA una variabile \"lunghezza\"=lunghezza di \"parola\"",
    	    "DICHIARA una variabile \"i\"=0",
    	    "DICHIARA una variabile \"j\"=\"lunghezza\"-1",
    	    "RIPETI fino a quando \"i\" è minore di \"j\"",
    	    "SE \"parola\"[\"i\"] è diverso da \"parola\"[\"j\"]",
    	    "Imposta \"palindromo\" a falso",
    	    "Esci dal ciclo",
    	    "FINE SE",
    	    "Incrementa \"i\" di 1",
    	    "Decrementa \"j\" di 1",
    	    "FINE RIPETI",
    	    "SE \"palindromo\" è vero",
    	    "Stampa \"La parola è un palindromo.\"",
    	    "ALTRIMENTI",
    	    "Stampa \"La parola non è un palindromo.\"",
    	    "FINE SE",
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
        salvaRisultato("Pseudocodifica Ordinata", "intermedio", 0, "fallimento");
    }
    
    // Metodo per tornare all'esercizio precedente
    @FXML
    public void ScenaEsercizioIndietro(ActionEvent event) throws IOException {
        SceneManager.cambiaScena("EsercizioIntermedioJ2.fxml", event);
    }

    // Metodo per passare alla schermata dei risultati
    @FXML
    public void ScenaRisultato(ActionEvent event) throws IOException {
        controllaOrdine(Lista3, ordineCorretto, "intermedio");
        BaseController.setLivelloAttivo("intermedio");
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
