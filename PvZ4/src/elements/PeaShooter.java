package elements;

import java.util.LinkedList;
import java.util.List;

public class PeaShooter extends Plant{
    //public static final int plantIndex = 1;
    public static final int cost = 100;
    public static final int peaSpeed = 15; //shoot a pea every 15 frame



    public PeaShooter(int positionX, int positionY,int row, int col) {
        super(positionX, positionY, "/images/gif_peashooter.gif", 60,  60, 8,100,row, col);
    }

    public List<Integer> attackRange() {
        List<Integer> rtn = new LinkedList<>();
        for (int i = this.getCol(); i <= 8; i++)
            rtn.add(this.getRow()*9+ i);
        return rtn;
    }

    public int getCost() {
        return cost;
    }

    public int getPlantIndex() {
        return 1;
    }

    public void action() {

    }
}
