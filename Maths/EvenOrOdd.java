package Maths;

/**
 * A positive integer is called an even if (number % 2) == 0
 */

public class EvenOrOdd {
    public static void main(String[] args) {
        System.out.println(isEven(153)); 
        System.out.println(isEven(1634)); 
    }

    /**
     * Check if {@code number} is even number or not
     *
     * @param number the number
     * @return {@code true} if {@code number} is an even number, otherwise false
     */
    public static boolean isEven(int number) {
        return (number % 2 == 0);
    }
}
