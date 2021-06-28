import Searches.IterativeBinarySearch;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;

public class IterativeBinarySearchTest {
    private static IterativeBinarySearch search;

    @BeforeClass

    public static void prepareForalltest() {
        search = new IterativeBinarySearch();
    }

    //array null return -1.
    @Test
    public void testSearch2() {
        Integer [] arr = {};
        int index = search.find(arr, 1);
        assertTrue(index==-1);
    }

    //array != null return index of key
    @Test
    public void testSearch3() {
        Integer [] arr = {0, 1, 2, 3, 4, 5};
        int index = search.find(arr, 2);
        assertTrue(index==2);
    }

    //arry not null but can't find key, return -1
    @Test
    public void testSearch4() {
        Integer [] arr = {3, 5, 2, 6, 8};
        int index = search.find(arr, 4);
        assertTrue(index==-1);
    }
}
