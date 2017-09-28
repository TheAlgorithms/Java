package misc;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */
public class RemoveDuplicate {

    /**
     * This method produces a string after removing all the duplicate characters from input string and returns it
     * Example: Input String - "aabbbccccddddd"
     * Output String - "abcd"
     *
     * @param string String from which duplicate characters have to be removed
     * @return string with only unique characters
     */

    public static String removeDuplicate(String string) {
        if (string == null || string.isEmpty()) {
            return string;
        }

        StringBuilder sb = new StringBuilder("");
        int n = string.length();

        for (int i = 0; i < n; i++) {
            if (sb.toString().indexOf(string.charAt(i)) == -1) {
                sb.append(String.valueOf(string.charAt(i)));
            }
        }

        return sb.toString();
    }
}
