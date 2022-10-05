package bitMagic;

import java.util.Scanner;

public class PowerOf2 {
    static boolean isPowerOfTwo(int n){
        if(n==0)
            return false;
        return ((n&(n-1))==0); //brains kerningam algorithm
    }
    public static void main(String[] args) {

        /*  this program contains the usage of brian kerningam algorithm for calculation of power of 2 of any number
            this algorithm use the concept of bit manipulation for calculation of the answer so this algorithm is really fast
        * */
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the no you want to check");
        int num =sc.nextInt();
        System.out.println(isPowerOfTwo(num));

    }
}
