package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.Player;
import data.PlayerRecord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class NewUserController {

    PlayerRecord playerRecord;
    Player currentPlayer;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnLogIn;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnNewPlayer;

    @FXML
    private MenuItem helpAbout;

    @FXML
    private MenuItem helpContact;

    @FXML
    private AnchorPane newuser;

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfPW;

    @FXML
    void createNewPlayer(MouseEvent event) {
        String name = tfID.getText();
        String pw = tfPW.getText();
        this.currentPlayer = new Player(name, pw);
    }

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
        newuser.getChildren().setAll(pane);
    }

    @FXML
    public void initData(Player player, PlayerRecord playerRecord){
        this.playerRecord = playerRecord;
        this.currentPlayer = player;
    }

    @FXML
    void initialize() {

        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'login.fxml'.";
        assert btnLogIn != null : "fx:id=\"btnLogIn\" was not injected: check your FXML file 'login.fxml'.";
        assert btnMenu != null : "fx:id=\"btnMenu\" was not injected: check your FXML file 'login.fxml'.";
        assert btnNewPlayer != null : "fx:id=\"btnNewPlayer\" was not injected: check your FXML file 'login.fxml'.";
        assert helpAbout != null : "fx:id=\"helpAbout\" was not injected: check your FXML file 'login.fxml'.";
        assert helpContact != null : "fx:id=\"helpContact\" was not injected: check your FXML file 'login.fxml'.";
        assert newuser != null : "fx:id=\"login\" was not injected: check your FXML file 'login.fxml'.";
        assert tfID != null : "fx:id=\"tfID\" was not injected: check your FXML file 'login.fxml'.";
        assert tfPW != null : "fx:id=\"tfPW\" was not injected: check your FXML file 'login.fxml'.";

    }
}
