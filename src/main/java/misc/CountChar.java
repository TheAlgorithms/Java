package misc;

/**
 * @author Kyler Smith, 2017
 *         <p>
 *         Implementation of a character count.
 *         (Slow, could be improved upon, effectively O(n).
 */
public class CountChar {

    /**
     * @param str: String to count the characters
     * @return int: Number of characters in the passed string
     */
    public static int countCharacters(String str) {
        int count = 0;
        if (str == null || str.equals(""))
            return 0;

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}
