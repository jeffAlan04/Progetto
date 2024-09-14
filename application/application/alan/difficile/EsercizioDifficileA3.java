package application.alan.difficile;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class EsercizioDifficileA3 {
    @FXML
    private TextArea codeArea;

    @FXML
    private Button verifyButton;

    @FXML
    private Text feedbackText;

    @FXML
    private Button esciButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button helpButton;

    @FXML
    public void initialize() {

        String commento = "// Inversione di una stringa\n";

        // Codice predefinito nell'area di testo
        String initialCode = "public class InversioneStringa{\n" +
                "\tpublic static void main(String[] args){\n" +
                "\t\tint[] array1 = {1, 2, -4, -5, 10, 12, 8};\n" +
                "        System.out.println(valoreMinore(array1, 0, 1));\n" +
                "\t}\n" +
                "\tpublic static int valoreMinore(int[] arr, int indexMin, int index){\n" +
                "\t\tif (index == arr.length)\n" +
                "\t\t\treturn index;\n" +
                "\t\tif (arr[index] < arr[indexMin] || \n" +
                "\t\t\t(arr[index] == arr[indexMin] || index > indexMin)){\n" +
                "\t\t\tindexMin = index;\n" +
                "\t\t}\n" +
                "\t\treturn valoreMinore(arr, indexMin, index + 1);\n" +
                "\t}\n" +
                "}";

        String correctCode = "public class InversioneStringa{\n" +
                "\tpublic static void main(String[] args){\n" +
                "\t\tint[] array1 = {1, 2, -4, -5, 10, 12, 8};\n" +
                "        System.out.println(valoreMinore(array1, 0, 1));\n" +
                "\t}\n" +
                "\tpublic static int valoreMinore(int[] arr, int indexMin, int index){\n" +
                "\t\tif (index == arr.length)\n" +
                "\t\t\treturn indexMin;\n" +
                "\t\tif (arr[index] < arr[indexMin] || \n" +
                "\t\t\t(arr[index] == arr[indexMin] && index > indexMin)){\n" +
                "\t\t\tindexMin = index;\n" +
                "\t\t}\n" +
                "\t\treturn valoreMinore(arr, indexMin, index + 1);\n" +
                "\t}\n" +
                "}";

        codeArea.appendText(commento);
        codeArea.appendText(initialCode);

        String prossimoLivello = "../RisultatiA.fxml";

        int livelloAttuale = 2;
        int esercizioAttuale = 2;
        ModelloEsercizioDifficile modelloEsercizioDifficile = new ModelloEsercizioDifficile();
        modelloEsercizioDifficile.initialize(correctCode, codeArea,
                verifyButton, feedbackText, esciButton,
                nextButton, prossimoLivello, livelloAttuale, esercizioAttuale, helpButton);
        // Ascolta il pulsante di verifica
    }
}
