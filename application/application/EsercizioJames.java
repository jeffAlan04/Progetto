package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class EsercizioJames extends BaseController {
	
	
    // Metodo per cambiare alla scena del dashboard
    @FXML
    public void ScenaDashbord(ActionEvent event) throws IOException {
        SceneManager.cambiaScena("Dashbord.fxml", event);
    }

    // Metodo per cambiare alla scena dell'esercizio facile
    @FXML
    public void ScenaEsercizioFacile(ActionEvent event) throws IOException {
        SceneManager.cambiaScena("EsercizioFacileJ.fxml", event);
    }

    // Metodo per cambiare alla scena dell'esercizio intermedio
    @FXML
    public void ScenaEsercizioIntermedio(ActionEvent event) throws IOException {
        // Controlla se l'utente ha completato il livello facile prima di accedere all'intermedio
        if (Sessione.hasCompletatoFacile()) {
            SceneManager.cambiaScena("EsercizioIntermedioJ.fxml", event);
        } else {
            // Mostra un messaggio di avviso che indica che il livello facile non è stato completato
            SceneManager.mostraMessaggio("Accesso Negato", "Livello Intermedio Bloccato", 
                "Devi completare il livello facile prima di accedere al livello intermedio.");
        }
    }

    // Metodo per cambiare alla scena dell'esercizio difficile
    @FXML
    public void ScenaEsercizioDifficile(ActionEvent event) throws IOException {
        // Controlla se l'utente ha completato il livello intermedio prima di accedere al difficile
        if (Sessione.hasCompletatoIntermedio()) {
            SceneManager.cambiaScena("EsercizioDifficileJ.fxml", event);
        } else {
            // Mostra un messaggio di avviso che indica che il livello intermedio non è stato completato
            SceneManager.mostraMessaggio("Accesso Negato", "Livello Difficile Bloccato", 
                "Devi completare il livello intermedio prima di accedere al livello difficile.");
        }
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
