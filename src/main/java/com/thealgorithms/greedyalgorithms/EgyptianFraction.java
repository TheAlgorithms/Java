package com.thealgorithms.greedyalgorithms;
import java.util.ArrayList;
import java.util.List;
// Problem Link: https://en.wikipedia.org/wiki/Greedy_algorithm_for_Egyptian_fractions

public class EgyptianFraction {
    
    public List<String> getEgyptianFraction(int numerator, int denominator) {
        List<String> fractions = new ArrayList<>();

        // Loop until the numerator becomes zero
        while (numerator != 0) {
            // Find the smallest unit fraction
            int x = (denominator + numerator - 1) / numerator; // Ceiling of (denominator / numerator)
            fractions.add("1/" + x);

            // Update the numerator and denominator
            numerator = numerator * x - denominator;
            denominator = denominator * x;
        }

        return fractions;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a; // Compact if statement for readability
        return gcd(b, a % b);
    }
}


