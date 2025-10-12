package com.thealgorithms.recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Test class for DiceThrower
 *
 * @author BEASTSHRIRAM
 */
class DiceThrowerTest {

    @Test
    void testTargetZero() {
        List<String> result = DiceThrower.getDiceCombinations(0);
        assertEquals(1, result.size());
        assertEquals("", result.get(0));
    }

    @Test
    void testTargetOne() {
        List<String> result = DiceThrower.getDiceCombinations(1);
        assertEquals(1, result.size());
        assertEquals("1", result.get(0));
    }

    @Test
    void testTargetTwo() {
        List<String> result = DiceThrower.getDiceCombinations(2);
        assertEquals(2, result.size());
        assertTrue(result.contains("11"));
        assertTrue(result.contains("2"));
    }

    @Test
    void testTargetThree() {
        List<String> result = DiceThrower.getDiceCombinations(3);
        assertEquals(4, result.size());
        assertTrue(result.contains("111"));
        assertTrue(result.contains("12"));
        assertTrue(result.contains("21"));
        assertTrue(result.contains("3"));
    }

    @Test
    void testTargetFour() {
        List<String> result = DiceThrower.getDiceCombinations(4);
        assertEquals(8, result.size());
        assertTrue(result.contains("1111"));
        assertTrue(result.contains("112"));
        assertTrue(result.contains("121"));
        assertTrue(result.contains("13"));
        assertTrue(result.contains("211"));
        assertTrue(result.contains("22"));
        assertTrue(result.contains("31"));
        assertTrue(result.contains("4"));
    }

    @Test
    void testTargetSix() {
        List<String> result = DiceThrower.getDiceCombinations(6);
        assertEquals(32, result.size());
        assertTrue(result.contains("6"));
        assertTrue(result.contains("33"));
        assertTrue(result.contains("222"));
        assertTrue(result.contains("111111"));
    }

    @Test
    void testTargetSeven() {
        List<String> result = DiceThrower.getDiceCombinations(7);
        // Should include combinations like 61, 52, 43, 331, 322, 2221, etc.
        assertTrue(result.size() > 0);
        assertTrue(result.contains("61"));
        assertTrue(result.contains("16"));
        assertTrue(result.contains("52"));
        assertTrue(result.contains("43"));
    }

    @Test
    void testLargerTarget() {
        List<String> result = DiceThrower.getDiceCombinations(10);
        assertTrue(result.size() > 0);
        // All results should sum to 10
        for (String combination : result) {
            int sum = 0;
            for (char c : combination.toCharArray()) {
                sum += Character.getNumericValue(c);
            }
            assertEquals(10, sum);
        }
    }

    @Test
    void testNegativeTarget() {
        assertThrows(IllegalArgumentException.class, () -> { DiceThrower.getDiceCombinations(-1); });
    }

    @Test
    void testNegativeTargetPrint() {
        assertThrows(IllegalArgumentException.class, () -> { DiceThrower.printDiceCombinations(-1); });
    }

    @Test
    void testAllCombinationsValid() {
        List<String> result = DiceThrower.getDiceCombinations(5);

        for (String combination : result) {
            // Check that each character is a valid dice face (1-6)
            for (char c : combination.toCharArray()) {
                int face = Character.getNumericValue(c);
                assertTrue(face >= 1 && face <= 6, "Invalid dice face: " + face);
            }

            // Check that the sum equals the target
            int sum = 0;
            for (char c : combination.toCharArray()) {
                sum += Character.getNumericValue(c);
            }
            assertEquals(5, sum, "Combination " + combination + " does not sum to 5");
        }
    }

    @Test
    void testPrintDiceCombinations() {
        // Capture System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Test printing combinations for target 3
            DiceThrower.printDiceCombinations(3);
            String output = outputStream.toString();

            // Verify all expected combinations are printed
            assertTrue(output.contains("111"));
            assertTrue(output.contains("12"));
            assertTrue(output.contains("21"));
            assertTrue(output.contains("3"));

            // Count number of lines (combinations)
            String[] lines = output.trim().split("\n");
            assertEquals(4, lines.length);
        } finally {
            // Restore System.out
            System.setOut(originalOut);
        }
    }

    @Test
    void testPrintDiceCombinationsZero() {
        // Capture System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            DiceThrower.printDiceCombinations(0);
            String output = outputStream.toString();

            // Should print empty string (one line)
            assertEquals("", output.trim());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testMainMethod() {
        // Capture System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Test main method
            DiceThrower.main(new String[] {});
            String output = outputStream.toString();

            // Verify expected output contains header and combinations
            assertTrue(output.contains("All dice combinations that sum to 4:"));
            assertTrue(output.contains("Total combinations: 8"));
            assertTrue(output.contains("1111"));
            assertTrue(output.contains("22"));
            assertTrue(output.contains("4"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testEdgeCaseTargetFive() {
        List<String> result = DiceThrower.getDiceCombinations(5);
        assertEquals(16, result.size());

        // Test specific combinations exist
        assertTrue(result.contains("11111"));
        assertTrue(result.contains("1112"));
        assertTrue(result.contains("122"));
        assertTrue(result.contains("14"));
        assertTrue(result.contains("23"));
        assertTrue(result.contains("5"));
    }

    @Test
    void testTargetGreaterThanSix() {
        List<String> result = DiceThrower.getDiceCombinations(8);
        assertTrue(result.size() > 0);

        // Verify some expected combinations
        assertTrue(result.contains("62"));
        assertTrue(result.contains("53"));
        assertTrue(result.contains("44"));
        assertTrue(result.contains("2222"));
    }
}
