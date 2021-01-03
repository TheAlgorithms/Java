package com.string;

public class ReverseWords {
  /**
   * Converts all of the words in this {@code String} to reversed words
   *
   * @param s the string to convert
   * @return the {@code String}, converted to a string with reveresed words.
   */

  public static String returnReverseWords(String s) {
    StringBuilder sb = new StringBuilder();
    StringBuilder word = new StringBuilder();

    for(int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if(c == ' ') {
          
            sb.append(word);
            sb.append(" ");
            word.setLength(0);
            continue;
        }
        word.insert(0, c);
    }
    sb.append(word);

    return sb.toString();
  }
}
