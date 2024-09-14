package application.alan.facile;

import application.alan.CostruzioneScenaAlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

public class EsercizioFacileA1 {

    @FXML
    private Text codeArea;

    @FXML
    private Button verifyButton;

    @FXML
    private Text feedbackText;

    @FXML
    private Button esciButton;

    @FXML
    private Button nextButton;

    @FXML
    private RadioButton r1;

    @FXML
    private RadioButton r2;

    @FXML
    private RadioButton r3;

    @FXML
    private Button helpButton;


    @FXML
    public void initialize() {
        // Codice predefinito nell'area di testo
        String testoDomanda = "// Il programma deve calcolare la somma dei numeri pari da 1 a 10.\n" +
                "public class SommaPari {\n" +
                "    public static void main(String[] args) {\n" +
                "        int somma = 0;\n" +
                "        for (int i = 1; i <= 10; i++) {\n" +
                "            if (i % 2 != 0) {\n" +
                "                somma += i;\n" +
                "            }\n" +
                "        }\n" +
                "        System.out.println(\"La somma è: \" + somma);\n" +
                "    }\n" +
                "}";
        codeArea.setText(testoDomanda);

        String prossimoLivello = "fxml/EsercizioFacileA2.fxml";
        int livelloAttuale = 0;
        int esercizioAttuale = 0;

        ModelloEsercizioFacile modelloEsercizioFacile = new ModelloEsercizioFacile();
        modelloEsercizioFacile.initialize(r1, r2, r3, 3,
                verifyButton, feedbackText, esciButton,
                nextButton, prossimoLivello, livelloAttuale, esercizioAttuale, helpButton);

        CostruzioneScenaAlan costruzioneScenaAlan = new CostruzioneScenaAlan();
        // costruzioneScenaAlan.startProgress();
    }

    @FXML
    private void controllaRisposta(ActionEvent event) {
    }
}
