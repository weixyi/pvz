package elements;

import java.util.LinkedList;
import java.util.List;

public class Walnut extends Plant{
    //public static final int plantIndex = 3;
    public static final int cost = 50;


    public Walnut(int positionX, int positionY,int row, int col) {
        //position x, position y, damage power, hit points
        super(positionX, positionY, "/images/gif_walnut.gif", 60,  60, 10,150,row,col);
    }

    public List<Integer> attackRange() {
        List<Integer> rtn = new LinkedList<>();
        if (this.getCol()!=8) rtn.add(this.getRow()*9 + this.getCol()+1);
        return rtn;
    }

    public int getCost() {
        return cost;
    }

    public int getPlantIndex() {
        return 3;
    }

    public void action() {

    }

}
