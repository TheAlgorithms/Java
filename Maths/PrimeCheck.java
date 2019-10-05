package Maths;

import java.util.Scanner;

public class PrimeCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter n:");
        int n = scanner.nextInt();
        if (isPrime(n)) {
            System.out.println(n + "is prime number");
        } else {
            System.out.println(n + "is not prime number");
        }
    }

    /***
     * Check a number is prime or not
     * @param n the number
     * @return {@code true} if {@code n} is prime
     */
    public static boolean isPrime(int n) {
        if (n == 2) {
            return true;
        }
        if (n < 2 || n % 2 == 0) {
            return false;
        }
        for (int i = 3, limit = (int) Math.sqrt(n); i <= limit; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
