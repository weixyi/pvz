package sample;

import data.Player;
import data.PlayerRecord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LevelController {

    Player currentPlayer;
    PlayerRecord playerRecord;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private MenuItem helpAbout;

    @FXML
    private MenuItem helpContact;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnExit;

    @FXML
    private ImageView imgLevelHard;

    @FXML
    private ImageView imgLevelMedium;

    @FXML
    private ImageView imgLevelEasy;

    @FXML
    private AnchorPane chooseLevel;



    @FXML
    void launchLevelEasy(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        AnchorPane pane = fxmlLoader.load();
        GameController gameController = fxmlLoader.<GameController>getController();
        gameController.initData(1,currentPlayer,playerRecord);
        chooseLevel.getChildren().setAll(pane);
    }

    @FXML
    void launchLevelMedium(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        AnchorPane pane = fxmlLoader.load();
        GameController gameController = fxmlLoader.<GameController>getController();
        gameController.initData(2,currentPlayer,playerRecord);
        chooseLevel.getChildren().setAll(pane);
    }

    @FXML
    void launchLevelHard(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        AnchorPane pane = fxmlLoader.load();
        GameController gameController = fxmlLoader.<GameController>getController();
        gameController.initData(3,currentPlayer,playerRecord);
        chooseLevel.getChildren().setAll(pane);
    }

    @FXML
    void exit(MouseEvent event) {
        System.out.println("Exit the game");
        System.exit(0);
    }

    @FXML
    void menu(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        AnchorPane pane = fxmlLoader.load();
        MenuController controller = fxmlLoader.<MenuController>getController();
        controller.initData(currentPlayer,playerRecord);
        chooseLevel.getChildren().setAll(pane);
    }

    @FXML
    public void initData(Player player, PlayerRecord playerRecord){
        this.playerRecord = playerRecord;
        this.currentPlayer = player;
    }

    @FXML
    void initialize() {
        assert imgLevelHard != null : "fx:id=\"imgLevelHard\" was not injected: check your FXML file 'chooseLevel.fxml'.";
        assert imgLevelMedium != null : "fx:id=\"imgLevelMedium\" was not injected: check your FXML file 'chooseLevel.fxml'.";
        assert chooseLevel != null : "fx:id=\"chooseLevel\" was not injected: check your FXML file 'chooseLevel.fxml'.";
        assert helpAbout != null : "fx:id=\"helpAbout\" was not injected: check your FXML file 'chooseLevel.fxml'.";
        assert helpContact != null : "fx:id=\"helpContact\" was not injected: check your FXML file 'chooseLevel.fxml'.";
        assert btnMenu != null : "fx:id=\"btnMenu\" was not injected: check your FXML file 'chooseLevel.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'chooseLevel.fxml'.";
        assert imgLevelEasy != null : "fx:id=\"imgLevelEasy\" was not injected: check your FXML file 'chooseLevel.fxml'.";

    }
}
