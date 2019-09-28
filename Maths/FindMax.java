package Maths;

public class FindMax {

    //Driver
    public static void main(String[] args) {
        int[] array = {2, 4, 9, 7, 19, 94, 5};
        System.out.println("max = " + findMax(array));
    }

    /**
     * find max of array
     *
     * @param array the array contains element
     * @return max value
     */
    public static int findMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
