package Maths;

// If you have a high load project where you need to compute factorial of big numbers a lot of times
// you need to optimize a time to get it. This method provides you an opportunity to speed up your project.
// But this method have only one requirement: You need to know what's maximum factorial you've need to get.
// Or you can make it more flexible by using ArrayList instead of simple array.
// The complexity of this algorithm to get a needed factorial is O(1) after initialization in constructor.
import java.math.BigInteger;

public class OptimizedFactorial {
    private BigInteger[] factorials;

    public OptimizedFactorial(int maxValue) {
        if (maxValue >= 0) {
            factorials = new BigInteger[maxValue + 1];

            factorials[0] = BigInteger.ONE;

            for (int index = 1; index < factorials.length; index++) {
                factorials[index] = BigInteger.valueOf(index).multiply(factorials[index - 1]);
            }

        } else {
            factorials = null;
        }
    }

    public static void main(String[] args) {
        int yourNumber = 23;
        OptimizedFactorial optimizedFactorial = new OptimizedFactorial(yourNumber);
        BigInteger result = optimizedFactorial.getFactorial(yourNumber);
        System.out.printf("!%d = %d", yourNumber, result);
    }

    public BigInteger getFactorial(int number) {
        if ((number < 0) || (number >= factorials.length))
            return null;
        return factorials[number];
    }
}