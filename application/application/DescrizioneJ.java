package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.Collections;

public class DescrizioneJ extends BaseController {
    
	// ListaView per mostrare gli elementi da ordinare
    @FXML
    private ListView<String> ListaD;  
    
    // ListaView per mostrare l'ordine corretto degli elementi
    @FXML
    private ListView<String> ListaD1; 
    
    // Lista di riferimento con l'ordine corretto per questo esercizio
    private final ObservableList<String> ordineCorretto = FXCollections.observableArrayList(
        "Elemento 1",
        "Elemento 2",
        "Elemento 3"
    );

    private final ObservableList<String> ordineCorretto1 = FXCollections.observableArrayList(
        "Elemento 1",
        "Elemento 2",
        "Elemento 3"
    );
    
    // Metodo chiamato dopo che il controller è stato caricato
    @FXML
    private void initialize() {
        // Configura le ListView con i dati corretti e mescola l'ordine per la lista non corretta
        setupListView(ListaD1, ordineCorretto1);
        Collections.shuffle(ordineCorretto); // Mescola l'ordine degli elementi per la lista da ordinare
        setupListView(ListaD, ordineCorretto);
    }
    
    // Metodo per cambiare la scena all'esercizio James
    @FXML
    public void ScenaEsercizioJames(ActionEvent event) {
        SceneManager.cambiaScena("EsercizioJames.fxml", event);
    }
    
    // Metodo per tornare alla dashboard
    @FXML
    public void ScenaDashbord(ActionEvent event) {
        SceneManager.cambiaScena("Dashbord.fxml", event);
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
