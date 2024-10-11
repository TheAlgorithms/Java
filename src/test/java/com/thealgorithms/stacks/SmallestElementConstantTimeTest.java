package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    public void testPushPopAfterEmpty() {
        sect.push(10);
        sect.push(1);
        sect.pop();
        sect.pop();
        sect.push(5);
        assertEquals(5, sect.getMinimumElement());
        sect.push(1);
        assertEquals(1, sect.getMinimumElement());
    }
}
