package application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CreaNuovoUtente {

	 // Campo per inserire l'username
    @FXML
    private TextField Username; 
    // Campo per inserire la password
    @FXML
    private PasswordField Password; 
 // Campo per inserire il nome
    @FXML
    private TextField Nome;
    // Campo per inserire il cognome
    @FXML
    private TextField Cognome;
    private static final String FILE_PATH = "progressi.json"; // JSON file path

    private static final String USERS_FILE_PATH = "users.txt"; // JSON file path

    @FXML
    public void salvaUtente(ActionEvent event) {
        // Ottieni i dati inseriti dall'utente
        String username = Username.getText();
        String password = Password.getText();
        String Nome1 = Nome.getText();
        String Cognome1 = Cognome.getText();

     // Verifica se i campi sono vuoti
        if (username.isEmpty() || password.isEmpty() || Nome1.isEmpty() || Cognome1.isEmpty()) {
            mostraMessaggio("Errore", "Campi obbligatori vuoti", "Compila tutti i campi richiesti.");
            return;
        }
        if (usernameEsiste(username)) {
            mostraMessaggio("Errore", "Username già esistente", "L'username inserito è già in uso. Per favore, scegli un altro username.");
            return;
        }

        // Salva i dati dell'utente nel file "users.txt"
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(USERS_FILE_PATH, true)))) {
        	 out.println(username + ":" + password + "|" + Nome1 + ":" + Cognome1);
        	 mostraMessaggio("Successo", "Registrazione completata", "L'utente è stato registrato con successo.");

            User user = new User();
            user.setUserName(username);
            user.setPunteggio(0);
            user.setEsercizio(0);
            user.setLivello(0);

            signUpUser(user);
             SceneManager.cambiaScena("Login.fxml", event);
        } catch (IOException e) {
            mostraMessaggio("Errore", "Errore durante la registrazione", "C'è stato un errore nel salvataggio dell'utente.");
            e.printStackTrace();
        }
    }
    private boolean usernameEsiste(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("|");
                String usernameCorrente = parts[0].split(":")[0];
                if (usernameCorrente.equals(username)) {
                    return true; // Username trovato
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Username non trovato
    }
    // Metodo per passare alla scena del Login
    @FXML
    public void ScenaLogin(ActionEvent event) {
        SceneManager.cambiaScena("Login.fxml", event);
    }

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
