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

    //Check a basic test
    @Test
    public void checkBasicCase(){
        int[] arr = new int[]{10, 20, 30, 50, 10, 70, 30};
        int[] target = new int[]{70, 30, 20, 10, 10, 10, 10};
        int[] res = MaximumMinimumWindow.calculateMaxOfMin(arr, arr.length);
        int[] real_res = Arrays.copyOfRange(res, 1, res.length);
        assert Arrays.equals(target, real_res);
    }

    //Check a basic false test
    @Test
    public void checkBasicFakeCase(){
        int[] arr = new int[]{10, 20, 30, 50, 10, 70, 30};
        int[] fake_target = new int[]{10, 20, 30, 50, 10, 70, 30};
        int[] res = MaximumMinimumWindow.calculateMaxOfMin(arr, arr.length);
        int[] real_res = Arrays.copyOfRange(res, 1, res.length);
        boolean answer = Arrays.equals(fake_target, real_res);
        assertFalse(answer);
    }

    //Check an array full of identical numbers
    @Test
    public void checkAnArrayFullOfIdenticalNumbers(){
        int[] arr = new int[]{10, 10, 10, 10, 10, 10, 10};
        int[] target = new int[]{10, 10, 10, 10, 10, 10, 10};
        int[] res = MaximumMinimumWindow.calculateMaxOfMin(arr, arr.length);
        int[] real_res = Arrays.copyOfRange(res, 1, res.length);
        assert Arrays.equals(target, real_res);
    }

    //Check when the window is lower than the array's length
    @Test
    public void checkWindowLower(){
        int[] arr = new int[]{10, 20, 30, 50, 10, 70, 30};
        int[] target = new int[]{20, 10};
        int[] res = MaximumMinimumWindow.calculateMaxOfMin(arr, 2);
        int[] real_res = Arrays.copyOfRange(res, 1, res.length);
        assert Arrays.equals(target, real_res);
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
