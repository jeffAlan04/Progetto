package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class SceneManager {

    // Metodo per cambiare scena semplice
    public static void cambiaScena(String fxmlFile, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(SceneManager.class.getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            mostraMessaggio("Errore", "Errore durante il caricamento della scena", "C'è stato un errore nel caricamento della scena: " + fxmlFile);
            e.printStackTrace();
        }
    }

    // Metodo per cambiare scena e iniettare un controller
    public static void cambiaScenaConController(String fxmlFile, ActionEvent event, Consumer<Object> controllerConsumer) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlFile));
            Parent root = loader.load();

            // Ottieni il controller e passa l'oggetto consumer per operazioni aggiuntive
            Object controller = loader.getController();
            controllerConsumer.accept(controller);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            mostraMessaggio("Errore", "Errore durante il caricamento della scena", "C'è stato un errore nel caricamento della scena: " + fxmlFile);
            e.printStackTrace();
        }
    }

    // Metodo per mostrare un messaggio di alert all'utente
    public static void mostraMessaggio(String titolo, String header, String contenuto) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(header);
        alert.setContentText(contenuto);
        alert.showAndWait();
    }
    
}
