package application.alan.medio;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class EsercizioMedioA1 {
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
    private TextArea rispostaUserArea;

    @FXML
    private Button helpButton;

    @FXML
    public void initialize() {

        // Codice predefinito nell'area di testo
        String domanda = "// Inversione di una stringa\n" +
                "public class InvertiStringa {\n" +
                "    public static void main(String[] args) {\n" +
                "        String str = \"ciao\";\n" +
                "        String invertita = \"\";\n" +
                "        for (------------------------) {\n" +
                "            invertita += str.charAt(i);\n" +
                "        }\n" +
                "        System.out.println(\"Stringa invertita: \" + invertita);\n" +
                "    }\n" +
                "}\n";
        codeArea.setText(domanda);

        String rispostaGiusta = "int i = str.length() - 1; i >= 0; i--";
        String prossimoLivello = "fxml/EsercizioMedioA2.fxml";
        int livelloAttuale = 1;
        int esercizioAttuale = 0;

        ModelloEsercizioMedio modelloEsercizioMedio = new ModelloEsercizioMedio();
        modelloEsercizioMedio.initialize(rispostaGiusta,
                verifyButton, feedbackText, esciButton,
                nextButton, prossimoLivello, rispostaUserArea, livelloAttuale,
                esercizioAttuale, helpButton);
    }
}
