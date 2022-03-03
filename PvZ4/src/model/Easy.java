package model;

import java.io.IOException;

public class Easy extends Lawn{
    public Easy() throws IOException {
        super();
        this.countSun = 500;
        this.countZombies=5;
        this.countWaves=1;
        this.zombieBirthSpeed = 60;
    }

}
