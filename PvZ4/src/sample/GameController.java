package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.Player;
import data.PlayerRecord;
import elements.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import model.*;

public class GameController {

    private Player currentPlayer;
    private PlayerRecord playerRecord;
    private int level;

    //Time
    private Time time = new Time(3,0);
    private Lawn lawn;

    private int newPlantIndex;
    private Plant tempPlant;
    private boolean place = false;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane game;

    @FXML
    private GridPane lawnGridPane;

    @FXML
    private TextField tfRemainingTimeHint;

    @FXML
    private TextField tfRemainingTime;

    @FXML
    private MenuItem helpAbout;

    @FXML
    private MenuItem helpContact;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnExit;

    @FXML
    private TextField tfSunHint;

    @FXML
    private TextField tfSun;


    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1.0/ Time.FPS), e->{
                // For every frame passed

                // If no remaining time -- switch to lose page
                if (time.getRemainingSeconds()<=0){
                    try {
                        if (this.currentPlayer!=null)
                            playerRecord.writeRecord(currentPlayer.name, currentPlayer.password, 0, 180, level);
                        lose();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }

                time.oneFramePassed();
                this.tfSun.setText(lawn.getCountSun()+"");
                if (lawn.countRemainingZombies()<=0 && lawn.countCurrentZombies()<=0) {
                    try {
                        if (this.currentPlayer!=null)
                            playerRecord.writeRecord(currentPlayer.name, currentPlayer.password, 1,
                                time.getRemainingSeconds(), level);
                        win();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
                if (lawn.hasArrived()) {
                    try {
                        if (this.currentPlayer!=null)
                            playerRecord.writeRecord(currentPlayer.name, currentPlayer.password, 0,
                                time.getRemainingSeconds(), level);
                        lose();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }

                //the actual game mechanics
                this.lawn.sync(time.getRemainingFrames(),game);
                //display remaining time
                tfRemainingTime.setText(time.getCurrentTime());
                tfSun.setText(""+lawn.getCountSun());

            })
    );


    public void play(){
        // a game lasts for 3 min max
        tfRemainingTime.setText("3:00");
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void win() throws IOException {
        //lawn.clear();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("result.fxml"));
        AnchorPane pane = fxmlLoader.load();
        ResultController controller = fxmlLoader.<ResultController>getController();
        controller.initData(true,currentPlayer,playerRecord);
        game.getChildren().setAll(pane);
    }

    public void lose() throws IOException {
        //lawn.clear();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("result.fxml"));
        AnchorPane pane = fxmlLoader.load();
        ResultController controller = fxmlLoader.<ResultController>getController();
        controller.initData(false,currentPlayer,playerRecord);
        game.getChildren().setAll(pane);
    }

    //four methods to add a plant!
    @FXML
    void selectPeaShooter(MouseEvent event) throws IOException {
        if (this.lawn.getCountSun()<PeaShooter.cost) return;
        this.newPlantIndex = 1;

        Node source = (Node) event.getSource();
        this.tempPlant = new PeaShooter((int) (source.getLayoutX()+ event.getX()),
                (int) (event.getY()+source.getLayoutY()),0,0);

        this.lawn.increaseSun(-tempPlant.getCost());
        this.tempPlant.makeImage(game);
        this.place = true;

    }

    @FXML
    void selectSunFlower(MouseEvent event) throws IOException {
        if (this.lawn.getCountSun()<SunFlower.cost) return;
        this.newPlantIndex = 2;

        Node source = (Node) event.getSource();
        this.tempPlant = new SunFlower((int) (source.getLayoutX()+ event.getX()),
                (int) (event.getY()+source.getLayoutY()),0,0);

        this.lawn.increaseSun(-tempPlant.getCost());
        this.tempPlant.makeImage(game);
        this.place = true;
    }

    @FXML
    void selectWalnut(MouseEvent event) throws IOException {
        if (this.lawn.getCountSun()<Walnut.cost) return;
        this.newPlantIndex = 3;


        Node source = (Node) event.getSource();
        this.tempPlant = new Walnut((int) (source.getLayoutX()+ event.getX()),
                (int) (event.getY()+source.getLayoutY()),0,0);

        this.lawn.increaseSun(-tempPlant.getCost());
        this.tempPlant.makeImage(game);
        this.place = true;
    }

    @FXML
    void selectCherry(MouseEvent event) throws IOException {
        if (this.lawn.getCountSun()<Cherry.cost) return;
        this.newPlantIndex = 4;

        Node source = (Node) event.getSource();
        this.tempPlant = new Cherry((int) (source.getLayoutX()+ event.getX()),
                (int) (event.getY()+source.getLayoutY()),0,0);

        this.lawn.increaseSun(-tempPlant.getCost());
        this.tempPlant.makeImage(game);
        this.place = true;
    }

    //add the selected plant on lawn grid
    //four methods to add a plant!
    @FXML
    void newPlantGrid(MouseEvent event) throws IOException{
        if (tempPlant==null || newPlantIndex == 0)
            return;

        Node source = (Node) event.getSource();
        Integer col  = GridPane.getColumnIndex(source);
        Integer row  = GridPane.getRowIndex(source);
        System.out.println(row+" "+col);

        System.out.println("Scene x "+event.getSceneX());
        System.out.println("Scene y "+event.getSceneY());

        if (this.lawn.isOccupied(col, row)){
            //if the grid is already occupied
            System.out.println("Occupied!");
            System.out.println(this.lawn.lawnGrid[col][row]);
            lawn.increaseSun(tempPlant.getCost());
            tempPlant.disappear();
            tempPlant = null;
            newPlantIndex = 0;
            this.place = false;
            return;
        }

        //source.getLayoutX() + source.getParent().getLayoutX()
        //source.getLayoutY() + source.getParent().getLayoutY())
        this.lawn.addPlant(newPlantIndex, col, row,
                (int) (event.getSceneX())-20, (int) ((event.getSceneY()))-30);
        this.lawn.lawnGrid[col][row].makeImage(game);
        tempPlant.disappear();
        tempPlant = null;
        newPlantIndex = 0;
        this.place = false;
    }

    @FXML
    void collectSun(MouseEvent event) throws IOException {
        if (place){
            newPlantGrid(event);
            return;
        }
        this.lawn.clickLawn((int)event.getSceneX(),(int)event.getSceneY());
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
        game.getChildren().setAll(pane);
    }

    @FXML
    public void initData(int level, Player player, PlayerRecord playerRecord) throws IOException {
        this.playerRecord = playerRecord;
        this.currentPlayer = player;
        this.level = level;

        switch (level) {
            case 2:
                this.lawn = new Medium();
                break;
            case 3:
                this.lawn = new Hard();
                break;
            default: //case 1
                this.lawn = new Easy();
        }
    }


    @FXML
    void initialize() {

        assert tfRemainingTimeHint != null : "fx:id=\"tfRemainingTimeHint\" was not injected: check your FXML file 'game.fxml'.";
        assert game != null : "fx:id=\"game\" was not injected: check your FXML file 'game.fxml'.";
        assert tfRemainingTime != null : "fx:id=\"tfRemainingTime\" was not injected: check your FXML file 'game.fxml'.";
        assert tfSunHint != null : "fx:id=\"tfSunHint\" was not injected: check your FXML file 'game.fxml'.";
        assert lawnGridPane != null : "fx:id=\"lawnGridPane\" was not injected: check your FXML file 'game.fxml'.";
        assert tfSun != null : "fx:id=\"tfSun\" was not injected: check your FXML file 'game.fxml'.";
        assert helpAbout != null : "fx:id=\"helpAbout\" was not injected: check your FXML file 'game.fxml'.";
        assert helpContact != null : "fx:id=\"helpContact\" was not injected: check your FXML file 'game.fxml'.";
        assert btnMenu != null : "fx:id=\"btnMenu\" was not injected: check your FXML file 'game.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'game.fxml'.";

        this.play();

    }
}
