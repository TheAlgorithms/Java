package misc;

import java.util.Scanner;

import static misc.CountWords.wordCount;

class CountWordsTest {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your text: ");
        String str = input.nextLine();
        System.out.println("Your text has " + wordCount(str) + " word(s)");
        input.close();
    }
}