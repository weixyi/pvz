package test;

import elements.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test the "public List<Integer> attackRange()" method of a Plant
 * Dependency: Plant, Peashooter,SunFlower, Walnut, Cherry
 * */
public class TestAttackRange {
    private Random random;

    /**
     *  Private helper to test if two lists have equal components.
     *
     *  Note: this method to test for list equality works
     *  because an attack range should contain no repetitive elements.
     *  This method will not work for (Linked)List with repeated elements.*/
    private boolean attackRangeEqual(List<Integer> actual, List<Integer> expected){
        if (actual.size()!=expected.size()) return false;
        HashSet<Integer> testEquality = new HashSet<>();
        testEquality.addAll(actual);
        for (int posiE :expected)
            testEquality.remove(posiE);
        return (testEquality.isEmpty());
    }

    @Before
    public void setUp(){
        random = new Random();
    }

    /**
     * Test whether a PeaShooter returns the appropriate range
     */
    @Test
    public void testPeaShooter() {
        Plant peaShooter;
        //test every grid position(9*5) possible
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                peaShooter = new PeaShooter(0,0,j,i);
                List<Integer> expected = new LinkedList<>();
                for (int k = i ; k < 9; k++)
                    expected.add( j*9 + k );
                assertTrue(attackRangeEqual(peaShooter.attackRange(), expected));
            }
        }
    }

    /**
     * Test whether a SunFlower returns the appropriate range
     */
    @Test
    public void testSunFlower() {
        int row , col;
        Plant sunFlower;
        for (int i = 0; i < 100; i++) {
            row = random.nextInt(5);
            col = random.nextInt(9);
            sunFlower = new SunFlower(0,0,row,col);
            assertTrue(sunFlower.attackRange().isEmpty());
        }
    }

    /**
     * Test whether a Walnut returns the appropriate range
     */
    @Test
    public void testWalnut() {
        Plant walnut;
        //test corner case (right most, col = 8)
        for (int i = 0; i < 5; i++) {
            walnut = new Walnut(0,0,i,8);
            assertTrue(walnut.attackRange().isEmpty());
        }
        //test with col = 0,..., 7
        int row;
        for (int j = 0; j < 7; j++) {
            row = random.nextInt(5);
            walnut = new Walnut(0,0,row,j);
            assertEquals(walnut.attackRange().size(),1);
            int actual = walnut.attackRange().get(0);
            assertEquals(j+row*9+1, actual);
        }
    }

    /**
     * Test whether a Cherry returns the appropriate range
     */
    @Test
    public void testCherry() {
        Plant cherry;
        List<Integer> expected = new LinkedList<>();

        //test corner cases (CORNER)
        cherry = new Cherry(0,0,0,0);
        expected = Arrays.asList(1,9,10);
        assertTrue(attackRangeEqual(cherry.attackRange(), expected));

        cherry = new Cherry(0,0,0,8);
        expected = Arrays.asList(7,16,17);
        assertTrue(attackRangeEqual(cherry.attackRange(), expected));

        cherry = new Cherry(0,0,4,0);
        expected = Arrays.asList(27,28,37);
        assertTrue(attackRangeEqual(cherry.attackRange(), expected));

        cherry = new Cherry(0,0,4,8);
        expected = Arrays.asList(34,35,43);
        assertTrue(attackRangeEqual(cherry.attackRange(), expected));

        int single;

        //test corner cases (Edge)
        for (int i = 1; i < 8; i++) { //top
            cherry = new Cherry(0,0,0,i);
            single = i;
            expected = Arrays.asList(single-1,single+1,single+8,single+9,single+10);
            assertTrue(attackRangeEqual(cherry.attackRange(), expected));
        }

        //test corner cases (Edge)
        for (int i = 1; i < 8; i++) { //bottom
            cherry = new Cherry(0,0,4,i);
            single = 36+i;
            expected = Arrays.asList(single-1,single+1,single-8,single-9,single-10);
            assertTrue(attackRangeEqual(cherry.attackRange(), expected));
        }

        //test corner cases (Edge)
        for (int i = 1; i < 3; i++) { //left
            cherry = new Cherry(0,0,i,0);
            single = i*9;
            expected = Arrays.asList(single+1,single-9,single-8,single+9,single+10);
            assertTrue(attackRangeEqual(cherry.attackRange(), expected));
        }

        //test corner cases (Edge)
        for (int i = 1; i < 3; i++) { //right
            cherry = new Cherry(0,0,i,8);
            single = (i+1)*9-1;
            expected = Arrays.asList(single-1,single-9,single-10,single+9,single+8);
            assertTrue(attackRangeEqual(cherry.attackRange(), expected));
        }

        //test general cases (Edge)
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 3; j++) {
                cherry = new Cherry(0,0,j,i);
                single = j*9+i;
                expected = Arrays.asList(single-1,single+1,
                        single-9,single-10,single-8,single+9,single+8,single+10);
                assertTrue(attackRangeEqual(cherry.attackRange(), expected));
            }
        }

    }




    public static void main(String[] args) {

    }


}
