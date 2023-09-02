package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.datastructures.lists.SinglyLinkedList;
import org.junit.jupiter.api.Test;

public class PalindromeSinglyLinkedListTest {
    @Test
    public void testWithEmptyList() {
        assertTrue(PalindromeSinglyLinkedList.isPalindrome(new SinglyLinkedList()));
    }

    @Test
    public void testWithSingleElement() {
        var exampleList = new SinglyLinkedList();
        exampleList.insert(100);
        assertTrue(PalindromeSinglyLinkedList.isPalindrome(exampleList));
    }

    @Test
    public void testWithListWithOddLengthPositive() {
        var exampleList = new SinglyLinkedList();
        exampleList.insert(1);
        exampleList.insert(2);
        exampleList.insert(1);
        assertTrue(PalindromeSinglyLinkedList.isPalindrome(exampleList));
    }

    @Test
    public void testWithListWithOddLengthPositive2() {
        var exampleList = new SinglyLinkedList();
        exampleList.insert(3);
        exampleList.insert(2);
        exampleList.insert(1);
        exampleList.insert(2);
        exampleList.insert(3);
        assertTrue(PalindromeSinglyLinkedList.isPalindrome(exampleList));
    }

    @Test
    public void testWithListWithEvenLengthPositive() {
        var exampleList = new SinglyLinkedList();
        exampleList.insert(10);
        exampleList.insert(20);
        exampleList.insert(20);
        exampleList.insert(10);
        assertTrue(PalindromeSinglyLinkedList.isPalindrome(exampleList));
    }

    @Test
    public void testWithListWithOddLengthNegative() {
        var exampleList = new SinglyLinkedList();
        exampleList.insert(1);
        exampleList.insert(2);
        exampleList.insert(2);
        assertFalse(PalindromeSinglyLinkedList.isPalindrome(exampleList));
    }

    @Test
    public void testWithListWithEvenLengthNegative() {
        var exampleList = new SinglyLinkedList();
        exampleList.insert(10);
        exampleList.insert(20);
        exampleList.insert(20);
        exampleList.insert(20);
        assertFalse(PalindromeSinglyLinkedList.isPalindrome(exampleList));
    }
}
