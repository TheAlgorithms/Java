package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class ShorAlgorithmTest {
    @Test
    public void testFactorizationOfEvenNumber() {
        ShorAlgorithm shor = new ShorAlgorithm();
        BigInteger number = new BigInteger("15");
        BigInteger[] factors = shor.shorAlgorithm(number);

        assertNotNull(factors, "Factors should not be null for composite numbers.");
        assertEquals(2, factors.length, "There should be two factors.");

        BigInteger p = factors[0];
        BigInteger q = factors[1];

        assertEquals(number, p.multiply(q), "Factors should multiply to the original number.");
    }

    @Test
    public void testFactorizationOfPrimeNumber() {
        ShorAlgorithm shor = new ShorAlgorithm();
        BigInteger number = new BigInteger("13");
        BigInteger[] factors = shor.shorAlgorithm(number);

        assertNull(factors, "Factors should be null for prime numbers.");
    }

    @Test
    public void testFactorizationOfEvenCompositeNumber() {
        ShorAlgorithm shor = new ShorAlgorithm();
        BigInteger number = new BigInteger("20");
        BigInteger[] factors = shor.shorAlgorithm(number);
        
        assertNotNull(factors, "Factors should not be null for composite numbers.");
        assertEquals(2, factors.length, "There should be two factors.");

        BigInteger p = factors[0];
        BigInteger q = factors[1];

        assertEquals(number, p.multiply(q), "Factors should multiply to the original number.");
    }
}