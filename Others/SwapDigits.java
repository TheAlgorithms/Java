package Others;

/**
 * @author Cody Siegmann
 *
 * This algorithm is used to swap digits in an integer.
 * Ex: For an input of 1234, it would return 2143
 */

public class SwapDigits {
    public static void main(String[] args) {
        assert swapDigits(123) == 213;
        assert swapDigits(1234) == 2143;
        assert swapDigits(12345678) == 21436587;
    }

    /**
     * Swap the digits of the original number
     *
     * @param n, the number
     * @return the swapped version of the digit
     */
    public static int swapDigits(int n) {
        char[] c = String.valueOf(n).toCharArray();

        for(int i = 0; i < c.length-1; i+=2) {
            char tmp = c[i];
            c[i] = c[i+1];
            c[i+1] = tmp;
        }
        return Integer.parseInt(new String(c));
    }
}