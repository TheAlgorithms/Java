package com.thealgorithms.stacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class SmallestElementConstantTimeTest {

    private SmallestElementConstantTime sect;

    @BeforeEach
    public void setSect() {
        sect = new SmallestElementConstantTime();
    }

    @Test
    public void testMinAtFirst() {
        sect.push(1);
        sect.push(10);
        sect.push(20);
        sect.push(5);
        assertEquals(1, sect.getMinimumElement());
    }

    @Test
    public void testMinTwo() {
        sect.push(5);
        sect.push(10);
        sect.push(20);
        sect.push(1);
        assertEquals(1, sect.getMinimumElement());
        sect.pop();
        assertEquals(5, sect.getMinimumElement());
    }

    @Test
    public void testNullMin() {
        sect.push(10);
        sect.push(20);
        sect.pop();
        sect.pop();
        assertNull(sect.getMinimumElement());
    }

    @Test
    public void testBlankHandle() {
        sect.push(10);
        sect.push(1);
        sect.pop();
        sect.pop();
        assertThrows(NoSuchElementException.class, () -> sect.pop());
    }

}
