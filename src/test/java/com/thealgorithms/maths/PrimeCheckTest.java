package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PrimeCheckTest{
    @Test
    public void Test1() {
        //Valid Partition
    	assertEquals(true,PrimeCheck.isPrime(3));
    }
    @Test
    public void Test2() {
        //Invalid Partition
    	assertEquals(false,PrimeCheck.isPrime(4));
    }
    @Test
    public void Test3() {
        //Invalid Partition
    	assertEquals(false,PrimeCheck.isPrime(-13));
    }
}