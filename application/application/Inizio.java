package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Inizio {

    @FXML
    private Text title;

    @FXML
    private Text playText;

    @FXML
    public void initialize(){
        scenaPrima();
    }

    @FXML
    public void ScenaLogin(ActionEvent event) {
        SceneManager.cambiaScena("Login.fxml", event);
    }

    @FXML
    public void ScenaChiusura(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Chiusura.confermaChiusura(stage);
    }

    private  void scenaPrima() {
        String sigla = "P. L. A. Y.";
        String play = "Play, Learn, Asses Yourself";

        title.setText(sigla);
        title.setFont(Font.font("Lucida Console", FontWeight.BOLD, 40));

        playText.setText(play);
        playText.setFont(Font.font("Lucida Console", FontWeight.BOLD, 25));
    }

}