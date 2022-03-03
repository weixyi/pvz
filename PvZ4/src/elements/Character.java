package elements;

import java.util.Random;

public abstract class Character extends Element {
    protected int hitPoints;
    protected int damagePower;

    protected int row;
    protected int col;
    protected int singleIndex;

    protected final Random random;


    public Character(int positionX, int positionY, String path, int width, int height,
                     int damagePower, int hitPoints, int row, int col) {
        super(positionX,positionY,path,width,height);
        this.col = col;
        this.row = row;
        this.singleIndex = row * 9 + col;

        this.damagePower = damagePower;
        this.hitPoints = hitPoints;
        random = new Random();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getSingleIndex(){
        return this.singleIndex;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        if (hitPoints>0){
            this.hitPoints = hitPoints;
            return;
        }
        this.hitPoints = 0;
        this.disappear();
    }

    public void takeDamage(int damage){
        this.setHitPoints(this.hitPoints - damage);
    }

    //randomly attacks btw 5-10
    public int getDamage(){
        return 10 + random.nextInt(this.damagePower+1);
    }


}

