package datastructures.stacks;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;
class NearestElementTest {
    @Test
    void testNearestGreaterToRight_basic() {
        int[] arr = {4, 5, 2, 25};
        int[] expected = {5, 25, 25, -1};
        assertArrayEquals(expected, NearestElement.nearestGreaterToRight(arr));
    }
    @Test
    void testNearestGreaterToLeft_basic() {
        int[] arr = {4, 5, 2, 25};
        int[] expected = {-1, -1, 5, -1};
        assertArrayEquals(expected, NearestElement.nearestGreaterToLeft(arr));
    }
    @Test
    void testNearestSmallerToRight_basic() {
        int[] arr = {4, 5, 2, 25};
        int[] expected = {2, 2, -1, -1};
        assertArrayEquals(expected, NearestElement.nearestSmallerToRight(arr));
    }
    @Test
    void testNearestSmallerToLeft_basic() {
        int[] arr = {4, 5, 2, 25};
        int[] expected = {-1, 4, -1, 2};
        assertArrayEquals(expected, NearestElement.nearestSmallerToLeft(arr));
    }
    @Test
    void testEdgeCases_emptyAndSingle() {
        int[] empty = {};
        int[] single = {10};
        assertArrayEquals(new int[]{}, NearestElement.nearestGreaterToRight(empty));
        assertArrayEquals(new int[]{-1}, NearestElement.nearestGreaterToRight(single));
        assertArrayEquals(new int[]{-1}, NearestElement.nearestGreaterToLeft(single));
        assertArrayEquals(new int[]{-1}, NearestElement.nearestSmallerToRight(single));
        assertArrayEquals(new int[]{-1}, NearestElement.nearestSmallerToLeft(single));
    }
    @Test
    void testDuplicates() {
        int[] arr = {2, 2, 2};
        int[] ngr = {-1, -1, -1}; // strictly greater required
        int[] ngl = {-1, -1, -1};
        int[] nsr = {-1, -1, -1}; // strictly smaller required
        int[] nsl = {-1, -1, -1};
        assertArrayEquals(ngr, NearestElement.nearestGreaterToRight(arr));
        assertArrayEquals(ngl, NearestElement.nearestGreaterToLeft(arr));
        assertArrayEquals(nsr, NearestElement.nearestSmallerToRight(arr));
        assertArrayEquals(nsl, NearestElement.nearestSmallerToLeft(arr));
    }
}
