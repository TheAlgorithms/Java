package com.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AssignCookiesTest {
    // All test case view in md file
    @Test
    void testFindContentChildrenWithFirstPath() {
        Assertions.assertEquals(0, AssignCookies.findContentChildren(new int[]{}, new int[]{}));
    }

    @Test
    void testFindContentChildrenWithSecondPath() {
        Assertions.assertEquals(0, AssignCookies.findContentChildren(new int[]{2}, new int[]{1}));
    }

    @Test
    void testFindContentChildrenWithThirdPath() {
        Assertions.assertEquals(1, AssignCookies.findContentChildren(new int[]{2}, new int[]{2}));
    }


}
