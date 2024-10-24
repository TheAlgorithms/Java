package com.thealgorithms.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RandomSchedulingTest {

    private RandomScheduling randomScheduling;
    private Random mockRandom;

    @BeforeEach
    public void setup() {
        mockRandom = mock(Random.class); // Mocking Random for predictable behavior
    }

    @Test
    public void testRandomOrder1() {
        // Arrange
        List<String> tasks = List.of("Task1", "Task2", "Task3");
        // Mock the random sequence to control shuffling: swap 0 <-> 1, and 1 <-> 2.
        when(mockRandom.nextInt(anyInt())).thenReturn(1, 2, 0);
        randomScheduling = new RandomScheduling(tasks, mockRandom);

        // Act
        List<String> result = randomScheduling.schedule();

        // Assert
        assertEquals(List.of("Task1", "Task2", "Task3"), result);
    }

    @Test
    public void testRandomOrder2() {
        // Arrange
        List<String> tasks = List.of("A", "B", "C", "D");
        // Mocking predictable swaps for the sequence: [C, B, D, A]
        when(mockRandom.nextInt(anyInt())).thenReturn(2, 1, 3, 0);
        randomScheduling = new RandomScheduling(tasks, mockRandom);

        // Act
        List<String> result = randomScheduling.schedule();

        // Assert
        assertEquals(List.of("A", "C", "B", "D"), result);
    }

    @Test
    public void testSingleTask() {
        // Arrange
        List<String> tasks = List.of("SingleTask");
        when(mockRandom.nextInt(anyInt())).thenReturn(0); // No real shuffle
        randomScheduling = new RandomScheduling(tasks, mockRandom);

        // Act
        List<String> result = randomScheduling.schedule();

        // Assert
        assertEquals(List.of("SingleTask"), result);
    }

    @Test
    public void testEmptyTaskList() {
        // Arrange
        List<String> tasks = List.of();
        randomScheduling = new RandomScheduling(tasks, mockRandom);

        // Act
        List<String> result = randomScheduling.schedule();

        // Assert
        assertEquals(List.of(), result); // Should return an empty list
    }

    @Test
    public void testSameTasksMultipleTimes() {
        // Arrange
        List<String> tasks = List.of("X", "X", "Y", "Z");
        when(mockRandom.nextInt(anyInt())).thenReturn(3, 0, 1, 2);
        randomScheduling = new RandomScheduling(tasks, mockRandom);

        // Act
        List<String> result = randomScheduling.schedule();

        // Assert
        assertEquals(List.of("Y", "X", "X", "Z"), result);
    }
}
