package search;

import searching.MajorityElement;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MajorityElementTest {

    @Test
    public void testMajorityElement1() {
        int[] nums = {3, 3, 4, 2, 3, 3, 3};
        assertEquals(3, MajorityElement.findMajority(nums));
    }

    @Test
    public void testMajorityElement2() {
        int[] nums = {2, 2, 1, 1, 2, 2};
        assertEquals(2, MajorityElement.findMajority(nums));
    }

    @Test
    public void testMajorityElementSingle() {
        int[] nums = {1};
        assertEquals(1, MajorityElement.findMajority(nums));
    }
}
