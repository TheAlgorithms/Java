package com.thealgorithms.stacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class GreatestElementConstantTimeTest {

    private GreatestElementConstantTime gect;

    @BeforeEach
    public void setGect() {
        gect = new GreatestElementConstantTime();
    }

    @Test
    public void testMaxAtFirst() {
        gect.push(1);
        gect.push(10);
        gect.push(20);
        gect.push(5);
        assertEquals(20, gect.getMaximumElement());
    }

    @Test
    public void testMinTwo() {
        gect.push(5);
        gect.push(10);
        gect.push(20);
        gect.push(1);
        assertEquals(20, gect.getMaximumElement());
        gect.pop();
        gect.pop();
        assertEquals(10, gect.getMaximumElement());
    }

    @Test
    public void testNullMax() {
        gect.push(10);
        gect.push(20);
        gect.pop();
        gect.pop();
        assertNull(gect.getMaximumElement());
    }

    @Test
    public void testBlankHandle() {
        gect.push(10);
        gect.push(1);
        gect.pop();
        gect.pop();
        assertThrows(NoSuchElementException.class, () -> gect.pop());
    }
}
