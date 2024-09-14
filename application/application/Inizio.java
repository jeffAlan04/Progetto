package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Inizio {

    // Metodo per cambiare alla scena di login
    @FXML
    public void ScenaLogin(ActionEvent event) {
        SceneManager.cambiaScena("Login.fxml", event);
    }

    // Metodo per gestire la chiusura della finestra
    @FXML
    public void ScenaChiusura(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Chiusura.confermaChiusura(stage);
    }
}
