package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.*;
import com.thealgorithms.dynamicprogramming.TravellingSalesmanBitManipulation;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class TravellingSalesmanBitManipulationTests {

    @Test
    void testForOneElement() {
        int[][] dist = { {0, 20, 42, 35}, {20, 0, 30, 34}, {42, 30, 0, 12}, {35, 34, 12, 0}};
        int s = dist.length;
        int[][] dp = new int[1<<s][s];
        for(int[] i : dp)
            Arrays.fill(i,-1);
        assertEquals(97,TravellingSalesmanBitManipulation.travellingSalesmanBits(dist,1,0,s,dp));

    }
    @Test
    void testForTwoElement() {
        int[][] dist = {{0, 5, 10, 15}, {5, 0, 20, 30}, {10, 20, 0, 35}, {15, 30, 35, 0}};
        int s = dist.length;
        int[][] dp = new int[1<<s][s];
        for(int[] i : dp)
            Arrays.fill(i,-1);
        assertEquals(75,TravellingSalesmanBitManipulation.travellingSalesmanBits(dist,1,0,s,dp));

    }
    @Test
    void testForThreeElement() {
        int[][] dist = {{21,34,45,67},{24,67,87,34},{24,67,45,34},{21,34,65,78}};
        int s = dist.length;
        int[][] dp = new int[1<<s][s];
        for(int[] i : dp)
            Arrays.fill(i,-1);
        assertEquals(137,TravellingSalesmanBitManipulation.travellingSalesmanBits(dist,1,0,s,dp));
    }
}
