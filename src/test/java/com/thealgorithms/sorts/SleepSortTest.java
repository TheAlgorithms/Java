package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
public class SleepSortTest {

    // Method to capture the output of the SleepSort
    private String captureOutput(Runnable task) {
        // Create a stream to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Run the task
        task.run();

        // Restore original output
        System.setOut(originalOut);

        // Return the captured output
        return outputStream.toString().trim();
    }

    @Test
    public void testSleepSort() {
        ArrayList<Integer> numbers = new ArrayList<>();
        Collections.addAll(numbers, 4, 6, 8, 1, 10);

        // Capture output
        String output = captureOutput(() -> SleepSort.sleepSort(numbers));

        // Expected output
        String expected = "1 4 6 8 10";

        // Check if the captured output matches the expected output
        assertEquals(expected, output);
    }

    @Test
    public void testSleepSortWithAdjacentNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();
        Collections.addAll(numbers, 1, 2, 3, 4);

        // Capture output
        String output = captureOutput(() -> SleepSort.sleepSort(numbers));

        // Expected output
        String expected = "1 2 3 4";

        // Check if the output is sorted
        List<Integer> outputNumbers = parseOutput(output);
        assertTrue(isSorted(outputNumbers), "The output is not sorted.");

        // Check if the output contains all expected numbers
        assertEquals(expected, output, "The output does not contain the expected numbers.");
    }

    @Test
    public void testSleepSortWithLargeNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();
        Collections.addAll(numbers, 1000, 500, 2000, 1500);

        // Capture output
        String output = captureOutput(() -> SleepSort.sleepSort(numbers));

        // Expected output
        String expected = "500 1000 1500 2000";

        // Check if the captured output matches the expected output
        assertEquals(expected, output);
    }

    // Helper method to parse the output string to a list of integers
    private List<Integer> parseOutput(String output) {
        return Arrays.stream(output.trim().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
    }
    private boolean isSorted(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) > list.get(i)) {
                return false;
            }
        }
        return true;
    }
}
