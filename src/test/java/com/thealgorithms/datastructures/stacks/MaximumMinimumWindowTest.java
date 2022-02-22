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

    /* //Check a basic test
    @Test
    public void checkBasicCase(){
        int[] arr = new int[]{10, 20, 30, 50, 10, 70, 30};
        int[] target = new int[]{70, 30, 20, 10, 10, 10, 10};
        int[] res = MaximumMinimumWindow.calculateMaxOfMin(arr, arr.length);
        boolean answer = Arrays.equals(target, res);
        assertTrue(answer);
    } */

    //Check a basic false test
    @Test
    public void checkBasicFakeCase(){
        int[] arr = new int[]{10, 20, 30, 50, 10, 70, 30};
        int[] fake_target = new int[]{10, 20, 30, 50, 10, 70, 30};
        int[] res = MaximumMinimumWindow.calculateMaxOfMin(arr, arr.length);
        boolean answer = Arrays.equals(fake_target, res);
        assertFalse(answer);
    }

   //Check when the array is empty
    @Test
    public void checkArrayEmpty(){
        int[] arr = new int[]{};
        int[] target = new int[]{};
        int[] res = MaximumMinimumWindow.calculateMaxOfMin(arr, arr.length);
        boolean answer = Arrays.equals(target, res);
        assertTrue(answer);
    }

    //Check if the length of the array is equal to the lenght given
    @Test
    public void checkLengthOfTheArrayEqualsLenghtGiven(){
        int[] arr = new int[]{10, 20, 30, 50, 10, 70, 30};
        int[] res = MaximumMinimumWindow.calculateMaxOfMin(arr, 1);
        assertNull(res);
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
        System.out.println("Percentage of branches taken: " + coverage + "% ");
    }

}
