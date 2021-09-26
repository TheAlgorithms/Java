package Conversions;

/** @author Varun Upadhyay (https://github.com/varunu28) */

// Driver program
public class AnyBaseToDecimal {
  public static void main(String[] args) {
    assert convertToDecimal("1010", 2) == Integer.valueOf("1010", 2);
    assert convertToDecimal("777", 8) == Integer.valueOf("777", 8);
    assert convertToDecimal("999", 10) == Integer.valueOf("999", 10);
    assert convertToDecimal("ABCDEF", 16) == Integer.valueOf("ABCDEF", 16);
    assert convertToDecimal("XYZ", 36) == Integer.valueOf("XYZ", 36);
  }

  /**
   * Convert any radix to decimal number
   *
   * @param s the string to be convert
   * @param radix the radix
   * @return decimal of bits
   * @throws NumberFormatException if {@code bits} or {@code radix} is invalid
   */
  public static int convertToDecimal(String s, int radix) {
    int num = 0;
    int pow = 1;

    for (int i = s.length() - 1; i >= 0; i--) {
      int digit = valOfChar(s.charAt(i));
      if (digit >= radix) {
        throw new NumberFormatException("For input string " + s);
      }
      num += valOfChar(s.charAt(i)) * pow;
      pow *= radix;
    }
    return num;
  }

  /**
   * Convert character to integer
   *
   * @param c the character
   * @return represented digit of given character
   * @throws NumberFormatException if {@code ch} is not UpperCase or Digit character.
   */
  public static int valOfChar(char c) {
    if (!(Character.isUpperCase(c) || Character.isDigit(c))) {
      throw new NumberFormatException("invalid character :" + c);
    }
    return Character.isDigit(c) ? c - '0' : c - 'A' + 10;
  }
}
