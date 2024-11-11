package com.thealgorithms.dynamicprogramming;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class AllConstructTest {

    @Test
    public void testAllConstructBasic() {
        List<List<String>> expected = singletonList(Arrays.asList("he", "l", "l", "o"));
        List<List<String>> result = AllConstruct.allConstruct("hello", Arrays.asList("he", "l", "o"));
        assertEquals(expected, result);
    }

    @Test
    public void testAllConstructMultipleWays() {
        List<List<String>> expected = Arrays.asList(Arrays.asList("purp", "le"), Arrays.asList("p", "ur", "p", "le"));
        List<List<String>> result = AllConstruct.allConstruct("purple", Arrays.asList("purp", "p", "ur", "le", "purpl"));
        assertEquals(expected, result);
    }

    @Test
    public void testAllConstructNoWays() {
        List<List<String>> expected = emptyList();
        List<List<String>> result = AllConstruct.allConstruct("abcdef", Arrays.asList("gh", "ijk"));
        assertEquals(expected, result);
    }

    @Test
    public void testAllConstructEmptyTarget() {
        List<List<String>> expected = singletonList(emptyList());
        List<List<String>> result = AllConstruct.allConstruct("", Arrays.asList("a", "b", "c"));
        assertEquals(expected, result);
    }
}
