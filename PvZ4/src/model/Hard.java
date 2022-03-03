package model;

import java.io.IOException;

public class Hard extends Lawn{
    public Hard() throws IOException {
        super();
        this.countSun = 100;
        this.countZombies=50;
        this.countWaves=0;
        this.zombieBirthSpeed = 30;
    }

}
