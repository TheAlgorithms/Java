package Maths;

public class FindNearestFromTwoNumbers {

    //Driver
    public static void main(String[] args) {
        int[] array = {2, 4, 9, 7, 19, 94, 5};
        System.out.println("max = " + findMax(array));
    }

    /**
     * find the nearest integer from a given two numbers.
     * for example, calling findNearest(4, 2, 8) will return 2
     * if both numbers are in the same distance, return the larger number
     *
     * @param target number you are trying to find the nearset integer from
     * @param compare1 first number to compare with
     * @param compare2 second number to compare with
     * @return nearest integer from either compare1 or compare2
     */
    public static int findNearest(int target, int compare1, int compare2) {
        return Math.abs(target - compare1) < Math.abs(target - compare2) ? compare1 : compare2;
    }
}