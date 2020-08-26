package strings;

public class CharactersSame {

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        assert isAllCharactersSame("");
        assert !isAllCharactersSame("aab");
        assert isAllCharactersSame("aaa");
        assert isAllCharactersSame("11111");
    }

    /**
     * check if all the characters of a string are same
     *
     * @param s the string to check
     * @return {@code true} if all characters of a string are same, otherwise {@code false}
     */
    public static boolean isAllCharactersSame(String s) {
        for (int i = 1, length = s.length(); i < length; ++i) {
            if (s.charAt(i) != s.charAt(0)) {
                return false;
            }
        }
        return true;
    }
}
