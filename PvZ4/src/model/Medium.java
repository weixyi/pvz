package model;

import java.io.IOException;

public class Medium extends Lawn{
    public Medium() throws IOException {
        super();
        this.countSun = 150;
        this.countZombies=30;
        this.countWaves=1;
        this.zombieBirthSpeed = 40;
    }

}
