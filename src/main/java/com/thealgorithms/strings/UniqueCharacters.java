package main.java.com.thealgorithms.strings;
import java.util.*;
public class UniqueCharacters {
    public static boolean checkUniqueCharacters(String str) {
        // Creating a hashSet to count the unique characters
        HashSet<Character> hs = new HashSet<>();
        // adding the elements in the hashSet
        for (int i = 0; i < str.length(); i++) {
            hs.add(str.charAt(i));
        }
        // check if the length of the string and the hashSet is
        // same or not
        return hs.size() == str.length();

    }

    public static void main(String[] args) {
        String str = "asdkfg";
        if (checkUniqueCharacters(str)) {
            System.out.println("This string has all unique characters");
        } else {
            System.out.println("This string does not has all unique characters");
        }
    }
}
