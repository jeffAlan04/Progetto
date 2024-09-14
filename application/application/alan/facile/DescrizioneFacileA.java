package application.alan.facile;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Objects;

public class DescrizioneFacileA {

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
        Object root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/EsercizioFacileA.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }

    private void descrizioneRegole() {

        String regole = "In questo livello ti verrà mostrato un commento, un codice\n" +
                "e tre opzioni.\n" +
                "Scegli quella che ritieni più oppurtuna in modo da rendere il codice\n" +
                "coerente con il commento.\n" +
                "Se ti trovi in difficoltà potrai utlizzare il pulsante per i suggerimenti,\n" +
                "ma ricorda che ti verranno assegnati solo la metà dei punti.";

        gameRulesTextArea.setText(regole);

        gameRulesTextArea.setFont(Font.font("Lucida Console", FontWeight.BOLD, 25));

    }
}
