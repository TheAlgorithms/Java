package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/**
 * @author Tabbygray (https://github.com/Tabbygray)
 * @see CocktailShakerSort
 */
public class CocktailShakerSortTest {
    
    private CocktailShakerSort cocktailShakerSort = new CocktailShakerSort();

    @Test
    public void cocktailShakerSortEmptyArray(){
        Integer[] inputArray = {};
        Integer[] outputArray = cocktailShakerSort.sort(inputArray);
        Integer[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void cocktailShakerSortSingleStringElementArray(){
        String[] inputArray = {"Test"};
        String[] outputArray = cocktailShakerSort.sort(inputArray);
        String[] expectedOutput = {"Test"};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void cocktailShakerSortIntegerArray(){
        Integer[] inputArray = { 2, 92, 1, 33, -33, 27, 5, 100, 78, 99, -100};
        Integer[] outputArray = cocktailShakerSort.sort(inputArray);
        Integer[] expectedOutput = { -100, -33, 1, 2, 5, 27, 33, 78, 92, 99, 100};
        assertArrayEquals(outputArray, expectedOutput);
    }

    @Test
    public void cocktailShakerSortStringArray(){
        String[] inputArray = {
            "g3x1",
            "dN62",
            "oMdr",
            "KL2b",
            "JddJ",
            "mvE8",
            "Ej7Q",
            "n7n7",
            "LGTg",
            "2E1w",
        };
        String[] outputArray = cocktailShakerSort.sort(inputArray);
        String[] expectedOutput = {
            "2E1w",
            "Ej7Q",
            "JddJ",
            "KL2b",
            "LGTg",
            "dN62",
            "g3x1",
            "mvE8",
            "n7n7",
            "oMdr",
        };
        assertArrayEquals(outputArray, expectedOutput);
    }
}
