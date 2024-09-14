package application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CreaNuovoUtente {

    @FXML
    private TextField usernameField; // Campo per inserire l'username
    @FXML
    private PasswordField passwordField; // Campo per inserire la password
    private static final String FILE_PATH = "progressi.json"; // JSON file path

    private static final String USERS_FILE_PATH = "users.txt"; // JSON file path


    // Riferimenti agli oggetti di scena
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void salvaUtente() {
        // Ottieni i dati inseriti dall'utente
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Verifica se i campi sono vuoti
        if (username.isEmpty() || password.isEmpty()) {
            mostraMessaggio("Errore", "Campi obbligatori vuoti", "Compila tutti i campi richiesti.");
            return;
        }

        // Salva i dati dell'utente nel file "users.txt"
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(USERS_FILE_PATH, true)))) {
            out.println(username + ":" + password);
            mostraMessaggio("Successo", "Registrazione completata", "L'utente è stato registrato con successo.");


            User user = new User();
            user.setUserName(username);
            user.setPunteggio(0);
            user.setEsercizio(0);
            user.setLivello(0);

            signUpUser(user);
        } catch (IOException e) {
            mostraMessaggio("Errore", "Errore durante la registrazione", "C'è stato un errore nel salvataggio dell'utente.");
            e.printStackTrace();
        }
    }

    @FXML
    public void ScenaLogin(ActionEvent event) throws IOException {
        SceneManager.cambiaScena("Login.fxml", event);
    }

   /* @FXML
    public void ScenaCreaNuovoUtente1(ActionEvent event) throws IOException {
        // Carica il file FXML per la scena di creazione nuovo utente e cambia la scena
        Parent root = FXMLLoader.load(getClass().getResource("CreaNuovoUtente.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/

    // Metodo per chiudere l'applicazione con conferma
    @FXML
    public void ScenaChiusura(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Chiusura.confermaChiusura(stage);
    }

    private void mostraMessaggio(String titolo, String header, String contenuto) {
        // Mostra un messaggio di alert all'utente
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(header);
        alert.setContentText(contenuto);
        alert.showAndWait();
    }


    // Function to sign up a new user and save data to the JSON file
    public static void signUpUser(User newUser) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<User> userList = new ArrayList<>();

        // Read existing users from the JSON file
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type userListType = new TypeToken<ArrayList<User>>() {
                }.getType();
                userList = gson.fromJson(reader, userListType);// Load existing users

                if (userList == null)
                    userList = new ArrayList<>();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Append the new user to the list
        userList.add(newUser);

        // Write the updated list back to the file
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(userList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
