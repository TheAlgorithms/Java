package Maths;

import java.lang.Math;
import java.util.Scanner;

public class PrimeFactorization {
    public static void main(String[] args){
        System.out.println("## all prime factors ##");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        System.out.print(("printing factors of " + n + " : "));
        pfactors(n);
        scanner.close();
    }
    public static void pfactors(int n){

        while (n%2==0)
        {
            System.out.print(2 + " ");
             n /= 2;
        }

        for (int i=3; i<= Math.sqrt(n); i+=2)
        {
            while (n%i == 0)
            {
                System.out.print(i + " ");
                n /= i;
            }
        }

        if(n > 2)
            System.out.print(n);
    }
}
