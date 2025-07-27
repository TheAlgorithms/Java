package com.thealgorithms.others.cn;
public abstract class HammingHandler {
    protected HammingHandler next;

    public HammingHandler setNext(HammingHandler next) {
        this.next = next;
        return next;
    }

    public abstract Object handle(String bitsStrA, String bitsStrB);
}