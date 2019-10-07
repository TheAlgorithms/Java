package Maths;

public class LCM {
    public static void main(String[] args) {
        int i = 15;
        int j = 20;

        System.out.println("The LCM of "+i +" and " +j +" is " + lcm(i, j));
    }

    /**
     *  Calculate the lowest common multiple of {@code i} and {@code j}
     */
    public static int lcm(int i, int j) {
        return (i*j)/GCD.gcd(i,j);
    }
}