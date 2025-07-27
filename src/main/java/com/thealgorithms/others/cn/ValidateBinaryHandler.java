package com.thealgorithms.others.cn;
public class ValidateBinaryHandler extends HammingHandler {

    private void checkBinary(String str) {
        for (char c : str.toCharArray()) {
            if (c != '0' && c != '1') {
                throw new IllegalArgumentException("Input must be a binary string.");
            }
        }
    }

    @Override
    public Object handle(String bitsStrA, String bitsStrB) {
        checkBinary(bitsStrA);
        checkBinary(bitsStrB);
        return next.handle(bitsStrA, bitsStrB);
    }
}
