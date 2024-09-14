package application.alan;

import application.Sessione;
import application.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static application.alan.Constants.FILE_PATH;

public class Modal {

    @FXML
    private Text suggerimento;

    @FXML
    public void initialize() {
        suggerimentoCorretto();
    }

    private void suggerimentoCorretto() {

        String suggerimentoTesto = "";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<User> userList = new ArrayList<>();

        // Read existing users from the JSON file
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type userListType = new TypeToken<ArrayList<User>>() {
                }.getType();
                userList = gson.fromJson(reader, userListType);// Load existing users
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (User user : userList) {
            if (user.getUserName() != null && user.getUserName().equals(Sessione.getUsername())) {
                if (user.getLivello() == 0 && user.getEsercizio() == 0) {
                    suggerimentoTesto = "if (i % 2 == 0) {";

                } else if (user.getLivello() == 0 && user.getEsercizio() == 1) {
                    suggerimentoTesto = "Fattoriale *= i;";

                } else if (user.getLivello() == 0 && user.getEsercizio() == 2) {
                    suggerimentoTesto = "somma += array[i];";

                } else if (user.getLivello() == 1 && user.getEsercizio() == 0) {
                    suggerimentoTesto = "int i = str.length() - 1; i >= 0; i--";

                } else if (user.getLivello() == 1 && user.getEsercizio() == 1) {
                    suggerimentoTesto = "*= i";

                } else if (user.getLivello() == 1 && user.getEsercizio() == 2) {
                    suggerimentoTesto = "int prodotto = 0";

                } else if (user.getLivello() == 2 && user.getEsercizio() == 0) {
                    suggerimentoTesto = "public class MaxMatrice {\n" +
                            "    public static void main(String[] args) {\n" +
                            "        int[][] matrice = {\n" +
                            "            {1, 2, 3},\n" +
                            "            {4, 5, 6},\n" +
                            "            {7, 8, 9}\n" +
                            "        };\n" +
                            "        int max = Integer.MIN_VALUE;\n" +
                            "        for (int i = 0; i < matrice.length; i++) {\n" +
                            "            for (int j = 0; j < matrice[i].length; j++) {\n" +
                            "                if (matrice[i][j] > max) {\n" +
                            "                    max = matrice[i][j];\n" +
                            "                }\n" +
                            "            }\n" +
                            "        }\n" +
                            "        System.out.println(\"Il massimo è: \" + max);\n" +
                            "    }\n" +
                            "}\n";

                } else if (user.getLivello() == 2 && user.getEsercizio() == 1) {
                    suggerimentoTesto = "public class BubbleSort {\n" +
                            "    public static void main(String[] args) {\n" +
                            "        int[] numeri = {5, 2, 9, 1, 5, 6};\n" +
                            "        for (int i = 0; i < numeri.length; i++) {\n" +
                            "            for (int j = 0; j < numeri.length - 1; j++) {\n" +
                            "                if (numeri[j] > numeri[j + 1]) {\n" +
                            "                    int temp = numeri[j];\n" +
                            "                    numeri[j] = numeri[j + 1];\n" +
                            "                    numeri[j + 1] = temp;\n" +
                            "                }\n" +
                            "            }\n" +
                            "        }\n" +
                            "        System.out.println(\"Array ordinato: \");\n" +
                            "        for (int num : numeri) {\n" +
                            "            System.out.print(num + \" \");\n" +
                            "        }\n" +
                            "    }\n" +
                            "}\n";

                } else if (user.getLivello() == 2 && user.getEsercizio() == 2) {
                    suggerimentoTesto = "public class InversioneStringa{\n" +
                            "\tpublic static void main(String[] args){\n" +
                            "\t\tint[] array1 = {1, 2, -4, -5, 10, 12, 8};\n" +
                            "        System.out.println(valoreMinore(array1, 0, 1));\n" +
                            "\t}\n" +
                            "\tpublic static int valoreMinore(int[] arr, int indexMin, int index){\n" +
                            "\t\tif (index == arr.length)\n" +
                            "\t\t\treturn indexMin;\n" +
                            "\t\tif (arr[index] < arr[indexMin] || \n" +
                            "\t\t\t(arr[index] == arr[indexMin]&& index > indexMin)){\n" +
                            "\t\t\tindexMin = index;\n" +
                            "\t\t}\n" +
                            "\t\treturn valoreMinore(arr, indexMin, index + 1);\n" +
                            "\t}\n" +
                            "}";
                }
            }
        }
        suggerimento.setText(suggerimentoTesto);
    }
}
