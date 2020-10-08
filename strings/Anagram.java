import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        String s1;
        String s2;

        System.out.println("Please give a string");
        Scanner in = new Scanner(System.in);
        s1 = in.nextLine();
        System.out.println("Please give another string");
        s2 = in.nextLine();

        if (areAnagram(s1, s2)) {
            System.out.println("same");
        }
        else {
            System.out.println("not same");
        }

    }

    public static boolean areAnagram(String s1, String s2) {
        boolean b;
        ArrayList<Character> charsOfS1 = new ArrayList<>();
        ArrayList<Character> charsForS2 = new ArrayList<>();
        if (s1.length() == s2.length()) {
            for (int i = 0; i < s1.length(); i++) {
                charsOfS1.add(s1.charAt(i));
                charsForS2.add(s2.charAt(i));
            }

            Collections.sort(charsOfS1);
            Collections.sort(charsForS2);


            if (charsOfS1.equals(charsForS2)) {
                b = true;
            }

            else {
                b = false;
            }
        }
        else {
            b = false;
        }
        return b;
    }
}
