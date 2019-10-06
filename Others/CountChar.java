package Others;

import java.util.Scanner;


/**
 * @author blast314
 * <p>
 * Counts the number of characters in the text.
 */

public class CountChar {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your text: ");
        String str = input.nextLine();
        input.close();
        System.out.println("There are " + CountCharacters(str) + " characters.");
    }

    /**
     * @param str: String to count the characters
     * @return int: Number of characters in the passed string
     */
    private static int CountCharacters(String str) {
    	str = str.replaceAll("\\s","");
        return str.length();
    }
}
