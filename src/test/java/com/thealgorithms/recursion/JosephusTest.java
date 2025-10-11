package com.thealgorithms.recursion;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Test class for Josephus problem implementation
 *
 * @author ritesh-3822
 * @see Josephus
 */
class JosephusTest {

    @Test
    void testNOneAnyK() {
        // single person -> survivor is 1
        assertEquals(1, Josephus.getJosephus(1, 1));
        assertEquals(1, Josephus.getJosephus(1, 5));
        assertEquals(1, Josephus.getJosephus(1, 100));
    }

    @Test
    void testSmallCases() {
        // Known small results
        assertEquals(3, Josephus.getJosephus(5, 2)); // classic: n=5,k=2 -> 3
        assertEquals(4, Josephus.getJosephus(7, 3)); // classic: n=7,k=3 -> 4
        assertEquals(5, Josephus.getJosephus(10, 2)); // n=10,k=2 -> 5
    }

    @Test
    void testLargerKnown() {
        // Known classic example
        assertEquals(28, Josephus.getJosephus(40, 3)); // classic result
    }

    @Test
    void testVariousKValues() {
        assertEquals(1, Josephus.getJosephus(2, 2)); // persons 1..2, k=2 -> survivor 1
        assertEquals(2, Josephus.getJosephus(2, 1)); // k=1 eliminates in order -> last is 2
        assertEquals(4, Josephus.getJosephus(8, 3));
    }

    @Test
    void testLargeNPerformance() {
        // sanity for large n: should complete quickly (recursive depth = n)
        int survivor = Josephus.getJosephus(1000, 7);
        assertTrue(survivor >= 1 && survivor <= 1000);
    }

    @Test
    void testInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> Josephus.getJosephus(0, 3));
        assertThrows(IllegalArgumentException.class, () -> Josephus.getJosephus(-5, 2));
        assertThrows(IllegalArgumentException.class, () -> Josephus.getJosephus(5, 0));
        assertThrows(IllegalArgumentException.class, () -> Josephus.getJosephus(5, -1));
    }

    @Test
    void testPrintJosephus() {
        // Capture System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            Josephus.printJosephus(7, 3); // known survivor 4
            String output = outputStream.toString().trim();
            // output should contain just the survivor number (since printJosephus prints only the number)
            assertEquals("4", output);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testMainMethodContainsDemo() {
        // Capture System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            Josephus.main(new String[] {});
            String output = outputStream.toString();
            assertTrue(output.contains("Josephus problem demo:"));
            assertTrue(output.contains("Survivor (1-based position):"));
        } finally {
            System.setOut(originalOut);
        }
    }
}
