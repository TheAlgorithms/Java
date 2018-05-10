package DataStructures.Lists.Tests;

import DataStructures.Lists.CircleLinkedList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircleLinkedListTest {
    private CircleLinkedList circleLinkedList;

    @Before
    public void setUp() throws Exception {
        circleLinkedList = new CircleLinkedList();
    }

    @Test
    public void getSizeNoElementsAdded() {
        int expected = 0;
        assertEquals(expected, circleLinkedList.getSize());
    }

    @Test
    public void getSizeOneElementAdded() {

    }

    @Test
    public void getSizeNegativeElementAdded() {

    }

    @Test
    public void append() {
    }

    @Test
    public void remove() {
    }
}