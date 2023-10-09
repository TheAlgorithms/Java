package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class PrimeFactorizationTest {

    @Test
    void testpFactorsMustReturnEmptyList() {
        // given
        int n = 0;

        // then
        assertTrue(PrimeFactorization.pfactors(n).isEmpty());
    }

    @Test
    void testpFactorsMustReturnNonEmptyList() {
        // given
        int n = 198;
        int expectedListSize = 4;

        // when
        List<Integer> actualResultList = PrimeFactorization.pfactors(n);

        // then
        assertEquals(expectedListSize, actualResultList.size());
        assertEquals(2, actualResultList.get(0));
        assertEquals(3, actualResultList.get(1));
        assertEquals(3, actualResultList.get(2));
        assertEquals(11, actualResultList.get(3));
    }
}
