package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.*;

import com.thealgorithms.backtracking.AllPathsFromSourceToTarget;
import org.junit.jupiter.api.Test;

public class AllPathsFromSourceToTargetTest {

    @Test
    void testForFirstCase() {
        int vertices = 4;
        int a[][] = {{0,1},{0,2},{0,3},{2,0},{2,1},{1,3}};
        int source = 2;
        int destination = 3;
        int num_of_paths = 3;
        assertTrue(AllPathsFromSourceToTarget.all_Paths_From_Source_To_Target (vertices,a,source,destination,num_of_paths));
    }

    @Test
    void testForSecondCase() {
        int vertices = 5;
        int a[][] = {{0,1},{0,2},{0,3},{2,0},{2,1},{1,3},{1,4},{3,4},{2,4}};
        int source = 0;
        int destination = 4;
        int num_of_paths = 6;
        assertTrue(AllPathsFromSourceToTarget.all_Paths_From_Source_To_Target (vertices,a,source,destination,num_of_paths));
    }

    @Test
    void testForThirdCase() {
        int vertices = 6;
        int a[][] = {{1,0},{2,3},{0,4},{1,5},{4,3},{0,2},{0,3},{1,2},{0,5}};
        int source = 1;
        int destination = 5;
        int num_of_paths = 2;
        assertTrue(AllPathsFromSourceToTarget.all_Paths_From_Source_To_Target (vertices,a,source,destination,num_of_paths));
    }

    @Test
    void testForFourthcase() {
        int vertices = 3;
        int a[][] = {{0,1},{0,2},{1,2}};
        int source = 0;
        int destination = 2;
        int num_of_paths = 2;
        assertTrue(AllPathsFromSourceToTarget.all_Paths_From_Source_To_Target (vertices,a,source,destination,num_of_paths));
    }
}
