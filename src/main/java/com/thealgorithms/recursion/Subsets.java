package com.thealgorithms.recursion;

import java.util.ArrayList;

public class Subsets {

    public static void subset(String prefix, String str, ArrayList<String> result) {
        if (str.length() == 0) {
            result.add(prefix);
            return;
        }
        subset(prefix + str.charAt(0), str.substring(1), result);
        subset(prefix, str.substring(1), result);
    }
}
