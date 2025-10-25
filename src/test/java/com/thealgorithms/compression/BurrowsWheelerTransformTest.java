package com.thealgorithms.compression;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
