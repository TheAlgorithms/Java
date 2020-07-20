package Others;

/**
 * An Abecadrian is a word where each letter is in alphabetical order
 *
 * @author Oskar Enmalm
 */
public class Abecedarian {

    public static boolean isAbecedarian(String s) {
        int index = s.length() - 1;

        for (int i = 0; i < index; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                return false;
            }
        }
        
        return true;
    }
}
