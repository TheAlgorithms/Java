package Maths;

import java.util.Random;

public class Ceil {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 1; i <= 1000; ++i) {
            double randomNumber = random.nextDouble();
            assert ceil(randomNumber) == Math.ceil(randomNumber);
        }
    }

    /**
     * Returns the smallest (closest to negative infinity)
     *
     * @param number the number
     * @return the smallest (closest to negative infinity) of given {@code number}
     */
    public static double ceil(double number) {
        if (number - (int) number == 0) {
            return number;
        } else if (number - (int) number > 0) {
            return (int) (number + 1);
        } else {
            return (int) number;
        }
    }
}