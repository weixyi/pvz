package sample;


import data.Player;
import data.PlayerRecord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultController {

    Player currentPlayer;
    PlayerRecord playerRecord;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane result;

    @FXML
    private TextField tfGameResults;

    @FXML
    private MenuItem helpAbout;

    @FXML
    private MenuItem helpContact;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnExit;


    @FXML
    void exit(MouseEvent event) {
        System.out.println("Exit the game");
        System.exit(0);
    }

    @FXML
    void menu(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        AnchorPane pane = fxmlLoader.load();
        MenuController controller = fxmlLoader.<MenuController>getController();
        controller.initData(currentPlayer,playerRecord);
        result.getChildren().setAll(pane);
    }

    @FXML
    public void initData(boolean win, Player player, PlayerRecord playerRecord){
        this.currentPlayer = player;
        this.playerRecord = playerRecord;

        if (win){
            tfGameResults.setText("Congratulations! You have won the game.");
        }else{
            tfGameResults.setText("Better luck next time! Well Play!");
        }
    }

    @FXML
    void initialize() {

        assert result != null : "fx:id=\"result\" was not injected: check your FXML file 'result.fxml'.";
        assert tfGameResults != null : "fx:id=\"tfGameResults\" was not injected: check your FXML file 'result.fxml'.";
        assert helpAbout != null : "fx:id=\"helpAbout\" was not injected: check your FXML file 'result.fxml'.";
        assert helpContact != null : "fx:id=\"helpContact\" was not injected: check your FXML file 'result.fxml'.";
        assert btnMenu != null : "fx:id=\"btnMenu\" was not injected: check your FXML file 'result.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'result.fxml'.";

    }
}
