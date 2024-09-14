package application;

public class PunteggioManager {

    private static PunteggioManager instance;
    
    private int punteggioFacile = 0;
    private int punteggioIntermedio = 0;
    private int punteggioDifficile = 0;

    private PunteggioManager() {
        // Costruttore privato per impedire l'istanza esterna
    }

    public static PunteggioManager getInstance() {
        if (instance == null) {
            instance = new PunteggioManager();
        }
        return instance;
    }

    public int getPunteggioFacile() {
        return punteggioFacile;
    }

    public void aggiungiPunteggioFacile(int valore) {
        this.punteggioFacile += valore;
    }

    public int getPunteggioIntermedio() {
        return punteggioIntermedio;
    }

    public void aggiungiPunteggioIntermedio(int valore) {
        this.punteggioIntermedio += valore;
    }

    public int getPunteggioDifficile() {
        return punteggioDifficile;
    }

    public void aggiungiPunteggioDifficile(int valore) {
        this.punteggioDifficile += valore;
    }
}
