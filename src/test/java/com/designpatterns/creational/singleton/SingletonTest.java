package com.designpatterns.creational.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class SingletonTest {
    private static Set<Integer> hashCodeSet = new ConcurrentSkipListSet<>();

    @Test
    void testSingleton() throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        // Creates 15 threads and makes all of them access the Singleton class
        // Saves the hash code of the object in a static list
        for (int i = 0; i < 15; i++)
            es.execute(() -> {
                try {
                    Singleton singletonInstance = Singleton.getInstance();
                    int singletonInsCode = singletonInstance.hashCode();
                    hashCodeSet.add(singletonInsCode);
                } catch (Exception e) {
                    System.out.println("Exception is caught");
                }
            });
        es.shutdown();
        boolean finished = es.awaitTermination(1, TimeUnit.MINUTES);
        // wait for all threads to finish
        Assertions.assertTrue(finished);
        Assertions.assertEquals(1, hashCodeSet.size());
    }
}
