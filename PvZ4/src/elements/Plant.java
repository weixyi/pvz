package elements;

import java.util.List;

public abstract class Plant extends Character {

    public static int speed = -99;
    public static int waitTime = -99;
    public static int cost = -99;
    public static int plantIndex = -99;

    public Plant(int positionX, int positionY, String path, int width, int height,
                 int damagePower, int hitPoints,int row, int col) {
        super(positionX, positionY, path, width,  height, damagePower, hitPoints,row,col);
    }

    public void setPosition(int x, int y){
        this.setPositionX(x);
        this.setPositionY(y);
    }

    public int getWaitTime(){
        return waitTime;
    }

    public int getCost() {
        return cost;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public abstract int getPlantIndex();

    public abstract List<Integer> attackRange();
}
