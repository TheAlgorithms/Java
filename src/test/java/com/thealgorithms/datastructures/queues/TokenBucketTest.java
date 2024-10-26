package com.thealgorithms.datastructures.queues;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TokenBucketTest {

    @Test
    public void testRateLimiter() throws InterruptedException {
        TokenBucket bucket = new TokenBucket(5, 1);
        for (int i = 0; i < 5; i++) {
            assertTrue(bucket.allowRequest());
        }
        assertFalse(bucket.allowRequest());
        Thread.sleep(1000);
        assertTrue(bucket.allowRequest());
    }

    @Test
    public void testRateLimiterWithExceedingRequests() throws InterruptedException {
        TokenBucket bucket = new TokenBucket(3, 1);

        for (int i = 0; i < 3; i++) {
            assertTrue(bucket.allowRequest());
        }
        assertFalse(bucket.allowRequest());

        Thread.sleep(1000);
        assertTrue(bucket.allowRequest());
        assertFalse(bucket.allowRequest());
    }

    @Test
    public void testRateLimiterMultipleRefills() throws InterruptedException {
        TokenBucket bucket = new TokenBucket(2, 1);

        assertTrue(bucket.allowRequest());
        assertTrue(bucket.allowRequest());
        assertFalse(bucket.allowRequest());

        Thread.sleep(1000);
        assertTrue(bucket.allowRequest());

        Thread.sleep(1000);
        assertTrue(bucket.allowRequest());
        assertFalse(bucket.allowRequest());
    }

    @Test
    public void testRateLimiterEmptyBucket() {
        TokenBucket bucket = new TokenBucket(0, 1);

        assertFalse(bucket.allowRequest());
    }

    @Test
    public void testRateLimiterWithHighRefillRate() throws InterruptedException {
        TokenBucket bucket = new TokenBucket(5, 10);

        for (int i = 0; i < 5; i++) {
            assertTrue(bucket.allowRequest());
        }

        assertFalse(bucket.allowRequest());

        Thread.sleep(1000);

        for (int i = 0; i < 5; i++) {
            assertTrue(bucket.allowRequest());
        }
    }

    @Test
    public void testRateLimiterWithSlowRequests() throws InterruptedException {
        TokenBucket bucket = new TokenBucket(5, 1);

        for (int i = 0; i < 5; i++) {
            assertTrue(bucket.allowRequest());
        }

        Thread.sleep(1000);
        assertTrue(bucket.allowRequest());

        Thread.sleep(2000);
        assertTrue(bucket.allowRequest());
        assertTrue(bucket.allowRequest());
    }
}
