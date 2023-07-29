package com.thealgorithms.misc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PalindromePrimeTest {


    // tim so nguyen to
    @Test
    void prime(){
        boolean primeOf3 = PalindromePrime.prime(3);
        boolean primeOf4 = PalindromePrime.prime(15);// return true if num = 4
        boolean primeOf5 = PalindromePrime.prime(5);

        assertTrue(primeOf3);
        assertTrue(primeOf5);
        // Hàm check số nguyên tố có vấn đề vì khi 4 vẫn trả về số nguyên tố
        assertFalse(primeOf4);

    }

    @Test
    void primeForAll(){
        boolean primeOf3 = PalindromePrime.primeForAll(3);
        boolean primeOf4 = PalindromePrime.primeForAll(8);// return false
        boolean primeOf5 = PalindromePrime.primeForAll(5);

        assertTrue(primeOf3);
        assertTrue(primeOf5);
        assertFalse(primeOf4);
    }

    @Test
    void reverse(){
        int primeOf3 = PalindromePrime.reverse(13);
        int primeOf5 = PalindromePrime.reverse(5);

        assertEquals(primeOf3,31);
        assertEquals(primeOf5,5);

    }
}
