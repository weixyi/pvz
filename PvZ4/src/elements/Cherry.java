package elements;

import java.util.LinkedList;
import java.util.List;

public class Cherry extends Plant{
    //public static final int plantIndex = 4;
    public static final int cost = 150;

    public Cherry(int positionX, int positionY, int row, int col) {
        super(positionX, positionY, "/images/gif_cherry.gif", 60,  60, 5,90, row, col);
    }

    public List<Integer> attackRange() {
        List<Integer> rtn = new LinkedList<>();
        int x = this.getCol();
        int y = this.getRow();

        if (y > 0) rtn.add((y-1)*9+x);
        if (y < 4) rtn.add((y+1)*9+x);

        if (x < 8){
            rtn.add(y*9 + x+1);
            if (y>0) rtn.add((y-1)*9 + x+1);
            if (y<4) rtn.add((y+1)*9 + x+1);
        }
        if (x > 0){
            rtn.add(y*9 + x-1);
            if (y>0) rtn.add((y-1)*9 + x-1);
            if (y<4) rtn.add((y+1)*9 + x-1);
        }
        return rtn;
    }

    public int getCost() {
        return cost;
    }

    public int getPlantIndex() {
        return 4;
    }

    public void action() {

    }

}
