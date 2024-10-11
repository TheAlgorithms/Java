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
        
        // Setting up allocation resources
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
    void testSystemIsInSafeState() {
        assertTrue(bankersAlgorithm.isSafe(), "The system should be in a safe state.");
    }

    @Test
    void testRequestResourcesSuccessfully() {
        int[] request = {1, 0, 2};
        assertDoesNotThrow(() -> bankersAlgorithm.requestResources(0, request), "Request should not throw an exception.");
    }

    @Test
    void testRequestExceedsMaximumClaim() {
        int[] request = {8, 0, 0};
        assertEquals("Error: Process has exceeded its maximum claim.", 
                     getOutput(() -> bankersAlgorithm.requestResources(0, request)),
                     "Expected error message for exceeding maximum claim.");
    }

    @Test
    void testRequestExceedsAvailableResources() {
        int[] request = {1, 0, 3};
        assertEquals("Process is waiting.", 
                     getOutput(() -> bankersAlgorithm.requestResources(0, request)),
                     "Expected message when requesting more resources than available.");
    }

    @Test
    void testRequestResultsInUnsafeState() {
        // Requesting resources that lead to an unsafe state
        int[] request = {3, 3, 0};
        assertEquals("Resources allocation leads to unsafe state, request denied.", 
                     getOutput(() -> bankersAlgorithm.requestResources(1, request)),
                     "Expected message when allocation leads to unsafe state.");
    }

    // Helper method to capture console output
    private String getOutput(Runnable runnable) {
        // Code to capture System.out output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out; // Save original System.out
        System.setOut(new PrintStream(outContent));
        
        runnable.run();
        
        System.setOut(originalOut); // Reset System.out
        return outContent.toString().trim();
    }
}
