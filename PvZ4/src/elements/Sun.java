package elements;

import java.util.Random;

public class Sun extends Element{
    private boolean falling;
    private final Random random;
    private int countRemainingframes;
    private int fallspeed;

    public static final int UPPERBOUND = 50;
    public static final int LOWERBOUND = 620;
    public static final int BOOST = 20;
    public static final int DURATION = 50; //last for 20 frames



    public Sun(int positionX) {
        super(positionX,UPPERBOUND,"/images/sun.png",35,35);
        this.random = new Random();
        this.falling = true;
        this.fallspeed = 0;
        this.countRemainingframes = DURATION;
    }

    public Sun(int positionX, int positionY) {
        super(positionX,positionY,"/images/sun.png",35,35);
        this.random = new Random();
        this.falling = false;
        this.fallspeed = 0;
        this.countRemainingframes = DURATION;
    }

    public void action() {

    }


    public boolean isFalling(){
        return falling;
    }

    public int getCountRemainingframes() {
        return countRemainingframes;
    }

    public void oneFramePassed(){
        this.countRemainingframes--;
    }

    public int getSun(){
        return 5 + random.nextInt(BOOST+1);
    }

    public int getFallspeed(){
        return this.fallspeed+random.nextInt(15);
    }

}
