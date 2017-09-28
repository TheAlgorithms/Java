package misc;

/**
 * You enter a string into this program, and it will return how
 * many words were in that particular string
 *
 * @author Marcus
 */
public class CountWords {
    public static int wordCount(String s) {
        if (s.isEmpty() || s == null) return -1;
        return s.trim().split("[\\s]+").length;
    }
}
