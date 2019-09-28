import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTest {

    BinarySearch b = new BinarySearch();

    public Integer[] arr(){
        Integer a[] = new Integer[6];
        a[0] = 10;
        a[1] = 20;
        a[2] = 35;
        a[3] = 42;
        a[4] = 51;
        a[5] = 100;
        return a;
    }


    @Test
    public void BST1() {
        Integer[] arr = this.arr();
        assertEquals(-1, b.search(arr, 7, 0, 5));
    }

    @Test
    public void BST2() {
        Integer[] arr = this.arr();
        assertEquals(0, b.search(arr, 10, 0, 5));
    }

    @Test
    public void BST3() {
        Integer[] arr = this.arr();
        assertEquals(4, b.search(arr, 51, 0, 5));
    }

    @Test
    public void BST4() {
        Integer[] arr = this.arr();
        assertEquals(3, b.search(arr, 42, 0, 5));
    }

    @Test
    public void BST5() {
        Integer[] arr = this.arr();
        assertEquals(1, b.search(arr, 20, 0, 5));
    }

    @Test
    public void BST6() {
        Integer[] arr = this.arr();
        assertEquals(5, b.search(arr, 100, 0, 5));
    }

}