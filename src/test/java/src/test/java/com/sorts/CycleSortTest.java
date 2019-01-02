package src.test.java.com.sorts;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.sorts.CycleSort;

public class CycleSortTest {

    @Test
    public void cycleSortIntegerTest() {

        CycleSort cycleSort = new CycleSort();

        // Test case for integers
        Integer[] unsortedInt = new Integer[]{5, 1, 7, 0, 2, 9, 6, 3, 4, 8};
        Integer[] sortedInt = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assert.assertArrayEquals(sortedInt, cycleSort.sort(unsortedInt));

        // Test case for floating point numbers
        Float[] unsortedFloat = new Float[]{6.7f, 21.1f, 0.9f, -3.2f, 5.9f, -21.3f};
        Float[] sortedFloat = new Float[]{-21.3f, -3.2f, 0.9f, 5.9f, 6.7f, 21.1f};
        Assert.assertArrayEquals(sortedFloat, cycleSort.sort(unsortedFloat));

        // Test case for characters
        Character[] unsortedChar = new Character[]{'c', 'a', 'b', 'A', 'C', 'B'};
        Character[] sortedChar = new Character[]{'A', 'B', 'C', 'a', 'b', 'c'};
        Assert.assertArrayEquals(sortedChar, cycleSort.sort(unsortedChar));

        // Test case for Strings
        String[] unsortedStr = new String[]{"Edward", "Linus", "David", "Alan", "Dennis", "Robert", "Ken"};
        String[] sortedStr = new String[]{"Alan", "David", "Dennis", "Edward", "Ken", "Linus", "Robert"};
        Assert.assertArrayEquals(sortedStr, cycleSort.sort(unsortedStr));

    }
}
