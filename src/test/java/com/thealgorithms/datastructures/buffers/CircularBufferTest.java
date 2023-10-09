package com.thealgorithms.datastructures.buffers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicIntegerArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class CircularBufferTest {
    private static final int BUFFER_SIZE = 10;
    private CircularBuffer<Integer> buffer;

    @BeforeEach
    void setUp() {
        buffer = new CircularBuffer<>(BUFFER_SIZE);
    }

    @Test
    void isEmpty() {
        assertTrue(buffer.isEmpty());
        buffer.put(generateInt());
        assertFalse(buffer.isEmpty());
    }

    @Test
    void isFull() {
        assertFalse(buffer.isFull());
        buffer.put(generateInt());
        assertFalse(buffer.isFull());

        for (int i = 1; i < BUFFER_SIZE; i++) buffer.put(generateInt());
        assertTrue(buffer.isFull());
    }

    @Test
    void get() {
        assertNull(buffer.get());
        for (int i = 0; i < 100; i++) buffer.put(i);
        for (int i = 0; i < BUFFER_SIZE; i++) assertEquals(i, buffer.get());
        assertNull(buffer.get());
    }

    @Test
    void put() {
        for (int i = 0; i < BUFFER_SIZE; i++) assertTrue(buffer.put(generateInt()));
        assertFalse(buffer.put(generateInt()));
    }

    @RepeatedTest(1000)
    void concurrentTest() throws InterruptedException {
        final int numberOfThreadsForProducers = 3;
        final int numberOfThreadsForConsumers = 2;
        final int numberOfItems = 300;
        final CountDownLatch producerCountDownLatch = new CountDownLatch(numberOfItems);
        final CountDownLatch consumerCountDownLatch = new CountDownLatch(numberOfItems);
        final AtomicIntegerArray resultAtomicArray = new AtomicIntegerArray(numberOfItems);

        // We are running 2 ExecutorService simultaneously 1 - producer, 2 - consumer
        // Run producer threads to populate buffer.
        ExecutorService putExecutors = Executors.newFixedThreadPool(numberOfThreadsForProducers);
        putExecutors.execute(() -> {
            while (producerCountDownLatch.getCount() > 0) {
                int count = (int) producerCountDownLatch.getCount();
                boolean put = buffer.put(count);
                while (!put) put = buffer.put(count);
                producerCountDownLatch.countDown();
            }
        });

        // Run consumer threads to retrieve the data from buffer.
        ExecutorService getExecutors = Executors.newFixedThreadPool(numberOfThreadsForConsumers);
        getExecutors.execute(() -> {
            while (consumerCountDownLatch.getCount() > 0) {
                int count = (int) consumerCountDownLatch.getCount();
                Integer item = buffer.get();
                while (item == null) item = buffer.get();
                resultAtomicArray.set(count - 1, item);
                consumerCountDownLatch.countDown();
            }
        });

        producerCountDownLatch.await();
        consumerCountDownLatch.await();
        putExecutors.shutdown();
        getExecutors.shutdown();
        shutDownExecutorSafely(putExecutors);
        shutDownExecutorSafely(getExecutors);

        List<Integer> resultArray = getSortedListFrom(resultAtomicArray);
        for (int i = 0; i < numberOfItems; i++) {
            int expectedItem = i + 1;
            assertEquals(expectedItem, resultArray.get(i));
        }
    }

    private int generateInt() {
        return ThreadLocalRandom.current().nextInt(0, 100);
    }

    private void shutDownExecutorSafely(ExecutorService executorService) {
        try {
            if (!executorService.awaitTermination(1_000, TimeUnit.MILLISECONDS)) executorService.shutdownNow();
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    public List<Integer> getSortedListFrom(AtomicIntegerArray atomicArray) {
        int length = atomicArray.length();
        ArrayList<Integer> result = new ArrayList<>(length);
        for (int i = 0; i < length; i++) result.add(atomicArray.get(i));
        result.sort(Comparator.comparingInt(o -> o));
        return result;
    }
}
