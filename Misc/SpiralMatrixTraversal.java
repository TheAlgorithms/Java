package Misc;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class SpiralMatrixTraversal {
    public static <T> List<T> spiralTraverse(T[][] matrix) {
        List<T> result = new ArrayList<>();

        int startCol = 0;
        int endCol = matrix[0].length - 1;
        int startRow = 0;
        int endRow = matrix.length - 1;
        int count = 0;
        int goal = matrix[0].length * matrix.length;

        while (count < goal) {
            for (int i = startCol; i <= endCol; i++) {
                result.add(matrix[startRow][i]);
                count++;
            }

            for (int i = startRow + 1; i <= endRow; i++) {
                result.add(matrix[i][endCol]);
                count++;
            }

            for (int i = endCol - 1; i >= startCol; i--) {
                if (startRow == endRow) break;
                result.add(matrix[endRow][i]);
                count++;
            }

            for (int i = endRow - 1; i >= startRow + 1; i--) {
                if (startCol == endCol) break;
                result.add(matrix[i][startCol]);
                count++;
            }

            startCol++;
            endCol--;
            startRow++;
            endRow--;
        }

        return result;
    }

    @Test
    public void testEmptyMatrix() {
        Integer[][] testMatrix = {
                {}
        };

        List<Integer> result = spiralTraverse(testMatrix);
        List<Integer> expectedResult = new ArrayList<>();
        assertEquals("Incorrect result", expectedResult, result);
    }

    @Test
    public void testSquareMatrix() {
        Integer[][] testMatrix = {
                {1, 2, 3, 4},
                {12, 13, 14, 5},
                {11, 16, 15, 6},
                {10, 9, 8, 7}
        };

        List<Integer> result = spiralTraverse(testMatrix);
        List<Integer> expectedResult = new ArrayList<>();
        Collections.addAll(expectedResult, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        assertEquals("Incorrect result", expectedResult, result);
    }

    @Test
    public void testSingleMatrix() {
        Integer[][] testMatrix = {
                {7}
        };

        List<Integer> result = spiralTraverse(testMatrix);
        List<Integer> expectedResult = new ArrayList<>();
        Collections.addAll(expectedResult, 7);
        assertEquals("Incorrect result", expectedResult, result);
    }

    @Test
    public void testGreaterColumnMatrix() {
        Integer[][] testMatrix = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {20, 19, 18, 17, 16, 15, 14, 13, 12, 11}
        };

        List<Integer> result = spiralTraverse(testMatrix);
        List<Integer> expectedResult = new ArrayList<>();
        Collections.addAll(expectedResult, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        assertEquals("Incorrect result", expectedResult, result);
    }

    @Test
    public void testGreaterRowMatrix() {
        Integer[][] testMatrix = {
                {1, 2, 3, 4},
                {16, 17, 18, 5},
                {15, 24, 19, 6},
                {14, 23, 20, 7},
                {13, 22, 21, 8},
                {12, 11, 10, 9}
        };

        List<Integer> result = spiralTraverse(testMatrix);
        List<Integer> expectedResult = new ArrayList<>();
        Collections.addAll(expectedResult, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
        assertEquals("Incorrect result", expectedResult, result);
    }
}
