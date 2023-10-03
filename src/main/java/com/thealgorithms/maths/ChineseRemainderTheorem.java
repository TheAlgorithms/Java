import java.util.*;

/**
 * Chinese Remainder Theorem
 *
 * This program calculates the solution to a set of congruence relations using
 * the Chinese Remainder Theorem. The theorem states that there always exists a
 * unique solution x that satisfies the given congruences.
 *
 * Author: [Gyandeep] (https://github.com/Gyan172004)
 *
 * References:
 * - Wikipedia: https://en.wikipedia.org/wiki/Chinese_remainder_theorem
 */

class ChineseRemainderTheorem {
    // Function to calculate the Chinese Remainder using given residues, moduli,
    // size, and product
    static int chineseRemainder(int residues[], int moduli[], int n,
            int product) {
        int result = 0;

        // Iterate through the congruence relations
        for (int i = 0; i < n; i++) {
            // Calculate M, which is the product divided by the modulus
            int M = product / moduli[i];
            int inverse = 0;

            // Calculate the modular multiplicative inverse of M modulo moduli[i]
            for (int j = 0; j < moduli[i]; j++) {
                if ((M * j) % moduli[i] == 1) {
                    inverse = j;
                    break;
                }
            }

            // Calculate the intermediate result using the Chinese Remainder Theorem
            // formula
            result = result + residues[i] * M * inverse;
        }

        // Return the final result modulo the product
        return result % product;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        // Introduction and problem statement
        System.out.println("Chinese Remainder Theorem");
        System.out.println("----------------------------");
        System.out.println("This program calculates the solution to a set of " +
                "congruence relations.");
        System.out.println(
                "The Chinese Remainder Theorem states that there always exists a " +
                        "unique solution x that satisfies these congruences.");
        System.out.println("Enter the number of congruence relations:");

        // Read the number of congruence relations
        int size = scanner.nextInt();

        // Input residues
        System.out.println("Enter the values of residues:");
        int residues[] = new int[size];
        for (int i = 0; i < size; i++)
            residues[i] = scanner.nextInt();

        // Input moduli and calculate the product of moduli
        System.out.println("Enter the values of moduli:");
        int moduli[] = new int[size], product = 1;
        for (int i = 0; i < size; i++) {
            moduli[i] = scanner.nextInt();
            product = product * moduli[i];
        }

        // Calculate and display the solution
        System.out.println("The solution is " +
                chineseRemainder(residues, moduli, size, product));
    }
}
