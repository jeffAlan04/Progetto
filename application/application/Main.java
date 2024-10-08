package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

import java.util.Objects;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Inizio.fxml")));
            primaryStage.initStyle(StageStyle.UNDECORATED);            
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
    
    public static void main(String[] args) {
        launch(args); 
    }
}
