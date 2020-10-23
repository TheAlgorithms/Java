package Maths;

import java.util.Random;

public class Floor {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 1; i <= 1000; ++i) {
            double randomNumber = random.nextDouble();
            assert floor(randomNumber) == Math.floor(randomNumber);
        }
    }

    /**
     * Returns the largest (closest to positive infinity)
     *
     * @param number the number
     * @return the largest (closest to positive infinity)  of given {@code number}
     */
    public static double floor(double number) {
        if (number - (int) number == 0) {
            return number;
        } else if (number - (int) number > 0) {
            return (int) number;
        } else {
            return (int) number - 1;
        }
    }
}