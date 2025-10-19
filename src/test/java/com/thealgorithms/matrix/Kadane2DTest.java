package com.thealgorithms.matrix;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for Kadane2D algorithm
 */
class Kadane2DTest {
    
    @Test
    void testBasicMatrix() {
        int[][] matrix = {
            {1, 2, -1, -4, -20},
            {-8, -3, 4, 2, 1},
            {3, 8, 10, 1, 3},
            {-4, -1, 1, 7, -6}
        };
        
        assertEquals(29, Kadane2D.maxSumRectangle(matrix));
    }
    
    @Test
    void testAllPositive() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        // Sum of all elements
        assertEquals(45, Kadane2D.maxSumRectangle(matrix));
    }
    
    @Test
    void testAllNegative() {
        int[][] matrix = {
            {-1, -2, -3},
            {-4, -5, -6},
            {-7, -8, -9}
        };
        
        // Maximum single element (least negative)
        assertEquals(-1, Kadane2D.maxSumRectangle(matrix));
    }
    
    @Test
    void testSingleElement() {
        int[][] matrix = {{5}};
        assertEquals(5, Kadane2D.maxSumRectangle(matrix));
    }
    
    @Test
    void testSingleRow() {
        int[][] matrix = {{-2, 1, -3, 4, -1, 2, 1, -5, 4}};
        assertEquals(6, Kadane2D.maxSumRectangle(matrix));
    }
    
    @Test
    void testSingleColumn() {
        int[][] matrix = {{-2}, {1}, {-3}, {4}, {-1}, {2}, {1}, {-5}, {4}};
        assertEquals(6, Kadane2D.maxSumRectangle(matrix));
    }
    
    @Test
    void testMixedValues() {
        int[][] matrix = {
            {2, 1, -3, -4},
            {-5, -1, 4, 0},
            {1, 2, 3, -2}
        };
        
        assertEquals(9, Kadane2D.maxSumRectangle(matrix));
    }
    
    @Test
    void testWithCoordinates() {
        int[][] matrix = {
            {1, 2, -1, -4, -20},
            {-8, -3, 4, 2, 1},
            {3, 8, 10, 1, 3},
            {-4, -1, 1, 7, -6}
        };
        
        Kadane2D.Result result = Kadane2D.maxSumRectangleWithCoordinates(matrix);
        assertEquals(29, result.maxSum);
        assertEquals(1, result.topRow);
        assertEquals(1, result.leftCol);
        assertEquals(2, result.bottomRow);
        assertEquals(3, result.rightCol);
    }
    
    @Test
    void testNullMatrix() {
        assertThrows(IllegalArgumentException.class, () -> {
            Kadane2D.maxSumRectangle(null);
        });
    }
    
    @Test
    void testEmptyMatrix() {
        assertThrows(IllegalArgumentException.class, () -> {
            Kadane2D.maxSumRectangle(new int[0][0]);
        });
    }
    
    @Test
    void testZeroMatrix() {
        int[][] matrix = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        
        assertEquals(0, Kadane2D.maxSumRectangle(matrix));
    }
}
