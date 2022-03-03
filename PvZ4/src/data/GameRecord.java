package data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A data type designed to stored the result of a game.
 * Dependency: none
 * */
public class GameRecord {

    public final String name;
    public final int win; // 0 lose, 1 win
    public final int time;
    public final int level;

    /**
     * Initializes a new game result(record) object.
     *
     * @param win whether the play has won the match, 0 for lose and 1 for win
     * @param time duration of this game, in seconds
     * @param level difficulty level of this game, 1,2,3 respectively for
     *              easy, medium, and hard
     */
    public GameRecord(String name, int win, int time, int level) {
        this.name = name;
        this.win = win;
        this.time = time;
        this.level = level;
    }

    public SimpleStringProperty getName() {
        return new SimpleStringProperty(name);
    }

    public SimpleStringProperty getWin() {
        if (this.win==0) return new SimpleStringProperty("Win");
        return new SimpleStringProperty("Lose");
    }

    public IntegerProperty getTime() {
        return new SimpleIntegerProperty(time);
    }

    public StringProperty getLevel() {
        if (this.level == 1) return new SimpleStringProperty("Easy");
        if (this.level == 2) return new SimpleStringProperty("Medium");
        return new SimpleStringProperty("Hard");
    }
}
