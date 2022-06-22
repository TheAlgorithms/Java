package com.thealgorithms.maths;

public class Armstrong {

    public boolean isArmstrong(int number) {
        long sum = 0;
        long number2 = number;
        while (number2 > 0) {
            long mod = number2 % 10;
            sum += Math.pow(mod, 3);
            number2 /= 10;
        }
        return sum == number;
    }
}