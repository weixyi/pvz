package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.GameRecord;
import data.Player;
import data.PlayerRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LogInController {

    PlayerRecord playerRecord;
    Player currentPlayer;
    private final ObservableList<GameRecord> cellData = FXCollections.observableArrayList();


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
    private AnchorPane login;

    @FXML
    private TableView<GameRecord> tableRecord;

    @FXML
    private TableColumn<GameRecord, Integer> tcDuration;

    @FXML
    private TableColumn<GameRecord, String> tcLevel;

    @FXML
    private TableColumn<GameRecord, String> tcPlayer;

    @FXML
    private TableColumn<GameRecord, String> tcResult;

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfPW;

    @FXML
    void createNewPlayer(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newuser.fxml"));
        AnchorPane pane = fxmlLoader.load();
        NewUserController controller = fxmlLoader.getController();
        controller.initData(currentPlayer,playerRecord);
        login.getChildren().setAll(pane);
    }

    @FXML
    void exit(MouseEvent event) {
        System.out.println("Exit the game");
        System.exit(0);
    }

    @FXML
    void login(MouseEvent event) {
        String name = tfID.getText();
        String pw = tfPW.getText();
        if (this.playerRecord.record.contains(name)){
            if (this.playerRecord.record.get(name).password.equals(pw)){
                this.currentPlayer = new Player(name, pw);
                this.tfID.setText("Log In Successfully");
                this.tfPW.setText("Log In Successfully");
            }else{
                this.tfID.setText("Wrong Password");
                this.tfPW.setText("Please Exit or Create a New Account");
            }
        }else{
            this.tfID.setText("Account Does Not Exist");
            this.tfPW.setText("Please Exit or Create a New Account");
        }
    }

    @FXML
    void menu(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        AnchorPane pane = fxmlLoader.load();
        MenuController controller = fxmlLoader.<MenuController>getController();
        controller.initData(currentPlayer,playerRecord);
        login.getChildren().setAll(pane);
    }


    @FXML
    public void initData(Player player,PlayerRecord playerRecord){
        this.playerRecord = playerRecord;
        this.currentPlayer = player;

        //System.out.println(playerRecord.record.keySet());

        for (String p:playerRecord.record.keySet()){
            Player currentPlayer = playerRecord.record.get(p);
            cellData.addAll(currentPlayer.gameRecordList);
        }
        tcPlayer.setCellValueFactory(cellData->cellData.getValue().getName());
        tcDuration.setCellValueFactory(cellData->cellData.getValue().getTime().asObject());
        tcLevel.setCellValueFactory(cellData -> cellData.getValue().getLevel());
        tcResult.setCellValueFactory(cellData -> cellData.getValue().getWin());
        tableRecord.setItems(cellData);
    }

    @FXML
    void initialize() {
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'login.fxml'.";
        assert btnLogIn != null : "fx:id=\"btnLogIn\" was not injected: check your FXML file 'login.fxml'.";
        assert btnMenu != null : "fx:id=\"btnMenu\" was not injected: check your FXML file 'login.fxml'.";
        assert btnNewPlayer != null : "fx:id=\"btnNewPlayer\" was not injected: check your FXML file 'login.fxml'.";
        assert helpAbout != null : "fx:id=\"helpAbout\" was not injected: check your FXML file 'login.fxml'.";
        assert helpContact != null : "fx:id=\"helpContact\" was not injected: check your FXML file 'login.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'login.fxml'.";
        assert tableRecord != null : "fx:id=\"tableRecord\" was not injected: check your FXML file 'login.fxml'.";
        assert tfID != null : "fx:id=\"tfID\" was not injected: check your FXML file 'login.fxml'.";
        assert tfPW != null : "fx:id=\"tfPW\" was not injected: check your FXML file 'login.fxml'.";

    }

}
