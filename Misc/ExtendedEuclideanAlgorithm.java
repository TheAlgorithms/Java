/*
** This is the Extended Euclidean algorithm, which computes gcd(a,b) as well as the integers x,z such that ax+by = gcd(a,b).
** https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm
**
** @author Joel Brun
** @version 1.0, 19.10.2017
**/

import java.util.Scanner;

public class ExtendedEuclideanAlgorithm {
    static int s = 0;
    static int t = 1;
    static int old_s = 1;
    static int old_t = 0;
    static int r;
    static int old_r;

    static void compute(int a, int b){
        r = a;
        old_r = b;

        while(r!=0){
            int quotient = old_r/r;
            int temp = r;
            r = old_r - quotient * temp;
            old_r = temp;
            temp = s;
            s = old_s -quotient* temp;
            old_s = temp;
            temp = t;
            t = old_t - quotient * temp;
            old_t = temp;
        }

        System.out.println("Bézout coefficients (x,z): " + old_s + ", " + old_t);
        System.out.println("gcd(a,b) = "+old_r);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter integer a: ");
        int a = sc.nextInt();
        System.out.print("Enter integer b: ");
        int b = sc.nextInt();

        compute(a,b);
    }
}