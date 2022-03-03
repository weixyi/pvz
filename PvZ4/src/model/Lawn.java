package model;

import data.PlayerRecord;
import elements.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.*;

public abstract class Lawn {

    //Easy: 200,20,3,1
    //Medium: 150,30,1,2
    //Hard: 100, 50, 0,2

    //count values
    protected int countSun = 99;
    protected int countZombies = 99;
    protected int countWaves = 99;
    protected int zombieBirthSpeed = 10;
    protected int mouseCheckSpeed = 30;
    protected int mouseCheckAccuracy = 1000;

    protected int waveGap;

    //protected int countFrames = 3 * 60 *  Time.fps; //Default duration: 3min

    //other private values
    protected Random random;
    protected boolean arrived;

    public final Plant[][] lawnGrid = new Plant[9][5];
    protected HashMap<Integer, Zombie> currentZombiesMap;
    protected HashMap<Integer, Stack<Pea>> peasMap;
    protected HashMap<Point, Sun> sunsMap;
    protected HashSet<Point> mousePositions;

    //player data
    PlayerRecord playerRecord;

    public Lawn() throws IOException {
        this.currentZombiesMap = new HashMap<>();
        this.peasMap = new HashMap<>();
        this.sunsMap = new HashMap<>();
        this.mousePositions = new HashSet<>();
        this.random = new Random();
        this.playerRecord = new PlayerRecord("src/data/data.txt");

        this.waveGap = (countWaves==0)?999:(3*60*Time.FPS/countWaves);
    }

    public int getCountSun() {
        return countSun;
    }

    public int countRemainingZombies() {
        return countZombies;
    }

    public int countCurrentZombies() {
        return currentZombiesMap.size();
    }

    public void increaseSun(int rise) {
        this.countSun += rise;
    }

    public void addZombie() {
        int randRow = random.nextInt(5); // choose a random row to enter
        int positionX = 1170, positionY;
        switch (randRow) {
            case 1:
                positionY = 260;
                break;
            case 2:
                positionY = 400;
                break;
            case 3:
                positionY = 500;
                break;
            case 4:
                positionY = 600;
                break;
            default: // index = 0
                positionY = 150;
                break;
        }
        Zombie z = new Zombie(8, randRow, positionX, positionY);
        currentZombiesMap.put(z.getSingleIndex(), z);
        countZombies--;
    }

    public boolean isOccupied(int x, int y)
            throws IllegalArgumentException {
        if (x > 8 || x < 0 || y > 5 || y < 0)
            throw new IllegalArgumentException("illegal x y ");
        return lawnGrid[x][y] != null;
    }

    public boolean hasArrived() {
        return arrived;
    }


    public void addPlant(int plantIndex, int x, int y, int positionX, int positionY)
            throws IllegalArgumentException {
        if (x > 8 || x < 0 || y > 5 || y < 0)
            throw new IllegalArgumentException("illegal x y ");
        switch (plantIndex) {
            case 1:
                lawnGrid[x][y] = new PeaShooter(positionX, positionY, y, x);
                Stack<Pea> stack = new Stack<>();
                stack.push(new Pea(positionX + 20, positionY));
                peasMap.put(lawnGrid[x][y].getSingleIndex(), stack);
                break;
            case 2:
                lawnGrid[x][y] = new SunFlower(positionX, positionY, y, x);
                break;
            case 3:
                lawnGrid[x][y] = new Walnut(positionX, positionY, y, x);
                break;
            case 4:
                lawnGrid[x][y] = new Cherry(positionX, positionY, y, x);
                break;
            default:
                System.out.println("Invalid Plant Index");
        }
    }

/*    public void deletePlant(int x, int y) throws IllegalArgumentException {
        if (x>8 || x<0 || y>5 || y<0)
            throw new IllegalArgumentException("illegal x y ");
        if (lawnGrid[x][y] == null)
            throw new IllegalArgumentException("no plant to delete");

        lawnGrid[x][y].disappear();
        lawnGrid[x][y] = null;
    }*/

