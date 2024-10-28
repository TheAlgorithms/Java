package com.thealgorithms.maths;

public final class UniformNumbers {

    // Private constructor to prevent instantiation of the utility class
    private UniformNumbers() {
        // Prevent instantiation
    }

    public static int uniformNumbers(int num) {
        String numStr = Integer.toString(num);
        int uniformCount = (numStr.length() - 1) * 9;
        int finalUniform = Integer.parseInt(String.valueOf(numStr.charAt(0)).repeat(numStr.length()));

        if (finalUniform <= num) {
            uniformCount += Integer.parseInt(String.valueOf(numStr.charAt(0)));
        } else {
            uniformCount += Integer.parseInt(String.valueOf(numStr.charAt(0))) - 1;
        }

        return uniformCount;
    }

    public static int countUniformIntegers(int a, int b) {
        if (b > a && b > 0 && a > 0) {
            return uniformNumbers(b) - uniformNumbers(a - 1);
        } else if (b == a) {
            return 1;
        } else {
            return 0;
        }
    }
}
