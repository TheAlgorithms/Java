package Sorts;

import org.junit.Test;

import static org.junit.Assert.*;

public class CocktailShakerSortTest {

    @Test
    public void test1() {
        CocktailShakerSort cocktailShakerSort = new CocktailShakerSort();

        Integer[] unsorted = {};
        Integer[] sorted = {};
        assertEquals(sorted, cocktailShakerSort.sort(unsorted));
    }

    @Test
    public void test2() {
        CocktailShakerSort cocktailShakerSort = new CocktailShakerSort();

        Integer[] unsorted = new Integer[]{100, -6, 0, 1, -25, 9, 7, 15};
        Integer[] sorted = new Integer[]{-25, -6, 0, 1, 7, 9, 15, 100};
        assertEquals(sorted, cocktailShakerSort.sort(unsorted));
    }

    @Test
    public void test3() {
        CocktailShakerSort cocktailShakerSort = new CocktailShakerSort();

        Integer[] unsorted = new Integer[]{100, 6, 0, 1, 25, 9, 7, 15};
        Integer[] sorted = new Integer[]{0, 1, 6, 7, 9, 15, 25, 100};
        assertEquals(sorted, cocktailShakerSort.sort(unsorted));
    }

    @Test
    public void test4() {
        CocktailShakerSort cocktailShakerSort = new CocktailShakerSort();

        Character[] unsorted = new Character[] {'a', 'w', 'i', 'l', 'o', 'b', 'v', 'p', 'm', 'a'};
        Character[] sorted = new Character[] {'a', 'a', 'b', 'i', 'l', 'm', 'o', 'p', 'v', 'w'};
        assertEquals(sorted, cocktailShakerSort.sort(unsorted));
    }
}