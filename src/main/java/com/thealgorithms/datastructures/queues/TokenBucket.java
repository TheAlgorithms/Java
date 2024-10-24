package com.thealgorithms.datastructures.queues;

import java.util.concurrent.TimeUnit;

/**
 * TokenBucket implements a token bucket rate limiter algorithm.
 * This class is used to control the rate of requests in a distributed system.
 * It allows a certain number of requests (tokens) to be processed in a time frame,
 * based on the defined refill rate.
 *
 * Applications: Computer networks, API rate limiting, distributed systems, etc.
 *
 * @author Hardvan
 */
public final class TokenBucket {
    private final int maxTokens;
    private final int refillRate; // tokens per second
    private int tokens;
    private long lastRefill; // Timestamp in nanoseconds

    /**
     * Constructs a TokenBucket instance.
     *
     * @param maxTokens  Maximum number of tokens the bucket can hold.
     * @param refillRate The rate at which tokens are refilled (tokens per second).
     */
    public TokenBucket(int maxTokens, int refillRate) {
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.tokens = maxTokens;
        this.lastRefill = System.nanoTime();
    }

    /**
     * Attempts to allow a request based on the available tokens.
     * If a token is available, it decrements the token count and allows the request.
     * Otherwise, the request is denied.
     *
     * @return true if the request is allowed, false if the request is denied.
     */
    public synchronized boolean allowRequest() {
        refillTokens();
        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }

    /**
     * Refills the tokens based on the time elapsed since the last refill.
     * The number of tokens to be added is calculated based on the elapsed time
     * and the refill rate, ensuring the total does not exceed maxTokens.
     */
    private void refillTokens() {
        long now = System.nanoTime();
        long tokensToAdd = (now - lastRefill) / TimeUnit.SECONDS.toNanos(1) * refillRate;
        tokens = Math.min(maxTokens, tokens + (int) tokensToAdd);
        lastRefill = now;
    }
}
