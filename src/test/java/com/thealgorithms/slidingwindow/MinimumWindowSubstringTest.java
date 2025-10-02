package com.thealgorithms.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the MinimumWindowSubstring class.
 *
 * @author  (https://github.com/Chiefpatwal)
 */
public class MinimumWindowSubstringTest {

    /**
     * Tests for MinimumWindowSubstring.minWindow.
     */
    @Test
    public void testMinimumWindowSubstring() {
        assertEquals("BANC", MinimumWindowSubstring.minWindow("ADOBECODEBANC", "ABC")); // "BANC"
        assertEquals("a", MinimumWindowSubstring.minWindow("a", "a")); // "a"
        assertEquals("", MinimumWindowSubstring.minWindow("a", "aa")); // ""
        assertEquals("", MinimumWindowSubstring.minWindow("ADOBECODEBANC", "XYZ")); 
        assertEquals("BC", MinimumWindowSubstring.minWindow("ABCDEF", "BC"));
        assertEquals("q", MinimumWindowSubstring.minWindow("abcdefghijklmnopqrstuvwxyz", "q"));
        assertEquals("JQTUHzRLPPyjllmvqopolorblqrijdpytjzxxvnyqihohrszawlvetnrgcnrknuandawlfzthoeoeiqsxliroreemjvqfzuimacidfymmpyljzxtwatvqszhtsjsmpgzjozxvcygidhsunqtvlbvamafbjmplrbrehjmvvlxyluuaqgbrmfxrygeylkzlflbfcwqnemigwhrrshdqiwvifoifxsaxfyqnibcwrkffbtjqxjubgrfvexqhocdvyyavdmujnfwwecqkantiaogsrgvwtwwkcczydpevynzvirsppgrdmfhbdtczprkrrvxxlxjcvauaharesmqjxhjwmrrtspsbobgofrnvdzbwiibqhrrqbtxyspnszwpxbqrwtavsmhhqrqkjegvhbeftfbicnhamhwyonlwlzysocyzshhyknbdkelplkkvazqpnzhydraoyiodssA", MinimumWindowSubstring.minWindow("HRULTrctorlsshmgtarxadgosylsmxsorskzqfdkujoJQTUHzRLPPyjllmvqopolorblqrijdpytjzxxvnyqihohrszawlvetnrgcnrknuandawlfzthoeoeiqsxliroreemjvqfzuimacidfymmpyljzxtwatvqszhtsjsmpgzjozxvcygidhsunqtvlbvamafbjmplrbrehjmvvlxyluuaqgbrmfxrygeylkzlflbfcwqnemigwhrrshdqiwvifoifxsaxfyqnibcwrkffbtjqxjubgrfvexqhocdvyyavdmujnfwwecqkantiaogsrgvwtwwkcczydpevynzvirsppgrdmfhbdtczprkrrvxxlxjcvauaharesmqjxhjwmrrtspsbobgofrnvdzbwiibqhrrqbtxyspnszwpxbqrwtavsmhhqrqkjegvhbeftfbicnhamhwyonlwlzysocyzshhyknbdkelplkkvazqpnzhydraoyiodssA", "HRULTPQPJA")); 
        assertEquals("", MinimumWindowSubstring.minWindow("zzzzzzzzz", "zzzzzzzzzz")); 
        assertEquals("ABCDEFAAAllBBBBllZ", MinimumWindowSubstring.minWindow("ABCllBBBBBlllllllABCDEFAAAllBBBBllZ", "ABAAACDEFZ")); 
        assertEquals("abbbbbcdd", MinimumWindowSubstring.minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd")); 
    }
}
