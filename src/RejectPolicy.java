package com.thealgorithms.datastructures.threadPool;


public interface RejectPolicy<T> {

    void reject(BlockQueue<T> blockQueue, T e);

}
