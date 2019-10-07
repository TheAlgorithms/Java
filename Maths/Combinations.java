package Maths;

public class Combinations {
    public static void main(String[] args) {
        int n = 8;
        int k = 4;
        System.out.println(n + "C" + k  + " = " + combinations(n,k));
    }

    /**
     * Calculate the number of combinations possible given number of elements and length of combination.
     * Can be thought of as drawing balls from a bag without putting them back and putting them in a container so the final output will have
     * no repeats and order won't matter
     * 
     * eg. Given 3 options all permutations of lenth 2 (3P2)
     * 12, 13, 21, 23, 31, 32
     * but because order doesnt matter and there for 12 = 21, 13 = 31 and 23 = 32 we are left with the following combinations (3C2)
     * 12, 13, 23
     * therefore will output 3
     * 
     *  @param n number of elements
     *  @param k length of permutation
     *  @return the number of combinations from {@code n} options of length {@code k} ie. {@code n}C{@code k} 
     */
    public static long combinations(int n, int k) {
        return Factorial.factorial(n)/(Factorial.factorial(n-k)*Factorial.factorial(k));
    }
}