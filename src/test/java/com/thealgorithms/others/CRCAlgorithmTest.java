
package com.thealgorithms.others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CRCAlgorithmTest {

    @Test
    void test1(){
        CRCAlgorithm c = new CRCAlgorithm("10010101010100101010010000001010010101010", 10, 0.1);

        c.generateRandomMess();
        c.divideMessageWithP(true);
        c.divideMessageWithP(false);
        c.changeMess();

        assertEquals(c.getWrongMess(), 1);
    }


    @Test
    void test2(){
        CRCAlgorithm c = new CRCAlgorithm("1", 10, 1000000);

        c.divideMessageWithP(true);
        c.divideMessageWithP(true);

        assertEquals(c.getCorrectMess(), 2);
    }

    @Test
    void test3(){
        CRCAlgorithm c = new CRCAlgorithm("1001", 100000, 0.1);

        c.generateRandomMess();

        //test the other function
        assertEquals(c.getWrongMessNotCaught(), 0);
    }

    @Test
    void test4(){
        CRCAlgorithm c = new CRCAlgorithm("1001", 100000, 1000000000);

        c.generateRandomMess();
        c.divideMessageWithP(true);
        c.divideMessageWithP(false);
        c.changeMess();
        c.refactor();

        assertEquals(c.getWrongMessCaught(), 0);
    }

}
