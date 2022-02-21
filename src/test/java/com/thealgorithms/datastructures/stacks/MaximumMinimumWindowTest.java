package com.thealgorithms.datastructures.stacks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;


public class MaximumMinimumWindowTest {
    
    static MaximumMinimumWindow MMW;

    @BeforeAll
    static void startUp(){
        MMW = new MaximumMinimumWindow();
    }

    @Test
    public void testDummy(){
        assertTrue(true);
    }

    @AfterAll
    static void printCoverage(){
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
