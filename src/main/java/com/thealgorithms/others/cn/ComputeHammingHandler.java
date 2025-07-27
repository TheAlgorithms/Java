package com.thealgorithms.others.cn;
public class ComputeHammingHandler extends HammingHandler {

    @Override
    public Object handle(String bitsStrA, String bitsStrB) {
        int totalErrorBitCount = 0;
        for (int i = 0; i < bitsStrA.length(); i++) {
            totalErrorBitCount += bitsStrA.charAt(i) == bitsStrB.charAt(i) ? 0 : 1;
        }
        return totalErrorBitCount;
    }
}