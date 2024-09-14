package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Informazioni extends BaseController{
	
	@FXML
    private Text nomeUtenteText; // Un elemento Text nella dashboard per mostrare il nome utente
	
	@FXML
    private ScrollPane risultatiScrollPane;

	@FXML
	private Text risultatiText; // Usando solo Text

    // Metodo chiamato ogni volta che si carica la scena
    public void initialize() {
        String username = Sessione.getUsername(); // Recupera l'username dalla sessione
        if (username != null) {
            nomeUtenteText.setText("Ecco tutte le informazioni sugli esercizi di " + username);
            risultatiText.setText(leggiRisultati(username));
            // Imposta la dimensione preferita del testo per la visualizzazione corretta
            risultatiText.setWrappingWidth(risultatiScrollPane.getWidth() - 20); // Imposta il wrapping width per adattarsi alla larghezza del ScrollPane
        }
    }
    
    // Metodo per leggere i risultati dal file
    private String leggiRisultati(String username) {
        StringBuilder risultati = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\james\\Desktop\\application (2)\\application(2)\\application\\risultati.txt"))) {
            String linea;
            boolean utenteTrovato = false;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains("Username: " + username)) {
                    utenteTrovato = true;
                    risultati.append(linea).append("\n");
                    while ((linea = reader.readLine()) != null && !linea.equals("====================================")) {
                        risultati.append(linea).append("\n");
                    }
                    risultati.append("====================================\n");
                } else if (utenteTrovato) {
                    // Interrompe la lettura se incontra una nuova sezione per un altro utente
                    if (linea.equals("====================================")) {
                        utenteTrovato = false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Errore nella lettura dei risultati.";
        }
        return risultati.length() > 0 ? risultati.toString() : "Nessun risultato trovato per l'utente " + username;
    }
	
 // Metodo per cambiare alla scena del dashboard
    @FXML
    public void ScenaDashbord(ActionEvent event) {
        SceneManager.cambiaScena("Dashbord.fxml", event);	
    }
	
    // Metodo per gestire la chiusura della finestra
	@FXML
    public void ScenaChiusura(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        boolean conferma = Chiusura.confermaChiusuraDati(stage);
        if (conferma) {
        	chiusuraApplicazione();
        	}
    	}
}
