package elements;


public class Zombie extends Character {

    protected int step; //walking step length
    protected int damagePower;
    public static final int [] gridMetrics = new int[]{415,500,605,700,795,895,990,1070,1175};

    //default values
    //positionX = 1170;
    //hp = 400

    public Zombie(int col, int row, int positionX,int positionY ) {
        //int positionX, int positionY, String path, int width, int height, int damagePower, int hitPoints,int row, int col
        super(positionX, positionY,"/images/gif_zombie.gif",60,60, 10, 400,row,col);
        this.damagePower = 15;
        this.step = 5;
    }

    public Zombie(int col, int row, int positionX,int positionY , int speed, int damagePower, int step) {
        //int positionX, int positionY, String path, int width, int height, int damagePower, int hitPoints
        super(positionX, positionY,"/images/gif_zombie.gif",60,60, 10, 400,row,col);
        this.col = col;
        this.row = row;
        this.damagePower = damagePower;
        this.step = step;
    }

    public void action(){
        if (this.getPositionX()-step<415){
            this.setPositionX(415);
            return;
        }
        this.setPositionX(this.getPositionX()-step);
    }

    //action without barrier
    public void action(Plant[][] lawn) {
        Plant p = lawn[col][row];
        //if there is plant at the current grid
        if (p!=null){
            p.takeDamage(this.getDamage());
            System.out.println( "plant hp " + p.getHitPoints());
            if (p.getHitPoints()<=0){
                // if the plant has died
                p.disappear();
                lawn[col][row] = null;
            } else return;
        }
        //if there is no plant at the current grid
        if (this.col==0){
            this.action();
            return;
        }
        this.setPositionX(this.getPositionX()-this.step);
        if (Math.abs(this.getPositionX()- gridMetrics[col])>
                Math.abs(this.getPositionX()- gridMetrics[col-1])){
            col--;
        }
    }

}
