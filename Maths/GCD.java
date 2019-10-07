package Maths;

public class GCD {
    public static void main(String[] args) {
        int i = 98;
        int j = 56;

        System.out.println("The GCD of "+i +" and " +j +" is " + gcd(i, j));
    }

    /**
     *  Calculate the greatest common denomator of {@code i} and {@code j}
     */
    public static int gcd(int i, int j) {
        if (i == 0) return j;
        return gcd(j % i, i);
    }
}