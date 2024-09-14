package application.alan;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import javafx.stage.Stage;

import static application.alan.Constants.FILE_PATH;

public class CostruzioneScenaAlan {

    @FXML
    private Button iniziaBtnFacile;

    @FXML
    private Button iniziaBtnMedio;

    @FXML
    private Button iniziaBtnDifficile;

    private Stage stage;
    private Scene scene;
    private String scena = "";

    @FXML
    public void initialize() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<User> userList = new ArrayList<>();

        // Read existing users from the JSON file
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type userListType = new TypeToken<ArrayList<User>>() {
                }.getType();
                userList = gson.fromJson(reader, userListType);// Load existing users

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (User user : userList) {
            if (user.getUserName() != null && user.getUserName().equals(Sessione.getUsername())) {
                scena = aggiornaScena(user, iniziaBtnFacile, iniziaBtnMedio, iniziaBtnDifficile);
            } else {
                System.out.println("Hello world");
            }
        }
    }

    @FXML
    public void ScenaDashbord(ActionEvent event) throws IOException {
        // Carica il file FXML per la scena di login e cambia la scena
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Dashbord.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void ScenaEsercizioFacile(ActionEvent event) throws IOException {
        // Carica il file FXML per la scena di creazione nuovo utente e cambia la scena
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(this.scena)));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = Objects.requireNonNull(this.getClass().getResource("../application.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    public void ScenaEsercizioIntermedio(ActionEvent event) throws IOException {
        // Carica il file FXML per la scena di creazione nuovo utente e cambia la scena
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(this.scena)));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void ScenaEsercizioDificile(ActionEvent event) throws IOException {
        // Carica il file FXML per la scena di creazione nuovo utente e cambia la scena
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(this.scena)));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void aggiornaProgresso(int livelloAttuale, int esercizioAttuale, boolean suggerimentoAttivato) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<User> userList = new ArrayList<>();

        // Leggo il file progresso.json e ne metto il contenuto dentro List<User>
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

        // index mi serve per aggiornare i dati dello User in posizione index
        int index = 0;
        User userLoggato = new User();

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserName().equals(Sessione.getUsername())) {
                index = i;
                // Ottengo i dati dello User loggato
                userLoggato = userList.get(i);

                // Aggiorno il punteggio in base all'utilizzo del suggerimento
                if (suggerimentoAttivato) userLoggato.setPunteggio(userLoggato.getPunteggio() + 5);
                else userLoggato.setPunteggio(userLoggato.getPunteggio() + 20);

                // Aggiorno i campi necessari di User
                if (esercizioAttuale == 0 && livelloAttuale == 0) {
                    userLoggato.setEsercizio(1);
                    break;
                } else if (esercizioAttuale == 1 && livelloAttuale == 0) {
                    userLoggato.setEsercizio(2);
                    break;
                } else if (esercizioAttuale == 2 && livelloAttuale == 0) {
                    userLoggato.setEsercizio(0);
                    userLoggato.setLivello(1);
                    break;
                } else if (esercizioAttuale == 0 && livelloAttuale == 1) {
                    userLoggato.setEsercizio(1);
                    break;
                } else if (esercizioAttuale == 1 && livelloAttuale == 1) {
                    userLoggato.setEsercizio(2);
                    break;
                } else if (esercizioAttuale == 2 && livelloAttuale == 1) {
                    userLoggato.setEsercizio(0);
                    userLoggato.setLivello(2);
                    break;
                } else if (esercizioAttuale == 0 && livelloAttuale == 2) {
                    userLoggato.setEsercizio(1);
                    break;
                } else if (esercizioAttuale == 1 && livelloAttuale == 2) {
                    userLoggato.setEsercizio(2);
                    break;
                }
            }
        }
        // Aggiorno in userList l'oggetto "userLoggato" in posizione index con i dati che ho aggiornato nel for-loop
        userList.set(index, userLoggato);

        // Sovrascrivo tutto userList nel file progresso.json
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(userList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String aggiornaScena(User user, Button iniziaBtnFacile,
                                Button iniziaBtnMedio, Button iniziaBtnDifficile) {
        int livello = user.getLivello();
        int esercizio = user.getEsercizio();

        String prossimoLivello = "";

        String backgroundGrey = "-fx-background-color: grey;";

        if (livello == 0 && esercizio == 0) {
            iniziaBtnMedio.setDisable(true);
            iniziaBtnMedio.setStyle(backgroundGrey);
            iniziaBtnDifficile.setStyle(backgroundGrey);
            prossimoLivello = "facile/fxml/DescrizioneFacileA.fxml";
        } else if (livello == 0 && esercizio == 1) {
            iniziaBtnMedio.setDisable(true);
            iniziaBtnDifficile.setDisable(true);
            iniziaBtnMedio.setStyle(backgroundGrey);
            iniziaBtnDifficile.setStyle(backgroundGrey);
            prossimoLivello = "facile/fxml/EsercizioFacileA2.fxml";
        } else if (livello == 0 && esercizio == 2) {
            iniziaBtnMedio.setDisable(true);
            iniziaBtnDifficile.setDisable(true);
            iniziaBtnMedio.setStyle(backgroundGrey);
            iniziaBtnDifficile.setStyle(backgroundGrey);
            prossimoLivello = "facile/fxml/EsercizioFacileA3.fxml";
        } else if (livello == 1 && esercizio == 0) {
            iniziaBtnFacile.setDisable(true);
            iniziaBtnDifficile.setDisable(true);
            iniziaBtnMedio.setStyle(backgroundGrey);
            iniziaBtnDifficile.setStyle(backgroundGrey);
            prossimoLivello = "medio/fxml/DescrizioneMedioA.fxml";
        } else if (livello == 1 && esercizio == 1) {
            iniziaBtnFacile.setDisable(true);
            iniziaBtnDifficile.setDisable(true);
            iniziaBtnFacile.setStyle(backgroundGrey);
            iniziaBtnDifficile.setStyle(backgroundGrey);
            prossimoLivello = "medio/fxml/EsercizioMedioA2.fxml";
        } else if (livello == 1 && esercizio == 2) {
            iniziaBtnFacile.setDisable(true);
            iniziaBtnDifficile.setDisable(true);
            iniziaBtnFacile.setStyle(backgroundGrey);
            iniziaBtnDifficile.setStyle(backgroundGrey);
            prossimoLivello = "medio/fxml/EsercizioMedioA3.fxml";
        } else if (livello == 2 && esercizio == 0) {
            iniziaBtnFacile.setDisable(true);
            iniziaBtnMedio.setDisable(true);
            iniziaBtnFacile.setStyle(backgroundGrey);
            iniziaBtnMedio.setStyle(backgroundGrey);
            prossimoLivello = "difficile/fxml/DescrizioneDifficileA.fxml";
        } else if (livello == 2 && esercizio == 1) {
            iniziaBtnFacile.setDisable(true);
            iniziaBtnMedio.setDisable(true);
            iniziaBtnFacile.setStyle(backgroundGrey);
            iniziaBtnMedio.setStyle(backgroundGrey);
            prossimoLivello = "difficile/fxml/EsercizioDifficileA2.fxml";
        } else if (livello == 2 && esercizio == 2) {
            iniziaBtnFacile.setDisable(true);
            iniziaBtnMedio.setDisable(true);
            iniziaBtnFacile.setStyle(backgroundGrey);
            iniziaBtnMedio.setStyle(backgroundGrey);
            prossimoLivello = "difficile/fxml/EsercizioDifficileA3.fxml";
        } else {
            iniziaBtnFacile.setDisable(true);
            iniziaBtnMedio.setDisable(true);
            iniziaBtnFacile.setStyle(backgroundGrey);
            iniziaBtnMedio.setStyle(backgroundGrey);
            //prossimoLivello = "EsercizioRisultati.fxml";
        }
        return prossimoLivello;
    }
}
