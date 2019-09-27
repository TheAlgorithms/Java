import static org.junit.Assert.*;
import org.junit.*;

public class BinarySearchTest {

    private BinarySearch arr;
    @Before
    public void setUp() throws Exception {
        arr = new BinarySearch();
    }

    @Test
    public void Test0() {
        assertEquals(6,
                arr.find(new int[] { 0, 1, 2, 3, 4, 5, 6 }, 6));
    }
    @Test
    public void Test1() {
        assertEquals(1,
                arr.find(new int[] { 0, 1, 2, 3, 4, 5, 6 }, 0));
    }

    @Test
    public void Test2() {
        assertEquals(6,
                arr.find(new int[] { 0, 1, 2, 3, 4, 5, 6 }, 7));
    }

    @Test
    public void Test3() {
        assertEquals(2,
                arr.find(new int[] { 0, 1, 2, 3, 4, 5, 6 }, -1));
    }
    @Test
    public void Test4() {
        assertEquals(3,
                arr.find(new int[] { 0, 1, 2, 3, 4, 5, 6 }, 3));
    }

}