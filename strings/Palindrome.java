package strings;

/** Wikipedia: https://en.wikipedia.org/wiki/Palindrome */
class Palindrome {

  /** Driver Code */
  public static void main(String[] args) {
    String[] palindromes = {null, "", "aba", "123321"};
    for (String s : palindromes) {
      assert isPalindrome(s) && isPalindromeRecursion(s) && isPalindrome1(s);
    }

    String[] notPalindromes = {"abb", "abc", "abc123"};
    for (String s : notPalindromes) {
      assert !isPalindrome(s) && !isPalindromeRecursion(s) && !isPalindrome1(s);
    }
  }

  /**
   * Check if a string is palindrome string or not
   *
   * @param s a string to check
   * @return {@code true} if given string is palindrome, otherwise {@code false}
   */
  public static boolean isPalindrome(String s) {
    return (s == null || s.length() <= 1) || s.equals(new StringBuilder(s).reverse().toString());
  }

  /**
   * Check if a string is palindrome string or not using recursion
   *
   * @param s a string to check
   * @return {@code true} if given string is palindrome, otherwise {@code false}
   */
  public static boolean isPalindromeRecursion(String s) {
    if (s == null || s.length() <= 1) {
      return true;
    }

    if (s.charAt(0) != s.charAt(s.length() - 1)) {
      return false;
    }

    return isPalindrome(s.substring(1, s.length() - 1));
  }

  /**
   * Check if a string is palindrome string or not another way
   *
   * @param s a string to check
   * @return {@code true} if given string is palindrome, otherwise {@code false}
   */
  public static boolean isPalindrome(String str)
  {

    // Pointers pointing to the beginning
    // and the end of the string
    int i = 0, j = str.length() - 1;

    // While there are characters toc compare
    while (i < j) {

      // If there is a mismatch
      if (str.charAt(i) != str.charAt(j))
        return false;

      // Increment first pointer and
      // decrement the other
      i++;
      j--;
    }

    // Given string is a palindrome
    return true;
  }
}
