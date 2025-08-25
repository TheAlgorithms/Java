package com.thealgorithms.others.cn;
public class ValidateLengthHandler extends HammingHandler {

    @Override
    public Object handle(String bitsStrA, String bitsStrB) {
        if (bitsStrA.length() != bitsStrB.length()) {
            throw new IllegalArgumentException("Input strings must have the same length.");
        }
        return next.handle(bitsStrA, bitsStrB);
    }
}
