import java.util.Scanner;

public class ReverseStringRecursion {

  public static void main(String[] args) {
    System.out.println("Please enter string");
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    System.out.println("Reverse of the input string is ");
    System.out.println(reverseOfString(input));
  }

  private static String reverseOfString(String input) {
    if (input.length() <= 0) {
      return "";
    }
    return input.substring(input.length() - 1) + reverseOfString(
        input.substring(0, input.length() - 1));
  }
}
