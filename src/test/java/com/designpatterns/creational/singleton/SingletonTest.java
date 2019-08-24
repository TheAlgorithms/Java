package com.designpatterns.creational.singleton;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SingletonTest {
    private static volatile ArrayList<Integer> hashCodeList = new ArrayList<>();

    @Test
    public void testSingleton() throws InterruptedException {
        boolean testFailed = false;
        ExecutorService es = Executors.newCachedThreadPool();
        // Creates 15 threads and makes all of them access the Singleton class
        // Saves the hash code of the object in a static list
        for (int i = 0; i < 15; i++)
            es.execute(() -> {
                try {
                    Singleton singletonInstance = Singleton.getInstance();
                    int singletonInsCode = singletonInstance.hashCode();
                    hashCodeList.add(singletonInsCode);
                } catch (Exception e) {
                    System.out.println("Exception is caught");
                }
            });
        es.shutdown();
        boolean finished = es.awaitTermination(1, TimeUnit.MINUTES);
        // wait for all threads to finish
        if (finished) {
            Integer firstCode = hashCodeList.get(0);
            for (Integer code : hashCodeList) {
                if (!firstCode.equals(code)) {
                    testFailed = true;
                }
            }
            assertFalse(testFailed);
        }
    }
}
