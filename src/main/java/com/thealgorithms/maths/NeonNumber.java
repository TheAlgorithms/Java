package com.thealgorithms.maths;

public class NeonNumber {

    private NeonNumber(){
    }
    /**
     * Check if a number is Neon Number.
     * A neon number is a number where a sum of digits of its square equals the number itself.
     * Example : 9--> 9^2 = 81  --> 8+1 = 9
     * @see <a href="https://en.wikipedia.org/wiki/Recreational_mathematics">
     *         *    Wikipedia - Recreational Mathematics</a>
     * @param number the number to check
     * @return true if neon number, else --> false
      */


    public static boolean isNeon(int number)
    {
        int square = number*number;
        int digitSum = 0;
        while(square>0){
            digitSum += square % 10;
            square/= 10;
        }
        return digitSum == number;
    }


}