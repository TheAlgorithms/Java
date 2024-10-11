package com.thealgorithms.operatingsystemconcepts;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileDescriptor;

public class BankersAlgorithmTest {

    private BankersAlgorithm bankersAlgorithm;

    @BeforeEach
    void setUp() {
        // Example setup with 5 processes and 3 resources
        bankersAlgorithm = new BankersAlgorithm(5, 3);

        // Setting up maximum resources
        bankersAlgorithm.max = new int[][] {
            {7, 5, 3},
            {3, 2, 2},
            {9, 0, 2},
            {2, 2, 2},
            {4, 3, 3}
        };

        // Setting up allocated resources
        bankersAlgorithm.allot = new int[][] {
            {0, 1, 0},
            {2, 0, 0},
            {3, 0, 2},
            {2, 1, 1},
            {0, 0, 2}
        };

        // Setting up available resources
        bankersAlgorithm.available = new int[]{3, 3, 2};

        // Calculating need matrix
        bankersAlgorithm.calculateNeed();
    }

    @Test
    void testIsSafeState() {
        assertTrue(bankersAlgorithm.isSafe());
    }

    @Test
    void testRequestResourcesValid() {
        int[] request = {1, 0, 2};
        assertDoesNotThrow(() -> bankersAlgorithm.requestResources(0, request));
    }

    @Test
    void testRequestResourcesExceedsNeed() {
        int[] request = {8, 0, 0};
        assertEquals("Error: Process has exceeded its maximum claim.",
                     getOutput(() -> bankersAlgorithm.requestResources(0, request)));
    }

    @Test
    void testRequestResourcesExceedsAvailable() {
        int[] request = {1, 0, 3};
        assertEquals("Process is waiting.",
                     getOutput(() -> bankersAlgorithm.requestResources(0, request)));
    }

    @Test
    void testRequestResourcesUnsafeState() {
        // Requesting resources that lead to an unsafe state
        int[] request = {3, 3, 0};
        assertEquals("Resources allocation leads to unsafe state, request denied.",
                     getOutput(() -> bankersAlgorithm.requestResources(1, request)));
    }

    // Helper method to capture console output
    private String getOutput(Runnable runnable) {
        // Code to capture System.out output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        runnable.run();

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        return outContent.toString().trim();
    }
}
