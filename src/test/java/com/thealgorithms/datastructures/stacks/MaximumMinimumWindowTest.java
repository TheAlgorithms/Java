package com.thealgorithms.datastructures.stacks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;


public class MaximumMinimumWindowTest {
    
    MaximumMinimumWindow MMW;

    @BeforeAll
    public void startUp(){
        MMW = new MaximumMinimumWindow();
        System.out.println("Hello 0");
    }

    @Test
    public void testDummy(){
        assertTrue(true);
        System.out.println("Hello 1");
    }

    @AfterAll
    public void printCoverage(){
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
