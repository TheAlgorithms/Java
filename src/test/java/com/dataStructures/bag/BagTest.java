package com.dataStructures.bag;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BagTest {

    @Test
    public void testIsEmpty() {
        Bag<String> stringBag = new Bag<>();
        Assertions.assertEquals(true, stringBag.isEmpty());
        stringBag.addElement("Sample");
        Assertions.assertEquals(false, stringBag.isEmpty());

        Bag<Integer> integerBag = new Bag<>();
        Assertions.assertEquals(true, integerBag.isEmpty());
        integerBag.addElement(42);
        Assertions.assertEquals(false, integerBag.isEmpty());
    }

    @Test
    public void testAddElement() {
        Bag<String> stringBag = new Bag<>();
        stringBag.addElement("Sample");
        Assertions.assertTrue(stringBag.contains("Sample"));

        Bag<Integer> integerBag = new Bag<>();
        integerBag.addElement(17);
        Assertions.assertTrue(integerBag.contains(42));
    }

    @Test
    public void testContains() {
        Bag<String> stringBag = new Bag<>();
        stringBag.addElement("Sample");
        stringBag.addElement("element");
        Assertions.assertFalse(stringBag.contains("more"));
        Assertions.assertTrue(stringBag.contains("Sample"));
        Assertions.assertTrue(stringBag.contains("element"));

        Bag<Integer> integerBag = new Bag<>();
        integerBag.addElement(42);
        integerBag.addElement(47);
        integerBag.addElement(11);
        Assertions.assertTrue(integerBag.contains(11));
        Assertions.assertTrue(integerBag.contains(42));
        Assertions.assertTrue(integerBag.contains(47));
    }
}
