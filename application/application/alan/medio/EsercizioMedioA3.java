package application.alan.medio;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class EsercizioMedioA3 {

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
        String domanda =
                "// La funzione calcola il prodotto di tutti i numeri dispari in una lista di numeri interi.\n" +
                        "// Se non ci sono numeri dispari nella lista, deve restituire 1.\n" +
                        "import java.util.List;\n" +
                        "public class ProdottoNumeriDispari {\n" +
                        "    public static int calcolaProdottoDispari(List<Integer> numeri) {\n" +
                        "        -----------------------;\n" +
                        "        boolean trovatoDispari = false;\n" +
                        "        for (int i = 0; i < numeri.size(); i++) {\n" +
                        "            if (numeri.get(i) % 2 != 0) {\n" +
                        "                prodotto *= numeri.get(i);\n" +
                        "                trovatoDispari = true;\n" +
                        "            }\n" +
                        "        }\n" +
                        "        if (!trovatoDispari) {\n" +
                        "            prodotto = 1;\n" +
                        "        }\n" +
                        "        return prodotto;\n" +
                        "    }\n" +
                        "}";
        codeArea.setText(domanda);

        String rispostaGiusta = "int prodotto = 0";
        String prossimoLivello = "../difficile/fxml/DescrizioneDifficileA.fxml";
        int livelloAttuale = 1;
        int esercizioAttuale = 2;

        ModelloEsercizioMedio modelloEsercizioMedio = new ModelloEsercizioMedio();
        modelloEsercizioMedio.initialize(rispostaGiusta,
                verifyButton, feedbackText, esciButton,
                nextButton, prossimoLivello, rispostaUserArea, livelloAttuale,
                esercizioAttuale, helpButton);
    }

}