    public void sync(int countFrames, Pane pane) {
        if (countFrames==600)
            countSun += 50;
        if (countFrames==580)
            countSun += 25;
        if (countFrames==560)
            countSun += 15;



        if (!this.sunsMap.isEmpty()) {
            // update the suns (regardless of mouse clicks)
            Iterator<Map.Entry<Point, Sun>> entryIt = sunsMap.entrySet().iterator();
            // iterate over all the elements
            while (entryIt.hasNext()) {
                Map.Entry<Point, Sun> entry = entryIt.next();
                entry.getValue().oneFramePassed();
                entry.getValue().disappear();
                if (entry.getValue().getCountRemainingframes() <= 0){
                    entryIt.remove();
                    continue;
                }
                if (entry.getValue().isFalling()){
                    if (entry.getValue().getPositionY()>700){
                        entryIt.remove();
                        continue;
                    }
                    entry.getValue().setPositionY(entry.getValue().getPositionY()+entry.getValue().getFallspeed());
                }
                entry.getValue().makeImage(pane);
            }
             // check the mouse clicks
            if (!this.mousePositions.isEmpty() && countFrames%this.mouseCheckSpeed==0){
                for (Point mousePosition:mousePositions){
                    // create an iterator to key set of sunsMap
                    // choose over removeif as we have to increase sun while deleting
                    Iterator<Point> it = sunsMap.keySet().iterator();
                    while (it.hasNext()) {
                        Point key = it.next();
                        Point ref;
                        if (sunsMap.get(key).isFalling())
                            ref = new Point(sunsMap.get(key).getPositionX(),sunsMap.get(key).getPositionY());
                        else ref = key;
                        // check if two Points are closed enough
                        if (ref.distance2(mousePosition)<=this.mouseCheckAccuracy) {
                            it.remove();
                            this.increaseSun(Sun.BOOST);
                        }
                    }
                }
                this.mousePositions = new HashSet<>();
            }
        }

        //add a sun drop when appropriate
        if (countWaves>=0 && countFrames%waveGap==0){
            sunDrop();
        }

        //add a zombie when appropriate
        if (countZombies>=0 && countFrames%zombieBirthSpeed==0){
            this.addZombie();
            countZombies--;
        }

        //let zombies attack plants -- let every zombie action
        Zombie zombieAttacker;
        for (int indexZ : currentZombiesMap.keySet()) {
            zombieAttacker = currentZombiesMap.get(indexZ);
            zombieAttacker.disappear();
            zombieAttacker.action(lawnGrid); //z.action();
            zombieAttacker.makeImage(pane);
            if (Math.abs(zombieAttacker.getPositionX() - 415) <= 20) {
                arrived = true;
                return;
            }
        }

        //check for every plant -- let plant attack zombies
        Plant plant; // let each plant attack
        Zombie zombie; // the zombie to be attacked
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                plant = lawnGrid[i][j];
                if (plant == null)
                    continue;
                //sync the peas
                if (plant.getPlantIndex() == 1) {
                    PeaShooter peaPlant = (PeaShooter) plant;
                    Stack<Pea> stack0 = peasMap.get(peaPlant.getSingleIndex());
                    if (countFrames % 6 == 0) stack0.add(new Pea(plant.getPositionX() + 20, plant.getPositionY()));
                    Stack<Pea> stack1 = new Stack<>();
                    Pea exam;
                    while (!stack0.isEmpty()) {
                        exam = stack0.pop();
                        exam.disappear();
                        if (exam.getPositionX() + Pea.step >= 1175) {
                            continue;
                        }
                        exam.setPositionX(exam.getPositionX() + Pea.step);
                        exam.makeImage(pane);
                        stack1.push(exam);
                    }
                    peasMap.put(peaPlant.getSingleIndex(), stack1);
                }
                //sync the sun flowers
                if (plant.getPlantIndex() == 2) {
                    plant.action();
                    assert plant instanceof SunFlower;
                    SunFlower sfPlant = (SunFlower) plant;
                    if (sfPlant.getCountWaiting() <= 0){
                        for (Sun sun : sfPlant.produceSun())
                            this.sunsMap.put(new Point(sun.getPositionX(), sun.getPositionY()), sun);
                    }
                    plant.action();
                }
                //make attacks
                if (plant.attackRange().isEmpty())
                    continue;
                for (int position : plant.attackRange()) {
                    zombie = currentZombiesMap.get(position);
                    if (zombie == null) continue;
                    zombie.takeDamage(plant.getDamage());
                    if (zombie.getHitPoints() <= 0) {
                        zombie.disappear();
                        currentZombiesMap.remove(position);
                    }
                }
            }
        }

    }

    public void sunDrop () {
        Point ref;
        int positionX;
        Sun neo;
        for (int i = 0; i < 7; i++) {
            positionX = 450+random.nextInt(700);
            ref = new Point(positionX,Sun.UPPERBOUND);
            neo = new Sun(positionX);
            sunsMap.put(ref, neo);
        }
    }

    public void collectSun () {
        // 10 for each sun collected
        this.countSun += Sun.BOOST;
    }

    //-1 lost, 1 win, 0 continuing
    public int result () {
        if (arrived) return -1;
        if (countZombies == 0) return 1;
        return 0;
    }

    public void clickLawn ( int mousePositionX, int mousePositionY ){
        mousePositions.add(new Point(mousePositionX, mousePositionY));
    }



}

