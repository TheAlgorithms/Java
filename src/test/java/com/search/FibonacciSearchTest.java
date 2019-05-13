package src.test.java.com.search;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.search.FibonacciSearch;

public class FibonacciSearchTest {
    @Test
    public void testFibonacciSearch() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();

        Integer[] arr = {11, 14, 23, 32, 36, 40, 54, 69};
        int x = 54;
        int index = fibonacciSearch.findIndex(arr, x);
        Assert.assertEquals("Incorrect index", 6, index);

        Integer[] arrTwo = {-400, -283, -180, -160, -129, -120, -30};
        x = -120;
        index = fibonacciSearch.findIndex(arrTwo, x);
        Assert.assertEquals("Incorrect index", 5, index);

        String[] arrString = {"101", "122", "136", "165", "225", "351", "458"};
        String stringX = "136";
        index = fibonacciSearch.findIndex(arrString, stringX);
        Assert.assertEquals("Incorrect index", 2, index);

        String[] arrThree = {};
        Assert.assertEquals("Incorrect index", -1, fibonacciSearch.findIndex(arrThree, ""));
    }
}
