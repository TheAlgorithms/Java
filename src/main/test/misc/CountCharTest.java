package misc;

import java.util.Scanner;

import static misc.CountChar.countCharacters;

class CountCharTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your text: ");
        String str = input.nextLine();
        System.out.println("There are " + countCharacters(str) + " characters.");
    }
}