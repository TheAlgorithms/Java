package strings;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/** Wikipedia: https://en.wikipedia.org/wiki/Pangram */
public class Pangram {

  /** Driver Code */
  public static void main(String[] args) {
    assert isPangram("The quick brown fox jumps over the lazy dog");
    assert !isPangram("The quick brown fox jumps over the azy dog"); /* not exists l character */
  }

  /**
   * Check if a string is a pangram string or not
   *
   * @param s string to check
   * @return {@code true} if given string is pangram, otherwise {@code false}
   */
  public static boolean isPangram(String s) {
    boolean[] marked = new boolean[26]; /* by default all letters don't exists */

    char[] values = s.toCharArray();

    getStreamOfCharArray(values).
            filter(Character::isLetter).
            map(c -> Character.isUpperCase(c) ? c - 'A' : c - 'a').
            forEach(index -> marked[index] = true);

    return areAllTrue(marked);
  }
  private static Stream<Character> getStreamOfCharArray(char[] values) {
    return IntStream.
            range(0, values.length).
            mapToObj(i -> values[i]);
  }
  public static boolean areAllTrue(boolean[] array)
  {
    for(boolean b : array) if(!b) return false;
    return true;
  }
}
