package com.thealgorithms.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BitUtilsTest {

  @Test
  public void testInt32Conversion() {
    int input = 123456789;
    byte[] bytes = BitUtils.getBytesInt32(input);
    int reconstructed = BitUtils.getInt32(bytes);
    assertEquals(input, reconstructed);
  }

  @Test
  public void testBitsInt32() {
    byte input = (byte) 177;
    int[] bits = BitUtils.toBitArray(input);
    System.out.println("Bits Of " + input + " is " + Arrays.toString(bits));
  }
}
