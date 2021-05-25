package Maths;

/**
 * This is used to get the least common multiple.
 * This function uses the GCD.
 *
 * @author Yuanhao 5/25/21
 */
public class LCM {

    /**
     * get least common multiple
     *
     * @param num1 the first num
     * @param num2 the second num
     * @return lcm
     */
    public static int lcm(int num1, int num2) {
        //call GCD.gcd function.
        return num1 * num2 / GCD.gcd(num1, num2);
    }

    /**
     * get least common multiple in array
     *
     * @param array contains number
     * @return lcm
     */
    public static int lcm(int[] array) {
        int res = array[0];
        for (int elem : array) {
            res = lcm(res, elem);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = {12, 15, 30};
        System.out.println(lcm(array)); // => 60
        System.out.printf("lcm(40,24)=%d lcm(24,40)=%d%n", lcm(24, 32), lcm(32, 24)); // => 96
    }
}
