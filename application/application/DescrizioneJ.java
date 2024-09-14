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

import java.util.Collections;


public class DescrizioneJ extends BaseController {
	
	@FXML
    private ListView<String> ListaD;
	
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
   
    @FXML
    private void initialize() {
    	setupListView(ListaD1, ordineCorretto1);
        Collections.shuffle(ordineCorretto);
        setupListView(ListaD, ordineCorretto);
    }
    @FXML
    public void ScenaEsercizioJames(ActionEvent event) {
        SceneManager.cambiaScena("EsercizioJames.fxml", event);
    }
    @FXML
    public void ScenaDashbord(ActionEvent event) {
        SceneManager.cambiaScena("Dashbord.fxml", event);	
    }
    
    @FXML
    public void ScenaChiusura(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Chiusura.confermaChiusura(stage);
    }

}
