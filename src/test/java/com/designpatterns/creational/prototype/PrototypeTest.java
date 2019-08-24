package com.designpatterns.creational.prototype;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrototypeTest {

    @Test
    public void testPrototype() {
        String testFailReason = "";
        String testOne = ColorStore.getColor("blue").addColor();
        if (!"Blue color added".equals(testOne)) {
            testFailReason += "TC 1 Failed: Blue couldn't be added\n";
        }
        String testTwo = ColorStore.getColor("black").addColor();
        if (!"Black color added".equals(testTwo)) {
            testFailReason += "TC 2 Failed: Black couldn't be added\n";
        }
        String testThree = ColorStore.getColor("red").addColor();
        if (!"Red color added".equals(testThree)) {
            testFailReason += "TC 3 Failed: Red couldn't be added\n";
        }
        String testFour = ColorStore.getColor("blue").addColor();
        if (!"Blue color added".equals(testFour)) {
            testFailReason += "TC 4 Failed: Blue couldn't be added\n";
        }
        assertEquals(testFailReason, "", testFailReason);
    }
}
