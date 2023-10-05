import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class LibrarySortTest {

    @Test
    // Valid test case
    public void librarySortNonDuplicateTest() {
        int[] expectedArray = {1, 2, 3, 4, 5};
        int[] actualArray = {5, 2, 3, 1, 4}; // Input array before sorting
        LibrarySort.librarySort(actualArray);
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    // Valid test case
    public void librarySortDuplicateTest() {
        int[] expectedArray = {2, 2, 2, 5, 7};
        int[] actualArray = {7, 2, 2, 5, 2}; // Input array before sorting
        LibrarySort.librarySort(actualArray);
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    // Valid test case
    public void librarySortEmptyTest() {
        int[] expectedArray = {};
        int[] actualArray = {}; // Empty array
        LibrarySort.librarySort(actualArray);
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    // Valid test case
    public void librarySortNullTest() {
        int[] expectedArray = null;
        assertThrows(NullPointerException.class, () -> {
            LibrarySort.librarySort(expectedArray);
        });
    }

    @Test
    // Valid test case
    public void librarySortNegativeTest() {
        int[] expectedArray = {-5, -4, -3, -2, -1};
        int[] actualArray = {-1, -4, -3, -5, -2}; // Input array before sorting
        LibrarySort.librarySort(actualArray);
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    // Valid test case
    public void librarySortNegativeAndPositiveTest() {
        int[] expectedArray = {-3, -2, -1, 4, 5};
        int[] actualArray = {5, -2, -3, 4, -1}; // Input array before sorting
        LibrarySort.librarySort(actualArray);
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    // Valid test case
    public void allSameTest() {
        int[] expectedArray = {1, 1, 1, 1, 1};
        int[] actualArray = {1, 1, 1, 1, 1}; // Input array before sorting
        LibrarySort.librarySort(actualArray);
        assertArrayEquals(expectedArray, actualArray);
    }
}
