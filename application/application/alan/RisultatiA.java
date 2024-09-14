package application.alan;

import application.Sessione;
import application.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static application.alan.Constants.FILE_PATH;

public class RisultatiA {


    @FXML
    private Button esciButton;

    @FXML
    private Text risultati;

    @FXML
    private Text complimento;

    @FXML
    public void initialize() {

        esciButton.setOnAction(event -> {
            try {
                esci(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        mostraRisultati();
    }

    // Method to switch to the "Dashbord" scene
    private void esci(ActionEvent event) throws IOException {
        Object root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Dashbord.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }

    public void mostraRisultati() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<User> userList = new ArrayList<>();

        // Leggo il file progressi.json e ne metto il contenuto in List<User>
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type userListType = new TypeToken<ArrayList<User>>() {
                }.getType();
                userList = gson.fromJson(reader, userListType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String punteggio = "";
        String testo = "";
        for (User user : userList) {
            // Ottengo i dati dello user loggato grazie a Sessione.getUsername()
            if (user.getUserName() != null && user.getUserName().equals(Sessione.getUsername())) {
                if (user.getPunteggio() <= 70)
                    testo = "Puoi fare di meglio " + user.getUserName();
                else if (user.getPunteggio() > 70 && user.getPunteggio() <= 120)
                    testo = "Continua ad esercitarti " + user.getUserName() + ". ";
                else if (user.getPunteggio() > 120 && user.getPunteggio() <= 150)
                    testo = "Ci sei quasi " + user.getUserName() + ". ";
                else
                    testo = "Complimenti " + user.getUserName() + "!";

                punteggio = "Hai realizzato: " + user.getPunteggio() + "/180 punti.";
            }
        }
        complimento.setText(testo);
        risultati.setText(punteggio);

        complimento.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        risultati.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
    }
}



