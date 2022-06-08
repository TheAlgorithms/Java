package com.thealgorithms.maths;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class PrimeTest{
    @Test public void validPartition() {
        //Valid Partition
    	Assertions.assertTrue(PrimeCheck.isPrime(3));
    }
    @Test public void invalidPartition1() {
        //Invalid Partition
    	Assertions.assertFalse(PrimeCheck.isPrime(4));
    }
    @Test public void invalidPartition2() {
        //Invalid Partition
    	Assertions.assertFalse(PrimeCheck.isPrime(-13));
    }
}