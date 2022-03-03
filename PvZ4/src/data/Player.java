package data;

import java.util.LinkedList;
import java.util.List;

/**
 * A data type designed to store a user and his game history.
 * Dependency: GameRecord.java
 * */
public class Player {
    public String name;
    public String password;
    public List<GameRecord> gameRecordList;

    /**
     * Initializes a new player.
     *
     * @param name name of the player
     * @param password password of the player, used to verify player identity
     */
    public Player(String name,String password){
        this.name = name;
        this.password = password;
        this.gameRecordList = new LinkedList<>();
    }

    /**
     * Add a new game result(record) under the player.
     *
     * @param win whether the play has won the match, 0 for lose and 1 for win
     * @param time duration of this game, in seconds
     * @param level difficulty level of this game, 1,2,3 respectively for
     *              easy, medium, and hard
     */
    public void addGame(String win, String time, String level){
        this.gameRecordList.add(new GameRecord(this.name, Integer.parseInt(win),Integer.parseInt(time),Integer.parseInt(level)));
    }


    public void pauseGame(){

    }

    public void resumeGame(){

    }

}
