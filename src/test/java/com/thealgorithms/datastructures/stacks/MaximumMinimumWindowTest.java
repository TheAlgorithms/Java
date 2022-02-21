package com.thealgorithms.datastructures.stacks;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class MaximumMinimumWindowTest {
    
    public static MaximumMinimumWindow MMW;

    @BeforeAll
    public void startUp(){
        MMW = new MaximumMinimumWindow();
    }

    @Test
    public void testDummy(){
        assertTrue(true);
    }

    @AfterAll
    public static void printCoverage()    {
        float covered = 0;
        for (int i : MaximumMinimumWindow.coverage)  {
            if (i != 0) {
                covered += 1;
            }
        }
        float coverage = covered / MaximumMinimumWindow.coverage.length * 100;
        System.out.println(Arrays.toString(MaximumMinimumWindow.coverage));
        System.out.println("Percentage of branches taken: " + coverage + "%");
    }

}
