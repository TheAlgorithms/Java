package Maths;

public class Permutations {
    public static void main(String[] args) {
        int n = 5;
        int k = 3;
        System.out.println(n + "P" + k  + " = " + permutations(n,k));
    }

    /**
     * Calculate the number of permuatations possible given number of elements and length of permutation.
     * Can be thought of as drawing balls from a bag without putting them back and putting them in a row so the final output will have
     * no repeats and order will matter
     * 
     * eg. Given 3 options all permutations of lenth 2 (3P2)
     * 12, 13, 21, 23, 31, 32
     * therefore will output 6
     * 
     *  @param n number of elements
     *  @param k length of permutation
     *  @return the number of permutations from {@code n} options of length {@code k}
     */
    public static long permutations(int n, int k) {
        return Factorial.factorial(n)/Factorial.factorial(n-k);
    }
}