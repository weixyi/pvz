package sample;

import data.Player;
import data.PlayerRecord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuController {

    Player currentPlayer;
    PlayerRecord playerRecord;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogIn;

    @FXML
    private Button btnNewUser;

    @FXML
    private MenuItem helpAbout;

    @FXML
    private Button btnStartGame;

    @FXML
    private MenuItem helpContact;

    @FXML
    private Button btnExit;

    @FXML
    private AnchorPane menu;



    @FXML
    void logInGame(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        AnchorPane pane = fxmlLoader.load();
        LogInController controller = fxmlLoader.<LogInController>getController();
        controller.initData(currentPlayer,playerRecord);
        menu.getChildren().setAll(pane);
    }

    @FXML
    void newUser(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newuser.fxml"));
        AnchorPane pane = fxmlLoader.load();
        NewUserController controller = fxmlLoader.getController();
        controller.initData(currentPlayer,playerRecord);
        menu.getChildren().setAll(pane);
    }

    @FXML
    void exit(MouseEvent event) {
        System.out.println("Exit the game");
        System.exit(0);
    }

/*    @FXML
    void startGame(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("game.fxml")));
        AnchorPane pane = fxmlLoader.load();
        GameController controller = fxmlLoader.getController();
        controller.initData(1,null,playerRecord);
        menu.getChildren().setAll(pane);
    }*/

    @FXML
    void chooseLevel(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chooseLevel.fxml"));
        AnchorPane pane = fxmlLoader.load();
        LevelController controller = fxmlLoader.getController();
        controller.initData(currentPlayer,playerRecord);
        menu.getChildren().setAll(pane);
    }

    @FXML
    public void initData(Player player, PlayerRecord playerRecord){
        if (player!=null) System.out.println(player.name);

        this.playerRecord = playerRecord;
        this.currentPlayer = player;
    }


    @FXML
    void initialize() {
        assert btnLogIn != null : "fx:id=\"btnLogIn\" was not injected: check your FXML file 'menu.fxml'.";
        assert helpAbout != null : "fx:id=\"helpAbout\" was not injected: check your FXML file 'menu.fxml'.";
        assert btnStartGame != null : "fx:id=\"btnStartGame\" was not injected: check your FXML file 'menu.fxml'.";
        assert btnNewUser != null : "fx:id=\"btnNewUser\" was not injected: check your FXML file 'menu.fxml'.";
        assert helpContact != null : "fx:id=\"helpContact\" was not injected: check your FXML file 'menu.fxml'.";
        assert menu != null : "fx:id=\"menu\" was not injected: check your FXML file 'menu.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'menu.fxml'.";


    }
}
