package Others;

import java.util.Scanner;

/**
 * You enter a string into this program, and it will return how many words were
 * in that particular string
 *
 * @author Marcus
 */
public class CountWords {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your text: ");
        String str = input.nextLine();

        System.out.println("Your text has " + wordCount(str) + " word(s)");
        input.close();
    }

    private static int wordCount(String s) {
        if (s == null || s.isEmpty())
            return 0;
        return s.trim().split("[\\s]+").length;
    }

}
