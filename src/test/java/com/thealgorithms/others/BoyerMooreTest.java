package com.thealgorithms.others;

// Import the JUnit library and the BoyerMoore class
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoyerMooreTest {

    // Create public methods with annotations @Test to test different scenarios of the findmajor method
    @Test
    public void testFindmajorWithEmptyArray() {
        // Create an empty array
        int[] a = {};
        // Call the findmajor method and store the result in a variable
        int result = BoyerMoore.findmajor(a);
        // Check if the result is -1 using assertEquals
        assertEquals(-1, result);
    }

    @Test
    public void testFindmajorWithSingleElementArray() {
        // Create an array with one element
        int[] a = {5};
        // Call the findmajor method and store the result in a variable
        int result = BoyerMoore.findmajor(a);
        // Check if the result is 5 using assertEquals
        assertEquals(5, result);
    }

    @Test
    public void testFindmajorWithMultipleElementsArrayWithMajorityElement() {
        // Create an array with multiple elements and a majority element
        int[] a = {3, 2, 3, 1, 3, 4, 3};
        // Call the findmajor method and store the result in a variable
        int result = BoyerMoore.findmajor(a);
        // Check if the result is 3 using assertEquals
        assertEquals(3, result);
    }

    @Test
    public void testFindmajorWithMultipleElementsArrayWithoutMajorityElement() {
        // Create an array with multiple elements and no majority element
        int[] a = {1, 2, 3, 4, 5};
        // Call the findmajor method and store the result in a variable
        int result = BoyerMoore.findmajor(a);
        // Check if the result is -1 using assertEquals
        assertEquals(-1, result);
    }
}
