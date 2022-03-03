package elements;

import java.util.LinkedList;
import java.util.List;

public class SunFlower extends Plant{
    //public static final int plantIndex = 2;
    public static final int cost = 50;
    public static final int waitingSun = 80;
    private int countWaiting;

    public SunFlower(int positionX, int positionY,int row, int col) {
        super(positionX, positionY, "/images/gif_sunflower.gif", 60,  60, 0,80,row,col);
        this.countWaiting = waitingSun;
    }

    public List<Integer> attackRange() {
        return new LinkedList<>();
    }

    public int getDamage(){
        return 0; // sunflower will not attack
    }

    public int getCost() {
        return cost;
    }

    public int getPlantIndex() {
        return 2;
    }

    public int getCountWaiting(){
        return this.countWaiting;
    }

    public List<Sun> produceSun() {
        List<Sun> sunList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            sunList.add(new Sun(this.getPositionX()+this.random.nextInt(100)-this.random.nextInt(100),
                            this.getPositionY()+this.random.nextInt(100)-this.random.nextInt(100))
            );
        }
        return sunList;
    }

    public void action() {
        if (this.countWaiting>=1)
            this.countWaiting--;
        else this.countWaiting = waitingSun;
    }

}
