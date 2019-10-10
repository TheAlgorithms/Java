// to check if a number is a power of 2 or not in O(1)
// contributed by

package Maths;

import java.util.Scanner;

public class PowerOfTwo{
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        boolean ans = powerOfTwo(n);
        System.out.println(ans);
    }

    public static boolean powerOfTwo(int n){
        if((n & (n-1)) == 0){
            return true;
        }
        return false;
    }
}