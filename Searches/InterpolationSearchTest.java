import org.junit.Test;
import Searches.InterpolationSearch;

import static org.junit.Assert.*;

public class InterpolationSearchTest {
    InterpolationSearch t = new InterpolationSearch();

    @Test
    public void InterpolationSearchTest1() {
        int arr[] = new int[]{3, 99, 2, 4};
        assertEquals(-1, t.find(arr, 0));
    }

    @Test
    public void InterpolationSearchTest2() {
        int arr[] = new int[]{1, 2, 2, 5};
        assertEquals(1, t.find(arr, 2));
    }

    @Test
    public void InterpolationSearchTest3() {
        int arr[] = new int[]{3, 100, 2, 4, 0, 1, -1, -5};
        assertEquals(3, t.find(arr, 4));
    }

    @Test
    public void InterpolationSearchTest3() {
        int arr[] = new int[]{3, 104, 2, 104, 0, 1, 104, -5};
        assertEquals(0, t.find(arr, 3));
    }

    @Test
    public void InterpolationSearchTest3() {
        int arr[] = new int[]{3, 3, 3, 3, 3};
        assertEquals(0, t.find(arr, 3));
    }

    @Test
    public void InterpolationSearchTest4() {
        int arr[] = new int[]{17, 4, 20, 22, 8, 98};
        assertEquals(2, t.find(arr, 20));
    }

    @Test
    public void InterpolationSearchTest5() {
        int arr[] = new int[]{0, 1, 2};
        assertEquals(-1, t.find(arr, -1));
    }

    @Test
    public void InterpolationSearchTest6() {
        int arr[] = new int[]{0};
        assertEquals(-1, t.find(arr, 1));
    }

    @Test
    public void InterpolationSearchTest7() {
        int arr[] = new int[]{0};
        assertEquals(0, t.find(arr, 0));
    }

    @Test
    public void InterpolationSearchTest8() {
        int arr[] = new int[]{0, 1, 2, 100, 200};
        assertEquals(4, t.find(arr, 200));
    }

} 