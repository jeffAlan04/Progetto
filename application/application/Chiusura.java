package application;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;


public class Chiusura {
    public static void confermaChiusura(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Uscita");
        alert.setHeaderText("Sei sicuro di voler uscire?");
        alert.setContentText("Conferma per uscire o annulla per rimanere.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage.close();
        }
    }
}
