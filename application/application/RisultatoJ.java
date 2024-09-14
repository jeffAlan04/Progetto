package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class RisultatoJ extends BaseController {

    @FXML
    private Text risultatoText;

    @FXML
    private void initialize() {
        // Controlla se il livello attivo è stato impostato
        if (livelloAttivo == null || livelloAttivo.isEmpty()) {
            risultatoText.setText("Errore: livello non specificato.");
            return;
        }

        // Ottieni i punteggi per il livello attivo
        int punteggio = calcolaPunteggio(livelloAttivo);

        // Componi il messaggio da mostrare in base al punteggio
        String messaggio = creaMessaggio(livelloAttivo, punteggio);

        // Imposta il messaggio nella Text
        risultatoText.setText(messaggio);
    }

    private String creaMessaggio(String livello, int punteggio) {
        StringBuilder messaggio = new StringBuilder();

        switch (livello) {
            case "facile":
                if (punteggio == 0) {
                    messaggio.append("Non hai completato il livello facile.\nHai risposto correttamente a 0 domande su 3.\nTi consiglio di riprovare il livello facile.");
                } else if (punteggio == 1) {
                    messaggio.append("Ci sei quasi!\nHai risposto correttamente a 1 domanda su 3.\nTi consiglio di riprovare il livello facile.");
                } else {
                    messaggio.append("Ottimo lavoro!\nHai risposto correttamente a ").append(punteggio).append(" domande su 3.\nPuoi prosseguire con la difficoltà intermedia.");
                }
                break;

            case "intermedio":
                if (punteggio == 0) {
                    messaggio.append("Non hai completato il livello intermedio.\nHai risposto correttamente a 0 domande su 3.\nTi consiglio di riprovare il livello intermedio.");
                } else if (punteggio == 1) {
                    messaggio.append("Ci sei quasi!\nHai risposto correttamente a 1 domanda su 3.\nTi consiglio di riprovare il livello intermedio.");
                } else {
                    messaggio.append("Ottimo lavoro!\nHai risposto correttamente a ").append(punteggio).append(" domande su 3.\nPuoi prosseguire con la difficoltà difficile.");
                }
                break;

            case "difficile":
                if (punteggio == 0) {
                    messaggio.append("Non hai completato il livello difficile.\nHai risposto correttamente a 0 domande su 3.\nTi consiglio di riprovare il livello difficile.");
                } else if (punteggio == 1) {
                    messaggio.append("Ci sei quasi!\nHai risposto correttamente a 1 domanda su 3.\nTi consiglio di riprovare il livello difficile.");
                } else {
                    messaggio.append("Ottimo lavoro!\nHai risposto correttamente a ").append(punteggio).append(" domande su 3.\nPuoi cambiare esercizio.");
                }
                break;

            default:
                messaggio.append("Livello sconosciuto.");
                break;
        }

        return messaggio.toString();
    }

    @FXML
    public void ScenaEsercizioJames(ActionEvent event) {
        // Determina se il risultato è un successo o un fallimento
        String stato = calcolaPunteggio(livelloAttivo) > 1 ? "successo" : "fallimento";
        
        // Salva i risultati prima di cambiare scena
        salvaRisultato("Pseudocodifica Ordinata", livelloAttivo, calcolaPunteggio(livelloAttivo), stato);

        // Usa SceneManager per cambiare scena verso "EsercizioJames.fxml"
        SceneManager.cambiaScena("EsercizioJames.fxml", event);
    }

    @FXML
    public void ScenaChiusura(ActionEvent event) {
    	// Salva i risultati come "fallimento" se non è stato completato correttamente
        int punteggio = calcolaPunteggio(livelloAttivo);
        String stato = punteggio > 1 ? "successo" : "fallimento";
        salvaRisultato("Pseudocodifica Ordinata", livelloAttivo, punteggio, stato);    	
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Chiusura.confermaChiusura(stage);
    }


}
