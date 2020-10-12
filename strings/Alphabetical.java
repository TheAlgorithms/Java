package strings;
import java.util.Arrays;

/**
 * <p>
 * Alphabetical order is a system whereby character strings are placed in order
 * based on the position of the characters in the conventional ordering of an alphabet.
 * </p>
 * Wikipedia: https://en.wikipedia.org/wiki/Alphabetical_order
 */
class Alphabetical {

    public static void main(String[] args) {
        assert !isAlphabetical("123abc");
        assert isAlphabetical("aBC");
        assert isAlphabetical("abc");
        assert !isAlphabetical("xyzabc");
        assert isAlphabetical("abcxyz");
    }

    /**
     * Check if a string is alphabetical order or not
     *
     * @param s a string
     * @return {@code true} if given string is alphabetical order, otherwise {@code false}
     */
    public static boolean isAlphabetical(String s) {
        int n = s.length();   
        char c[] = new char [n];  
        for (int i = 0; i < n; i++) {  
            c[i] = s.charAt(i);  
        }   
        Arrays.sort(c);   
        for (int i = 0; i < n; i++)  
            if (c[i] != s.charAt(i))   
                return false;
        return true;
    }
}
