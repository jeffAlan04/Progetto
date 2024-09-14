package application.alan.difficile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DescrizioneDifficileA {

    @FXML
    private Button nextButton;

    @FXML
    private Button esciButton;

    @FXML
    private Text gameRulesTextArea;

    @FXML
    public void initialize() {
        nextButton.setOnAction(event -> {
            try {
                apriEsercizio(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        esciButton.setOnAction(event -> {
            try {
                esci(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        descrizioneRegole();

    }

    // Method to switch to the "Dashbord" scene
    private void esci(ActionEvent event) throws IOException {
        Object root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Dashbord.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }

    // Method to switch to the specified next scene
    private void apriEsercizio(ActionEvent event) throws IOException {
        Object root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/EsercizioDifficileA.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }

    private void descrizioneRegole() {

        String regole = "Se sei arrivato fin qui, vuol dire che hai la stoffa per diventare un vero programmatore!! ;)\n" +
                "Ora dovrai superare il livello più difficile.\n" +
                "Come nei livelli precedenti, ti verrà mostrato un commento ed un codice\n" +
                "se c'è, trova gli errori e correggili direttamente sul codice\n" +
                "Potrai usare il pulsante dei suggerimenti, ma stai attento a non usufruirne.\n";

        gameRulesTextArea.setText(regole);

        gameRulesTextArea.setFont(Font.font("Lucida Console", FontWeight.BOLD, 25));

    }
}