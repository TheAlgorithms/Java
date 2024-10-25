package com.thealgorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RemoveSubFolders {

    public static List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> result = new ArrayList<>();
        String prev = " ";
        for (String f : folder) {
            if (!f.startsWith(prev)) {
                result.add(f);
                prev = f + "/";
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] folder = {"/a","/a/b","/c/d","/c/d/e","/c/f"};
        List<String> result = removeSubfolders(folder);
        System.out.println(result); // Output: ["/a", "/c/d", "/c/f"]
    }
}
