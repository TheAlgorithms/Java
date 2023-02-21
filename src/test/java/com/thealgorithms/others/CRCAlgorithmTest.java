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
        CRCAlgorithm c = new CRCAlgorithm("1001", 10, 0.1);

        c.generateRandomMess();
        c.divideMessageWithP(true);
        c.divideMessageWithP(false);
        c.changeMess();

        assertEquals(c.getCorrectMess(), 1);
    }

    @Test
    void test3(){
        CRCAlgorithm c = new CRCAlgorithm("1001", 10, 0.1);

        c.generateRandomMess();
        c.divideMessageWithP(true);
        c.divideMessageWithP(false);
        c.changeMess();

        //test the other function
        //assertEquals(c.divideMessageWithP(false), "10");
    }

    @Test
    void test4(){
        CRCAlgorithm c = new CRCAlgorithm("1001", 10, 0.1);

        c.generateRandomMess();
        c.divideMessageWithP(true);
        c.divideMessageWithP(false);
        c.changeMess();

        //test some other function here too
    }

}
