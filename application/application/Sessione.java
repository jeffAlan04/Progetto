package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sessione {
    private static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Sessione.username = username;
    }

    // Metodo per verificare se l'utente ha completato il livello facile
    public static boolean hasCompletatoFacile() {
        return verificaCompletamentoLivello("facile");
    }

    // Metodo per verificare se l'utente ha completato il livello intermedio
    public static boolean hasCompletatoIntermedio() {
        return verificaCompletamentoLivello("intermedio");
    }

    // Metodo per verificare se l'utente ha completato un livello specifico
    private static boolean verificaCompletamentoLivello(String livello) {
        if (username == null || username.isEmpty()) return false; // Se non c'è un utente loggato, ritorna falso

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\james\\Desktop\\application (2)\\application(2)\\application\\risultati.txt"))) {
            String linea;
            StringBuilder blocco = new StringBuilder();

            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    // Se incontriamo una riga vuota, significa che è la fine di un blocco di risultati
                    if (isSuccesso(blocco.toString(), livello)) {
                        return true;
                    }
                    blocco.setLength(0); // Pulisci il blocco per il prossimo risultato
                } else {
                    blocco.append(linea).append("\n");
                }
            }

            // Verifica l'ultimo blocco se non è stato seguito da una riga vuota
            if (isSuccesso(blocco.toString(), livello)) {
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Se non trova il livello completato, ritorna falso
    }

    // Metodo per verificare se un blocco di testo contiene un successo per l'utente e il livello specificato
    private static boolean isSuccesso(String blocco, String livello) {
        return blocco.contains("Username: " + username) &&
               blocco.contains("Livello: " + livello) &&
               blocco.contains("Stato: successo");
    }
}
