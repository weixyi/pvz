package test;

import data.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the "data" Project, whether the functions can write in, and store user
 * information correctly.
 * Dependency: GameRecord.java, Player.java, PlayerRecord.java
 * */
public class TestPlayerRecord {
    private PlayerRecord test;

    @Before
    public void setUp() throws IOException {
        PlayerRecord test = new PlayerRecord("src/test/testdata1.txt");
    }


    @Test
    public void testNewWrite() throws IOException {
        //test.writeRecord("src/test/testdata.txt","hekk","dddd",0,120,1);
        //assertEquals(test.record.keySet().size(),1);
    }


}
