package Maths;

public class FindMin {

    //Driver
    public static void main(String[] args) {
        int[] array = {2, 4, 9, 7, 19, 94, 5};
        System.out.println("min = " + findMin(array));
    }

    /**
     * Find the minimum number of an array of numbers.
     *
     * @param array the array contains element
     * @return min value
     */
    public static int findMin(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
}
