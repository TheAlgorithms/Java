package com.thealgorithms.greedyalgorithms;
import java.util.ArrayList;
import java.util.List;
// Problem Link: https://en.wikipedia.org/wiki/Greedy_algorithm_for_Egyptian_fractions

public class EgyptianFraction {
    // Function to decompose a fraction into a sum of unit fractions
    public static List<String> getEgyptianFraction(int numerator, int denominator) {
        List<String> result = new ArrayList<>();

        while (numerator != 0) {
            // Find the smallest unit fraction that can be subtracted
            if (denominator % numerator == 0) {
                result.add("1/" + (denominator / numerator));
                break;
            }
            int x = denominator / numerator + 1;

            result.add("1/" + x);
            // Update numerator and denominator for the next iteration
            numerator = numerator * x - denominator;
            denominator = denominator * x;

            // Reduce the fraction by dividing numerator and denominator by their gcd
            int gcd = gcd(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;
        }

        return result;
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        int numerator = 5;
        int denominator = 6;

        List<String> egyptianFractions = getEgyptianFraction(numerator, denominator);

        System.out.println("Egyptian Fraction representation of " + numerator + "/" + denominator + " is:");
        for (String frac : egyptianFractions) {
            System.out.print(frac + " ");
        }
    }
}


