package com.thealgorithms.others.cn;
public class MockHammingHandler extends HammingHandler {
    private final Object returnValue;

    public MockHammingHandler(Object returnValue) {
        this.returnValue = returnValue;
    }

    @Override
    public Object handle(String a, String b) {
        return returnValue;
    }
}
