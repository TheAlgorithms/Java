package strings;

import java.util.stream.IntStream;

/**
 * Alphabetical order is a system whereby character strings are placed in order based on the
 * position of the characters in the conventional ordering of an alphabet. Wikipedia:
 * https://en.wikipedia.org/wiki/Alphabetical_order
 */
class Alphabetical {

  public static void main(String[] args) {
    assert !isAlphabetical("123abc");
    assert isAlphabetical("aBC");
    assert isAlphabetical("abc");
    assert !isAlphabetical("xyzabc");
    assert isAlphabetical("abcxyz");
  }

  /**
   * Check if a string is alphabetical order or not
   *
   * @param s a string
   * @return {@code true} if given string is alphabetical order, otherwise {@code false}
   */
  public static boolean isAlphabetical(String s) {
    final String sLower = s.toLowerCase();
    return IntStream.
            range(0, sLower.length() - 1).
            allMatch(i -> isOrderderdAndAlphabaticAtIndex(i, sLower));
  }
  private static boolean isOrderderdAndAlphabaticAtIndex(int index, String string){
    int currentCharValue = string.charAt(index);
    int nextCharValue = string.charAt(index + 1);
    return Character.isLetter(currentCharValue) && currentCharValue <= nextCharValue;
  }
}
