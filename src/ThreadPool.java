package com.thealgorithms.datastructures.threadPool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * A simple thread pool implementation.
 */
public class ThreadPool {

    private final BlockQueue<Runnable> blockQueue;
    private final Set<Worker> workers = new HashSet<>();
    private final int corePoolSize;
    private final long keepAliveTime;
    private final TimeUnit timeUnit;
    private final RejectPolicy<Runnable> rejectPolicy;

    /**
     * Constructs a new ThreadPool with the given parameters.
     *
     * @param blockQueue      the blocking queue to hold tasks
     * @param corePoolSize    the number of core threads
     * @param keepAliveTime   the time to keep extra threads alive
     * @param timeUnit        the time unit for keepAliveTime
     * @param rejectPolicy    the policy to handle rejected tasks
     */
    public ThreadPool(BlockQueue<Runnable> blockQueue, int corePoolSize, long keepAliveTime, TimeUnit timeUnit, RejectPolicy<Runnable> rejectPolicy) {
        this.blockQueue = blockQueue;
        this.corePoolSize = corePoolSize;
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
    }

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(new BlockQueue<>(5), 2, 5, TimeUnit.SECONDS,
                (queue, task) -> {
                    // Discard the task
                    System.out.println("Task " + task + " rejected.");
                });


        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            threadPool.execute(() -> {
                System.out.println("Task " + taskId + " is running on thread: " + Thread.currentThread().getName());
                try {
                    // Simulate some work
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " completed.");
            });
        }

        // Wait for a while to let all tasks complete
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }



    }

    /**
     * Executes a task using the thread pool.
     *
     * @param task the task to execute
     */
    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < corePoolSize) {
                // Create a new worker thread
                Worker worker = new Worker();
                workers.add(worker);
                worker.start();
            } else {
                /*
                * Try to put the task into the block queue
                * */
                blockQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    /**
     * Worker thread class.
     */
    class Worker extends Thread {

        private volatile boolean running = true;

        public Worker() {
            super("Worker-" + workers.size());
        }

        @Override
        public void run() {
            while (running) {
                Runnable task = null;
                try {
                    // Get a task from the block queue
                    task = blockQueue.poll(keepAliveTime, timeUnit);
                    if (task != null) {
                        task.run();
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Error executing task", e);
                } finally {
                    if (task == null || !running) {
                        synchronized (workers) {
                            workers.remove(this);
                        }
                    }
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}