package com.thealgorithms.compression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BurrowsWheelerTransformTest {

    @Test
    public void testTransformAndInverseBanana() {
        String original = "banana$";
        BurrowsWheelerTransform.BWTResult expectedTransform = new BurrowsWheelerTransform.BWTResult("annb$aa", 4);

        // Test forward transform
        BurrowsWheelerTransform.BWTResult actualTransform = BurrowsWheelerTransform.transform(original);
        assertEquals(expectedTransform, actualTransform);

        // Test inverse transform
        String reconstructed = BurrowsWheelerTransform.inverseTransform(actualTransform.transformed, actualTransform.originalIndex);
        assertEquals(original, reconstructed);
    }

    @Test
    public void testTransformAndInverseAbracadabra() {
        String original = "abracadabra$";
        BurrowsWheelerTransform.BWTResult expectedTransform = new BurrowsWheelerTransform.BWTResult("ard$rcaaaabb", 3);

        // Test forward transform
        BurrowsWheelerTransform.BWTResult actualTransform = BurrowsWheelerTransform.transform(original);
        assertEquals(expectedTransform, actualTransform);

        // Test inverse transform
        String reconstructed = BurrowsWheelerTransform.inverseTransform(actualTransform.transformed, actualTransform.originalIndex);
        assertEquals(original, reconstructed);
    }

    @Test
    public void testTransformAndInverseSixMixPixFix() {
        String original = "SIX.MIX.PIX.FIX$";
        BurrowsWheelerTransform.BWTResult expectedTransform = new BurrowsWheelerTransform.BWTResult("XXXX.FPSM..$IIII", 11);

        // Test forward transform
        BurrowsWheelerTransform.BWTResult actualTransform = BurrowsWheelerTransform.transform(original);
        assertEquals(expectedTransform, actualTransform);

        // Test inverse transform
        String reconstructed = BurrowsWheelerTransform.inverseTransform(actualTransform.transformed, actualTransform.originalIndex);
        assertEquals(original, reconstructed);
    }

    @Test
    public void testEmptyString() {
        String original = "";
        BurrowsWheelerTransform.BWTResult expectedTransform = new BurrowsWheelerTransform.BWTResult("", -1);

        BurrowsWheelerTransform.BWTResult actualTransform = BurrowsWheelerTransform.transform(original);
        assertEquals(expectedTransform, actualTransform);

        String reconstructed = BurrowsWheelerTransform.inverseTransform(actualTransform.transformed, actualTransform.originalIndex);
        assertEquals(original, reconstructed);
    }

    @Test
    public void testSingleCharacter() {
        String original = "a";
        BurrowsWheelerTransform.BWTResult expectedTransform = new BurrowsWheelerTransform.BWTResult("a", 0);

        BurrowsWheelerTransform.BWTResult actualTransform = BurrowsWheelerTransform.transform(original);
        assertEquals(expectedTransform, actualTransform);

        String reconstructed = BurrowsWheelerTransform.inverseTransform(actualTransform.transformed, actualTransform.originalIndex);
        assertEquals(original, reconstructed);
    }

    @Test
    public void testTransformNull() {
        assertEquals(new BurrowsWheelerTransform.BWTResult("", -1), BurrowsWheelerTransform.transform(null));
    }

    @Test
    public void testInverseTransformNullString() {
        // bwtString == null
        assertEquals("", BurrowsWheelerTransform.inverseTransform(null, 1));
        // bwtString.isEmpty()
        assertEquals("", BurrowsWheelerTransform.inverseTransform("", 0));
    }

    @Test
    public void testInverseTransformIndexOutOfBounds() {
        String bwt = "annb$aa";
        int n = bwt.length(); // n = 7

        // originalIndex >= n
        assertThrows(IllegalArgumentException.class, () -> BurrowsWheelerTransform.inverseTransform(bwt, n));
        assertThrows(IllegalArgumentException.class, () -> BurrowsWheelerTransform.inverseTransform(bwt, 8));

        // originalIndex < 0
        assertThrows(IllegalArgumentException.class, () -> BurrowsWheelerTransform.inverseTransform(bwt, -2));
    }

    @Test
    public void testBWTResultHelpers() {
        BurrowsWheelerTransform.BWTResult res1 = new BurrowsWheelerTransform.BWTResult("annb$aa", 4);
        BurrowsWheelerTransform.BWTResult res2 = new BurrowsWheelerTransform.BWTResult("annb$aa", 4);
        BurrowsWheelerTransform.BWTResult res3 = new BurrowsWheelerTransform.BWTResult("other", 4);
        BurrowsWheelerTransform.BWTResult res4 = new BurrowsWheelerTransform.BWTResult("annb$aa", 1);

        assertEquals(res1, res1);
        assertEquals(res1, res2);
        assertNotEquals(res1, null); // obj == null
        assertNotEquals(res1, new Object()); // different class
        assertNotEquals(res1, res3); // different transformed
        assertNotEquals(res1, res4); // different originalIndex

        assertEquals(res1.hashCode(), res2.hashCode());
        assertNotEquals(res1.hashCode(), res3.hashCode());

        assertTrue(res1.toString().contains("annb$aa"));
        assertTrue(res1.toString().contains("originalIndex=4"));
    }
}
