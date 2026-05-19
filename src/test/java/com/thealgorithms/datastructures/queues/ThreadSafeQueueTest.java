package com.thealgorithms.datastructures.queues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

public class ThreadSafeQueueTest {

    @Test
    public void testEnqueueDequeue() throws InterruptedException {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(3, queue.size());
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testOfferPoll() {
        ThreadSafeQueue<String> queue = new ThreadSafeQueue<>(2);
        assertTrue(queue.offer("a"));
        assertTrue(queue.offer("b"));
        assertFalse(queue.offer("c"));

        assertEquals("a", queue.poll());
        assertEquals("b", queue.poll());
        assertNull(queue.poll());
    }

    @Test
    public void testOfferRejectsWhenFull() {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(2);
        assertTrue(queue.offer(1));
        assertTrue(queue.offer(2));
        assertFalse(queue.offer(3));
        assertEquals(2, queue.size());
    }

    @Test
    public void testPollReturnsNullWhenEmpty() {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(5);
        assertNull(queue.poll());
    }

    @Test
    public void testEnqueueNullThrows() {
        ThreadSafeQueue<String> queue = new ThreadSafeQueue<>(5);
        assertThrows(IllegalArgumentException.class, () -> queue.enqueue(null));
    }

    @Test
    public void testOfferNullThrows() {
        ThreadSafeQueue<String> queue = new ThreadSafeQueue<>(5);
        assertThrows(IllegalArgumentException.class, () -> queue.offer(null));
    }

    @Test
    public void testInvalidCapacityThrows() {
        assertThrows(IllegalArgumentException.class, () -> new ThreadSafeQueue<>(0));
        assertThrows(IllegalArgumentException.class, () -> new ThreadSafeQueue<>(-1));
    }

    @Test
    public void testIsEmptyAndIsFull() throws InterruptedException {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(2);
        assertTrue(queue.isEmpty());
        assertFalse(queue.isFull());

        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());

        queue.enqueue(2);
        assertFalse(queue.isEmpty());
        assertTrue(queue.isFull());

        queue.dequeue();
        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());

        queue.dequeue();
        assertTrue(queue.isEmpty());
        assertFalse(queue.isFull());
    }

    @Test
    public void testCapacity() {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(10);
        assertEquals(10, queue.capacity());
    }

    @Test
    public void testCircularBufferWrapAround() throws InterruptedException {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());

        queue.enqueue(4);
        queue.enqueue(5);

        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
        assertEquals(5, queue.dequeue());
    }

    @Test
    public void testMultipleProducersSingleConsumer() throws InterruptedException {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(100);
        int numProducers = 4;
        int itemsPerProducer = 250;
        int totalItems = numProducers * itemsPerProducer;
        CountDownLatch doneLatch = new CountDownLatch(numProducers);
        List<Integer> results = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(numProducers + 1);

        for (int p = 0; p < numProducers; p++) {
            final int producerId = p;
            executor.submit(() -> {
                try {
                    for (int i = 0; i < itemsPerProducer; i++) {
                        queue.enqueue(producerId * itemsPerProducer + i);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneLatch.countDown();
                }
            });
        }

        Thread consumerThread = new Thread(() -> {
            try {
                while (results.size() < totalItems) {
                    Integer item = queue.poll();
                    if (item != null) {
                        synchronized (results) {
                            results.add(item);
                        }
                    }
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        });
        consumerThread.start();

        assertTrue(doneLatch.await(10, TimeUnit.SECONDS));
        consumerThread.join(5000);

        assertEquals(totalItems, results.size());
        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS));
    }

    @Test
    public void testSingleProducerMultipleConsumers() throws InterruptedException {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(50);
        int numConsumers = 4;
        int totalItems = 1000;
        CountDownLatch doneLatch = new CountDownLatch(numConsumers);
        AtomicInteger consumedCount = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(numConsumers + 1);

        executor.submit(() -> {
            try {
                for (int i = 0; i < totalItems; i++) {
                    queue.enqueue(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        for (int c = 0; c < numConsumers; c++) {
            executor.submit(() -> {
                try {
                    while (consumedCount.get() < totalItems) {
                        Integer item = queue.poll();
                        if (item != null) {
                            consumedCount.incrementAndGet();
                        }
                    }
                } finally {
                    doneLatch.countDown();
                }
            });
        }

        assertTrue(doneLatch.await(10, TimeUnit.SECONDS));
        assertEquals(totalItems, consumedCount.get());
        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS));
    }

    @Test
    public void testBlockingEnqueueWhenFull() throws InterruptedException {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(1);
        queue.enqueue(1);

        AtomicInteger blockedCount = new AtomicInteger(0);
        Thread producer = new Thread(() -> {
            try {
                queue.enqueue(2);
                blockedCount.incrementAndGet();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start();

        Thread.sleep(100);
        assertEquals(1, queue.dequeue());

        producer.join(2000);
        assertEquals(1, blockedCount.get());
        assertEquals(2, queue.dequeue());
    }

    @Test
    public void testBlockingDequeueWhenEmpty() throws InterruptedException {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(5);

        AtomicInteger result = new AtomicInteger(-1);
        Thread consumer = new Thread(() -> {
            try {
                result.set(queue.dequeue());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        consumer.start();

        Thread.sleep(100);
        queue.enqueue(42);

        consumer.join(2000);
        assertEquals(42, result.get());
    }

    @Test
    public void testStressConcurrentAccess() throws InterruptedException {
        ThreadSafeQueue<Integer> queue = new ThreadSafeQueue<>(10);
        int numThreads = 8;
        int opsPerThread = 500;
        CountDownLatch latch = new CountDownLatch(numThreads);
        AtomicInteger enqueueCount = new AtomicInteger(0);
        AtomicInteger dequeueCount = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int t = 0; t < numThreads; t++) {
            final boolean isProducer = t % 2 == 0;
            executor.submit(() -> {
                try {
                    for (int i = 0; i < opsPerThread; i++) {
                        if (isProducer) {
                            if (queue.offer(i)) {
                                enqueueCount.incrementAndGet();
                            }
                        } else {
                            if (queue.poll() != null) {
                                dequeueCount.incrementAndGet();
                            }
                        }
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        assertTrue(latch.await(10, TimeUnit.SECONDS));
        assertTrue(enqueueCount.get() >= dequeueCount.get());
        assertEquals(enqueueCount.get() - dequeueCount.get(), queue.size());
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}
