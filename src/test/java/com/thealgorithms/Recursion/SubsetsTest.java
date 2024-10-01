package com.thealgorithms.Recursion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SubsetsTest {

    @Test
    void subset(){
        String str = "abc";
        ArrayList<String> ans = new ArrayList<>();
        ArrayList<String> actual = new ArrayList<>();
        ans.add("abc");
        ans.add("ab");
        ans.add("ac");
        ans.add("a");
        ans.add("bc");
        ans.add("b");
        ans.add("c");
        ans.add("");

        Subsets.subset("",str,actual);
        assertEquals(ans.toString(),actual.toString());
    }
}
