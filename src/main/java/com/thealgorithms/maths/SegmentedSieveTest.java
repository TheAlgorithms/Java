import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.*;
import org.junit.jupiter.api.Test;

public class SegmentedSieveTest {

    // The segmented sieve algorithm implementation
    public static void fillPrime(List<Integer> chprime, int high) {
        boolean[] ck = new boolean[high + 1];
        Arrays.fill(ck, true);
        ck[1] = false;
        ck[0] = false;

        for (int i = 2; (i * i) <= high; i++) {
            if (ck[i]) {
                for (int j = i * i; j <= high; j = j + i) {
                    ck[j] = false;
                }
            }
        }
        for (int i = 2; i <= high; i++) {
            if (ck[i]) {
                chprime.add(i);
            }
        }
    }

    public static List<Integer> segmentedSieve(int low, int high) {
        if (low < 2) throw new IllegalArgumentException("Low must be greater than or equal to 2.");
        if (low > high) throw new IllegalArgumentException("Low cannot be greater than high.");

        List<Integer> chprime = new ArrayList<>();
        fillPrime(chprime, (int) Math.sqrt(high));

        boolean[] prime = new boolean[high - low + 1];
        Arrays.fill(prime, true);

        for (int primeNum : chprime) {
            int lower = (low / primeNum) * primeNum;
            if (lower < low) lower += primeNum;
            if (lower == primeNum) lower += primeNum;

            for (int j = lower; j <= high; j += primeNum) {
                prime[j - low] = false;
            }
        }

        List<Integer> primesInRange = new ArrayList<>();
        for (int i = low; i <= high; i++) {
            if (prime[i - low]) {
                primesInRange.add(i);
            }
        }

        return primesInRange;
    }

    // JUnit test cases
    @Test
    public void testSegmentedSieveSmallRange() {
        List<Integer> expectedPrimes = Arrays.asList(2, 3, 5, 7);
        assertEquals(expectedPrimes, segmentedSieve(2, 10));
    }

    @Test
    public void testSegmentedSieveLargeRange() {
        List<Integer> expectedPrimes = Arrays.asList(99991, 99997);
        assertEquals(expectedPrimes, segmentedSieve(99990, 100000));
    }

    @Test
    public void testSegmentedSieveSingleNumberRange() {
        List<Integer> expectedPrime = Arrays.asList(7);
        assertEquals(expectedPrime, segmentedSieve(7, 7));

        List<Integer> expectedEmpty = new ArrayList<>();
        assertEquals(expectedEmpty, segmentedSieve(8, 8));
    }

    @Test
    public void testSegmentedSieveWithInvalidRange() {
        assertThrows(IllegalArgumentException.class, () -> segmentedSieve(-10, 10));
        assertThrows(IllegalArgumentException.class, () -> segmentedSieve(20, 10));
    }
}
