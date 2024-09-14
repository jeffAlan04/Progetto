package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Dashbord {

    @FXML
    private Text nomeUtenteText; // Un elemento Text nella dashboard per mostrare il nome utente

    // Metodo per impostare il nome utente
    public void initialize() {
        String username = Sessione.getUsername();; // Salvo il nome utente
        nomeUtenteText.setText("Benvenuto, " + username + "!");
    }

    // Metodo per passare alla scena di descrizione James
    @FXML
    public void ScenaDescrizioneJames(ActionEvent event) throws IOException {
        SceneManager.cambiaScena("DescrizioneJ.fxml", event);
    }

    // Metodo per passare alla scena di descrizione Alan
    @FXML
    public void ScenaDescrizioneAlan(ActionEvent event) throws IOException {
        SceneManager.cambiaScena("alan/EsercizioAlan.fxml", event);
    }


    // Metodo per passare alla scena di esercizio Alan
    @FXML
    public void ScenaInformazioni(ActionEvent event) throws IOException {
        SceneManager.cambiaScena("Informazioni.fxml", event);
    }

    // Metodo per chiudere l'applicazione con conferma
    @FXML
    public void ScenaChiusura(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Chiusura.confermaChiusura(stage);
    }
}
