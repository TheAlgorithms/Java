package Maths;

public class FindAverage {

    //Driver
    public static void main(String[] args) {
        int[] array = {2, 4, 10};
        assert findAverage(array) == 5;
    }

    /**
     * find average value of array
     *
     * @param array the array contains element and the sum does not 
     * excess long value limit
     * @return average value
     */
    public static int findAverage(int[] array) {
        long sum = 0;
        for (int i = 0 ; i < array.length; ++i) {
            sum += array[i];
        }
        return (int)(sum / array.length);
    }
}
