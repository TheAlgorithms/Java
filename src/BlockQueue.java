package com.thealgorithms.datastructures.threadPool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueue<T> {

    private final Deque<T> deque = new ArrayDeque<>();
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();
    //This condition object is used to manage thread waiting when the queue is full
    private final Condition full = lock.newCondition();
    //This condition object is used to manage thread waiting when the queue is empty
    private final Condition empty = lock.newCondition();

    public BlockQueue(int capacity) {
        this.capacity = capacity;
    }


    // add task
    public void put(T task) {
        lock.lock();
        lock.lock();
        try {
            while (deque.size() == capacity) {
                //If the queue is full, the thread that calls this method will be blocked until space becomes available in the queue.
                full.await();
            }
            deque.addLast(task);
            //Wake up threads that might be waiting due to the queue being empty.
            empty.signal();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for space in the queue", e);
        } finally {
            lock.unlock();
        }

    }

    // Block and add with a timeout
    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (deque.size() == capacity) {
                if (nanos <= 0) {
                    return false;
                }
                nanos = full.awaitNanos(nanos);
            }
            deque.addLast(task);
            empty.signal();
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            lock.unlock();
        }
    }


    public T take() {
        lock.lock();
        try {
            while (deque.isEmpty()) {
                empty.await();
            }
            T task = deque.removeFirst();
            full.signal();
            return task;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for elements in the queue", e);
        } finally {
            lock.unlock();
        }
    }


    public T poll(long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (deque.isEmpty()) {
                if (nanos <= 0) {
                    return null;
                }
                nanos = empty.awaitNanos(nanos);
            }
            T task = deque.removeFirst();
            full.signal();
            return task;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }


    }


    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            if (deque.size() == capacity) {
                rejectPolicy.reject(this, task);
            } else {
                deque.addLast(task);
                empty.signal();
            }
        } finally {
            lock.unlock();
        }

    }
}
