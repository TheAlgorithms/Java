package Misc;

import java.util.Scanner;

public class PalindromePrime {

    public static void main(String[] args) { // Main funtion
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the quantity of First Palindromic Primes you want");
        int n = in.nextInt(); // Input of how many first pallindromic prime we want
        functioning(n); // calling function - functioning  
        in.close();
    }

    public static boolean prime(int num) { // checking if number is prime or not
        for (int divisor = 3; divisor <= Math.sqrt(num); divisor += 2) {
            if (num % divisor == 0) {
                return false; //  false if not prime
            }
        }
        return true; // True if prime
    }

    public static int reverse(int n) { //  Returns  the reverse of the number
        int reverse = 0;
        while (n != 0) {
            reverse *= 10;
            reverse += n % 10;
            n /= 10;
        }
        return reverse;
    }

    public static void functioning(int y) {
        if (y == 0) return;
        System.out.print(2 + "\n"); // print the first Palindromic Prime
        int count = 1;
        int num = 3;
        while (count < y) {
            if (num == reverse(num) && prime(num)) { // number is prime and it's reverse is same
                count++; // counts check when to terminate while loop
                System.out.print(num + "\n"); // print the Palindromic Prime
            }
            num += 2; // inrease iterator value by two
        }
    }
}
