package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Progressi {
    public String verificaCheckPoint(String username) {
        String nomeFile = "";
        // Verifica se le credenziali sono presenti nel file "users.txt"
        try (BufferedReader reader = new BufferedReader(new FileReader("progressi.json"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] dati = linea.split(":");
                if (dati[0].equals(username)) {
                    String progresso = dati[1];
                    nomeFile = ottieniUltimoCheckPoint(progresso);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nomeFile;
    }

    public String ottieniUltimoCheckPoint(String progresso){
        String[] checkPoint = progresso.split("-");
        String result = "";

        if ("1".equals(checkPoint[0])){
            if ("1".equals(checkPoint[1])){
               result = "EsercizioFacileA.fxml";
            }
            else if ("2".equals(checkPoint[1])){
                result = "EsercizioFacileA2.fxml";
            }
            else if ("3".equals(checkPoint[1])){
                result = "EsercizioFacileA3.fxml";
            }
        }
        else if ("2".equals(checkPoint[0])){
            if ("1".equals(checkPoint[1])){
                result = "EsercizioMedioA1.fxml";
            }
            else if ("2".equals(checkPoint[1])){
                result = "EsercizioMedioA2.fxml";
            }
            else if ("3".equals(checkPoint[1])){
                result = "EsercizioMedioA3.fxml";
            }
        }
        else if ("3".equals(checkPoint[0])){
            if ("1".equals(checkPoint[1])){
                result = "EsercizioDifficileA.fxml";
            }
            else if ("2".equals(checkPoint[1])){
                result = "EsercizioDifficileA2.fxml";
            }
            else if ("3".equals(checkPoint[1])){
                result = "EsercizioDifficileA3.fxml";
            }
        }
        return result;
    }
}
