/**
 *
 */

package com.thealgorithms.bitmanipulation;

/**
 * A utility class for working with 32-bit integers
 * and their byte representations.
 *
 * @author Kumaraswamy BG (XomaDev)
 */
public class BitUtils {

  /**
   * Converts a 32-bit integer into a byte array.
   *
   * @param n The integer to convert.
   * @return A byte array representing the integer.
   */

  public static byte[] getBytesInt32(int n) {
    return new byte[]{
            // breaks down the bits in the int32 to form
            // individual decimal / byte representation
            (byte) (n >> 24), (byte) (n >> 16),
            (byte) (n >> 8), (byte) n
    };
  }

  /**
   * Converts a byte array back into a 32-bit integer.
   *
   * @param bytes The byte array to convert.
   * @return The reconstructed integer value.
   */

  public static int getInt32(byte[] bytes) {
    if (bytes.length != 4)
      throw new IllegalArgumentException("Invalid bytes argument");
    // Reconstructs the original int32 by performing bitwise OR operations
    // on each byte, bytes are unsigned before the operation by `byte & 255`
    return ((bytes[0] & 255) << 24) |
            ((bytes[1] & 255) << 16) |
            ((bytes[2] & 255) << 8) |
            ((bytes[3] & 255));
  }

  /**
   * Converts byte to its binary representation
   */
  public static int[] toBitArray(byte b) {
    int n = b & 0xff; // un-signed integer
    // range: 0 ~ 256 only
    // convert the ints to bits
    int[] bits = new int[8];

    // it's reverse looped to read bits form left
    for (int i = 7, j = 0; i >= 0; i--)
      // >> right shift each time, for `bit` iteration
      // then access the last `bit` (The Least Significant Bit or LSB) of shifted value
      bits[j++] = (n >> i) & 1;
    return bits;
  }
}
