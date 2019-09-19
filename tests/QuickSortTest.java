import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSortTest {
    @Test
    public void sort() {
        QuickSort qs = new QuickSort();
        Integer[] input = {2,3,4,5,6,1};
        Integer[] expect = {1,2,3,4,5,6};

        assertEquals(expect, qs.sort(input));
    }

    @Test
    public void sort1() {
        QuickSort qs = new QuickSort();
        Integer[] input = {0,0,0,0,0,0,0,0,0,0};
        Integer[] expect = {0,0,0,0,0,0,0,0,0,0};

        assertEquals(expect, qs.sort(input));
    }

    @Test
    public void sort2() {
        QuickSort qs = new QuickSort();
        Integer[] input = {1,1,5,5,2,2,3,3,4,4};
        Integer[] expect = {1,1,2,2,3,3,4,4,5,5};

        assertEquals(expect, qs.sort(input));
    }

    @Test
    public void sort3() {
        QuickSort qs = new QuickSort();
        Integer[] input = {-111,-222,555,666,333,444};
        Integer[] expect = {-222,-111,333,444,555,666};

        assertEquals(expect, qs.sort(input));
    }

    @Test
    public void sort4() {
        QuickSort qs = new QuickSort();
        Integer[] input = {a,r,m,n,o,p,q,v};
        Integer[] expect = {a,m,n,o,p,q,r,v};

        assertEquals(expect, qs.sort(input));
    }

    @Test
    public void sort5() {
        QuickSort qs = new QuickSort();
        Integer[] input = {0};
        Integer[] expect = {0};

        assertEquals(expect, qs.sort(input));
    }
}