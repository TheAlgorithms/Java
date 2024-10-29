package com.thealgorithms.scheduling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * RandomScheduling is an algorithm that assigns tasks in a random order.
 * It doesn't consider priority, deadlines, or burst times, making it
 * inefficient but useful in scenarios where fairness or unpredictability
 * is required (e.g., load balancing in distributed systems).
 *
 * Use Case: Distributed systems where randomness helps avoid task starvation.
 *
 * @author Hardvan
 */
public final class RandomScheduling {

    private final List<String> tasks;
    private final Random random;

    /**
     * Constructs a new RandomScheduling instance.
     *
     * @param tasks A collection of task names to be scheduled.
     * @param random A Random instance for generating random numbers.
     */
    public RandomScheduling(Collection<String> tasks, Random random) {
        this.tasks = new ArrayList<>(tasks);
        this.random = random;
    }

    /**
     * Schedules the tasks randomly and returns the randomized order.
     *
     * @return A list representing the tasks in their randomized execution order.
     */
    public List<String> schedule() {
        List<String> shuffledTasks = new ArrayList<>(tasks);
        Collections.shuffle(shuffledTasks, random);
        return shuffledTasks;
    }
}
