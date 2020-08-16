package Maths;

/**
 * Calculate average of a list  of numbers
 */
public class Average {
    public static void main(String[] args) {
        assert average(new double[]{3, 6, 9, 12, 15, 18, 21}) == 12;
        assert average(new double[]{5, 10, 15, 20, 25, 30, 35}) == 20;
        assert average(new double[]{1, 2, 3, 4, 5, 6, 7, 8}) == 4.5;
    }

    /**
     * Calculate average of a list  of numbers
     *
     * @param numbers array to store numbers
     * @return mean of given numbers
     */
    public static double average(double[] numbers) {
        double sum = 0;
        for (double number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }
}
