package strings;

/**
 * Vowel Count is a system whereby character strings are placed in order based on the
 * position of the characters in the conventional ordering of an alphabet. Wikipedia:
 * https://en.wikipedia.org/wiki/Alphabetical_order
 */
class CheckVowels{
    public static void main(String[] args) {
        assert !hasVowels("This is a strings");
        assert hasVowels("Hello World");
        assert hasVowels("Java is fun");
        assert !hasVowels("123hi");
        assert hasVowels("Coding vs Programming");
    }
    
  /**
   * Check if a string is has vowels or not
   *
   * @param input a string
   * @return {@code true} if given string has vowels, otherwise {@code false}
   */
    public static boolean hasVowels(String input){
        return input.matches("[AEIOUaeiou]");
    }
}