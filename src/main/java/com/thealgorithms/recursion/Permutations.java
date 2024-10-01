package com.thealgorithms.recursion;

import java.util.ArrayList;

public class Permutations {

    public static void permute(String str, String prefix, ArrayList<String> result) {
        if (str.length() == 0) {
            result.add(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring(i + 1);
                permute(rem, prefix + str.charAt(i), result);
            }
        }
    }
}
