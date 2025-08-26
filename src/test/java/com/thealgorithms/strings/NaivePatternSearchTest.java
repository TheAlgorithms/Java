package strings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class NaivePatternSearchTest {

    @Test
    void testPatternFound() {
        String text = "abcdxabchjikabc";
        String pattern = "abc";
        List<Integer> result = NaivePatternSearch.search(text, pattern);
        assertEquals(List.of(0, 5, 12), result);
    }

    @Test
    void testPatternNotFound() {
        String text = "hello world";
        String pattern = "abc";
        List<Integer> result = NaivePatternSearch.search(text, pattern);
        assertTrue(result.isEmpty());
    }

    @Test
    void testPatternAtEnd() {
        String text = "xyzabc";
        String pattern = "abc";
        List<Integer> result = NaivePatternSearch.search(text, pattern);
        assertEquals(List.of(3), result);
    }

    @Test
    void testPatternEqualsText() {
        String text = "abc";
        String pattern = "abc";
        List<Integer> result = NaivePatternSearch.search(text, pattern);
        assertEquals(List.of(0), result);
    }
}
