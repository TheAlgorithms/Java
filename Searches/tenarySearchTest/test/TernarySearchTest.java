import org.junit.Test;

import static org.junit.Assert.*;

public class TernarySearchTest {
    TernarySearch t = new TernarySearch();
    public Integer[] arr(){
        Integer a[] = new Integer[4];
        a[0] = 1;
        a[1] = 2;
        a[2] = 3;
        a[3] = 5;
        return a;
    }

    @Test
    public void TS1() {
        Integer a[] = this.arr();
        assertEquals(-1, t.ternarySearch(a, 4, 0, 3));
    }

    @Test
    public void TS2() {
        Integer a[] = this.arr();
        assertEquals(1, t.ternarySearch(a, 2, 0, 3));
    }

    @Test
    public void TS3() {
        Integer a[] = this.arr();
        assertEquals(2, t.ternarySearch(a, 3, 0, 3));
    }

    @Test
    public void TS4() {
        Integer a[] = this.arr();
        assertEquals(0, t.ternarySearch(a, 1, 0, 3));
    }

    @Test
    public void TS5() {
        Integer a[] = this.arr();
        assertEquals(3, t.ternarySearch(a, 5, 0, 3));
    }

}