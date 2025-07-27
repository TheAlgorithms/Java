package com.thealgorithms.others.cn;

public final class HammingDistance {

    private HammingDistance() {
    }

    public static int compute(String bitsStrA, String bitsStrB) {
        HammingHandler chain = new ValidateBinaryHandler();
        chain.setNext(new ValidateLengthHandler()).setNext(new ComputeHammingHandler());

        return (int) chain.handle(bitsStrA, bitsStrB);
    }
}
