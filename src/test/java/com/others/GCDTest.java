package com.others;
/**
 * This is Euclid's algorithm which is used to find the greatest common denominator
 * Overide function name gcd
 *
 * @author hoanpx
 */
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GCDTest {
    private final static int MAX_INT = Integer.MAX_VALUE;
    private final static int MIN_INT = -MAX_INT;

    @Test
    void GCDBoundaryTest() {
        Assertions.assertEquals(MAX_INT, GCD.gcd(MAX_INT, MAX_INT));
        Assertions.assertEquals(1, GCD.gcd(MAX_INT, MAX_INT - 1));
        Assertions.assertEquals(1, GCD.gcd(MAX_INT - 1, MAX_INT ));
        Assertions.assertEquals(MAX_INT - 1, GCD.gcd(MAX_INT - 1, MAX_INT - 1 ));

        Assertions.assertEquals(MAX_INT, GCD.gcd(MIN_INT, MAX_INT ));
        Assertions.assertEquals(1, GCD.gcd(MIN_INT , MAX_INT - 1 ));
        Assertions.assertEquals(1, GCD.gcd(MIN_INT + 1 , MAX_INT ));
        Assertions.assertEquals(MAX_INT - 1, GCD.gcd(MIN_INT + 1 , MAX_INT -1 ));

        Assertions.assertEquals(-MIN_INT, GCD.gcd(MIN_INT, MIN_INT ));
        Assertions.assertEquals(1, GCD.gcd(MIN_INT , MIN_INT + 1 ));
        Assertions.assertEquals(1, GCD.gcd(MIN_INT + 1 , MIN_INT ));
        Assertions.assertEquals(-(MIN_INT + 1), GCD.gcd(MIN_INT + 1 , MIN_INT + 1 ));

        Assertions.assertEquals(MAX_INT, GCD.gcd(MAX_INT, MIN_INT ));
        Assertions.assertEquals(1, GCD.gcd(MAX_INT , MIN_INT + 1 ));
        Assertions.assertEquals(1, GCD.gcd(MAX_INT - 1 , MIN_INT ));
        Assertions.assertEquals(MAX_INT - 1, GCD.gcd(MAX_INT - 1 , MIN_INT + 1 ));

        Assertions.assertEquals(MAX_INT - 1, GCD.gcd(0, MAX_INT - 1 ));

        Assertions.assertEquals(-(MIN_INT + 1), GCD.gcd(0, MIN_INT + 1 ));

        Assertions.assertEquals(MAX_INT, GCD.gcd(0, MAX_INT  ));

        Assertions.assertEquals(-MIN_INT , GCD.gcd(0, MIN_INT  ));
    }
    @Test
    void GCDNormalTest() {
        Assertions.assertEquals(2, GCD.gcd(10, 12));
        Assertions.assertEquals(0, GCD.gcd(0, 0));
        Assertions.assertEquals(1, GCD.gcd(10, 11));
        Assertions.assertEquals(5, GCD.gcd(100, 15));
        Assertions.assertEquals(2, GCD.gcd(-10, 2));
        Assertions.assertEquals(2, GCD.gcd(10, -2));
        Assertions.assertEquals(2, GCD.gcd(-10, -2));
    }
}
