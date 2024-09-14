package application.alan.facile;

import application.alan.CostruzioneScenaAlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

import static application.alan.Constants.styleButtonDisattivato;

// Classe utilizzata per creare ciascun livello in base ai parametri passati in input
public class ModelloEsercizioFacile {

    boolean suggerimentoAttivato = false;

    public void initialize(RadioButton r1, RadioButton r2, RadioButton r3,
                           int rispostaGiusta,
                           Button verifyButton, Text feedbackText,
                           Button esciButton,
                           Button nextButton, String prossimoLivello,
                           int livelloAttuale, int esercizioAttuale, Button helpButton) {

        nextButton.setDisable(true);
        nextButton.setStyle(styleButtonDisattivato);

        verifyButton.setOnAction(event -> {
            controllaRisposta(event, rispostaGiusta, r1, r2, r3, feedbackText,
                    nextButton, livelloAttuale, esercizioAttuale);
        });

        // Ascolta il pulsante di verifica
        esciButton.setOnAction(event -> {
            try {
                esci(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        helpButton.setOnAction(event -> {
            try {
                suggerimenti(event);
                suggerimentoAttivato = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        nextButton.setOnAction(event -> {
            try {
                prossimo(event, prossimoLivello);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void controllaRisposta(ActionEvent event, int rispostaGiusta,
                                   RadioButton r1, RadioButton r2, RadioButton r3,
                                   Text feedbackText, Button nextButton, int livelloAttuale, int esercizioAttuale) {
        if (rispostaGiusta == 1) {
            if (r1.isSelected()) {
                feedbackGiusto(feedbackText, nextButton, livelloAttuale, esercizioAttuale);
            } else {
                feedbackSbagliato(feedbackText, nextButton);
            }
        }
        if (rispostaGiusta == 2) {
            if (r2.isSelected()) {
                feedbackGiusto(feedbackText, nextButton, livelloAttuale, esercizioAttuale);
            } else {
                feedbackSbagliato(feedbackText, nextButton);
            }
        }
        if (rispostaGiusta == 3) {
            if (r3.isSelected()) {
                feedbackGiusto(feedbackText, nextButton, livelloAttuale, esercizioAttuale);
            } else {
                feedbackSbagliato(feedbackText, nextButton);
            }
        }
    }

    private void feedbackGiusto(Text feedbackText, Button nextButton, int livelloAttuale, int esercizioAttuale) {
        feedbackText.setText("Complimenti! Hai corretto correttamente il codice.");
        feedbackText.setFill(javafx.scene.paint.Color.GREEN);
        nextButton.setDisable(false);
        nextButton.setStyle("-fx-background-color: #00BFFF; -fx-text-fill: white");
        CostruzioneScenaAlan.aggiornaProgresso(livelloAttuale, esercizioAttuale, suggerimentoAttivato);
    }

    private void feedbackSbagliato(Text feedbackText, Button nextButton) {
        feedbackText.setText("Errore! La condizione non è ancora corretta.");
        feedbackText.setFill(javafx.scene.paint.Color.RED);
        nextButton.setDisable(true);
    }


    private void esci(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../Dashbord.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void prossimo(ActionEvent event, String prossimoLivello) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(prossimoLivello)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void suggerimenti(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Modal.fxml")));
        stage.setScene(new Scene(root));
        stage.setTitle("Modal soluzione");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.show();
    }


}
