package com.thealgorithms.dynamicprogramming;
import java.util.ArrayList;
public class subsetRecursion {
    public static void subset(String prefix, String str, ArrayList<String> result) {
        // Base case: if the string is empty, add the prefix to the result
        if (str.isEmpty()) {
            result.add(prefix);
            return;
        }
        // Recursive call by including the first character in the prefix
        subset(prefix + str.charAt(0), str.substring(1), result);
        
        // Recursive call by excluding the first character
        subset(prefix, str.substring(1), result);
        
    }
}
