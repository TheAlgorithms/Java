package main.java.com.thealgorithms.strings;
import java.util.*;
/**
 * A utility class to remove stars ('*') from a given string.
 * Each '*' deletes the character immediately before it.
 *
 * <p>Example:
 * <pre>
 * Input:  "leet**cod*e"
 * Output: "lecoe"
 * </pre>
 *
 * <p>This implementation uses a stack-like approach for efficient character removal.
 * @author Ganesh Mane
 */
public final class RemovingStars{
    private RemovingStars(){
        //prevent instantiation
    }

     /**
     * Removes stars from the given string, simulating backspace behavior.
     *
     * @param text the input string possibly containing '*'
     * @return the final string after removing stars and their preceding characters
     */
    public static void main(String[] args) {
        String s = "leet**cod*e";
        System.out.println(removeStarsFromString(s));
    }
    public static String removeStarsFromString(String s){
        StringBuilder sb = new StringBuilder();
        for(char ch : s.toCharArray()){
            if(ch == '*'){
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }else{
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}