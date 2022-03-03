package sample;


import data.PlayerRecord;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;

public class Main extends Application {

    private PlayerRecord playerRecord;
    FXMLLoader fxmlLoader;
    //private System FXMLLoader;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //music();

        this.playerRecord = new PlayerRecord("src/data/data.txt");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        AnchorPane menu = fxmlLoader.load();
        MenuController controller = fxmlLoader.getController();
        controller.initData(null,playerRecord);
        Scene menuScene = new Scene(menu, 1280, 720);
        primaryStage.setTitle("Menu - Plants VS Zombies");
        primaryStage.setScene(menuScene);
        primaryStage.show();

    }


    //"src/sound/background.wav"
    public void music() {
        File file = new File("/src/sample/backgroundmusic.wav");
        Media media =  new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

    }

    public static void main(String[] args) {
        //System.out.println(System.getProperty("java.version"));
        //System.out.println(System.getProperty("javafx.version"));
        launch(args);
    }

}
