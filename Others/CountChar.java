package Others;

import java.util.Scanner;

public class CountChar {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter your text: ");
    String str = input.nextLine();
    input.close();
    System.out.println("There are " + CountCharacters(str) + " characters.");
  }

  /**
   * Count non space character in string
   *
   * @param str String to count the characters
   * @return number of character in the specified string
   */
  private static int CountCharacters(String str) {
    return str.replaceAll("\\s", "").length();
  }
}
