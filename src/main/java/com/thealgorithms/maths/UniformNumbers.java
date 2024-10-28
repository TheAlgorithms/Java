package com.thealgorithms.maths;

public class UniformNumbers {

    public static int uniformNumbers(int num) {
        String numStr = Integer.toString(num);
        int uniformCount = (numStr.length() - 1) * 9; // Count for digits less than current length
        int finalUniform = Integer.parseInt(String.valueOf(numStr.charAt(0)).repeat(numStr.length()));

        if (finalUniform <= num) {
            uniformCount += Integer.parseInt(String.valueOf(numStr.charAt(0)));
        } else {
            uniformCount += Integer.parseInt(String.valueOf(numStr.charAt(0))) - 1;
        }

        return uniformCount;
    }

    public static int countUniformIntegers(int A, int B) {
        return uniformNumbers(B) - uniformNumbers(A - 1);
    }
}

