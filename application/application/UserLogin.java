package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserLogin {

    @FXML
    private TextField usernameField; // Campo per inserire l'username
    @FXML
    private PasswordField passwordField; // Campo per inserire la password
    private static final String FILE_PATH = "users.txt";

    @FXML
    public void effettuaLogin(ActionEvent event) {
        // Ottieni i dati inseriti dall'utente
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Verifica se i campi sono vuoti
        if (username.isEmpty() || password.isEmpty()) {
            SceneManager.mostraMessaggio("Errore", "Campi obbligatori vuoti", "Compila tutti i campi richiesti.");
            return;
        }

        // Verifica se le credenziali sono corrette
        boolean loginRiuscito = verificaCredenziali(username, password);

        if (loginRiuscito) {
            SceneManager.mostraMessaggio("Successo", "Login completato", "Benvenuto " + username + "!");

            // Salva l'username nella sessione
            Sessione.setUsername(username);

            // Passa alla dashboard
            ScenaDashbord(event);
        } else {
            SceneManager.mostraMessaggio("Errore", "Login fallito", "Username o password errati.");
        }
    }

    @FXML
    public void ScenaCreaNuovoUtente(ActionEvent event) {
        SceneManager.mostraMessaggio("Info", "Crea Nuovo Utente", "Questa funzione non è ancora implementata.");
    }

    @FXML
    public void ScenaCreaNuovoUtente1(ActionEvent event) {
        SceneManager.cambiaScena("NuovoUtente.fxml", event);
    }

    @FXML
    public void ScenaDashbord(ActionEvent event) {
        SceneManager.cambiaScena("Dashbord.fxml", event);
    }

    @FXML
    public void ScenaChiusura(ActionEvent event) {
        // Gestisce la chiusura dell'applicazione
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Chiusura.confermaChiusura(stage);
    }

    private boolean verificaCredenziali(String username, String password) {
        // Verifica se le credenziali sono presenti nel file "users.txt"
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] credenziali = linea.split(":");
                if (credenziali[0].equals(username) && credenziali[1].equals(password)) {
                    return true; // Credenziali corrette
                }
            }
        } catch (IOException e) {
            SceneManager.mostraMessaggio("Errore", "Errore durante il login", "C'è stato un errore nella lettura delle credenziali.");
            e.printStackTrace();
        }
        return false; // Credenziali errate
    }
}
