package com.thealgorithms.bitmanipulation;

/**
 * Is number power of 2
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public class IsPowerTwo {
    
    public static boolean isPowerTwo(int number) {
        if (number <= 0) {
            return false;
        }
        int ans = number & (number - 1);
        return ans == 0;
    }

//Adding the main function 
public static void main(String[] args) {
    //taking the entered list as example
    int[] list_test1 = {1, 2, 3, 4, 24, 48};
    for (int n : list_test1) {
        System.out.println(n + " is power of 2: " + isPowerTwo(n));
    }
}
}
