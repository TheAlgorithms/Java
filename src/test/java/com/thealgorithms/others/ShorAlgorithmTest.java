package com.thealgorithms.others;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testLargeCompositeNumber() {
        ShorAlgorithm shor = new ShorAlgorithm();
        BigInteger number = new BigInteger("21");
        BigInteger[] factors = shor.shorAlgorithm(number);
        
        assertNotNull(factors, "Factors should not be null for composite numbers.");
        assertEquals(2, factors.length, "There should be two factors.");
        
        BigInteger p = factors[0];
        BigInteger q = factors[1];
        
        assertEquals(number, p.multiply(q), "Factors should multiply to the original number.");
    }
}

