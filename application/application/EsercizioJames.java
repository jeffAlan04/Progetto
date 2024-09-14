package application;

import application.Chiusura;
import application.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class EsercizioJames {

    @FXML
    public void ScenaDashbord(ActionEvent event) throws IOException {
        SceneManager.cambiaScena("Dashbord.fxml", event);
    }

    @FXML
    public void ScenaEsercizioFacile(ActionEvent event) throws IOException {
        SceneManager.cambiaScena("EsercizioFacileJ.fxml", event);
    }

    @FXML
    public void ScenaEsercizioIntermedio(ActionEvent event) throws IOException {
        SceneManager.cambiaScena("EsercizioIntermedioJ.fxml", event);
    }

    @FXML
    public void ScenaEsercizioDifficile(ActionEvent event) throws IOException {
        SceneManager.cambiaScena("EsercizioDifficileJ.fxml", event);
    }
    
    @FXML
    public void ScenaChiusura(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Chiusura.confermaChiusura(stage);
    }
}
