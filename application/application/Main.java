package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Carica il file FXML per la scena di login
            Parent root = FXMLLoader.load(getClass().getResource("Inizio.fxml"));
            
            // Imposta il palco senza bordi e senza decorazioni
            primaryStage.initStyle(StageStyle.UNDECORATED);
            
            // Imposta la scena e mostra il palco
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Stampa l'eccezione in caso di errore
        }
    }
    
    public static void main(String[] args) {
        launch(args); // Avvia l'applicazione JavaFX
    }
}
