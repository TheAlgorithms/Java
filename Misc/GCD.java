//Oskar Enmalm 3/10/17
//This is Euclid's algorithm which is used to find the greatest common denominator

public class GCD{

public static int gcd(int a, int b) {

        int r = a % b;
        while (r != 0) {
            b = r;
            r = b % r;
        }
        return b;
    }
}
