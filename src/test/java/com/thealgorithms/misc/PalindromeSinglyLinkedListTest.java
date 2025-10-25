package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thealgorithms.datastructures.lists.SinglyLinkedList;
import org.junit.jupiter.api.Test;

public class PalindromeSinglyLinkedListTest {

    // Stack-based tests
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

    // Optimized approach tests
    @Test
    public void testOptimisedWithEmptyList() {
        assertTrue(PalindromeSinglyLinkedList.isPalindromeOptimised(null));
    }

    @Test
    public void testOptimisedWithSingleElement() {
        PalindromeSinglyLinkedList.Node node = new PalindromeSinglyLinkedList.Node(100);
        assertTrue(PalindromeSinglyLinkedList.isPalindromeOptimised(node));
    }

    @Test
    public void testOptimisedWithOddLengthPositive() {
        PalindromeSinglyLinkedList.Node node1 = new PalindromeSinglyLinkedList.Node(1);
        PalindromeSinglyLinkedList.Node node2 = new PalindromeSinglyLinkedList.Node(2);
        PalindromeSinglyLinkedList.Node node3 = new PalindromeSinglyLinkedList.Node(1);
        node1.next = node2;
        node2.next = node3;
        assertTrue(PalindromeSinglyLinkedList.isPalindromeOptimised(node1));
    }

    @Test
    public void testOptimisedWithOddLengthPositive2() {
        PalindromeSinglyLinkedList.Node node1 = new PalindromeSinglyLinkedList.Node(3);
        PalindromeSinglyLinkedList.Node node2 = new PalindromeSinglyLinkedList.Node(2);
        PalindromeSinglyLinkedList.Node node3 = new PalindromeSinglyLinkedList.Node(1);
        PalindromeSinglyLinkedList.Node node4 = new PalindromeSinglyLinkedList.Node(2);
        PalindromeSinglyLinkedList.Node node5 = new PalindromeSinglyLinkedList.Node(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        assertTrue(PalindromeSinglyLinkedList.isPalindromeOptimised(node1));
    }

    @Test
    public void testOptimisedWithEvenLengthPositive() {
        PalindromeSinglyLinkedList.Node node1 = new PalindromeSinglyLinkedList.Node(10);
        PalindromeSinglyLinkedList.Node node2 = new PalindromeSinglyLinkedList.Node(20);
        PalindromeSinglyLinkedList.Node node3 = new PalindromeSinglyLinkedList.Node(20);
        PalindromeSinglyLinkedList.Node node4 = new PalindromeSinglyLinkedList.Node(10);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        assertTrue(PalindromeSinglyLinkedList.isPalindromeOptimised(node1));
    }

    @Test
    public void testOptimisedWithOddLengthNegative() {
        PalindromeSinglyLinkedList.Node node1 = new PalindromeSinglyLinkedList.Node(1);
        PalindromeSinglyLinkedList.Node node2 = new PalindromeSinglyLinkedList.Node(2);
        PalindromeSinglyLinkedList.Node node3 = new PalindromeSinglyLinkedList.Node(2);
        node1.next = node2;
        node2.next = node3;
        assertFalse(PalindromeSinglyLinkedList.isPalindromeOptimised(node1));
    }

    @Test
    public void testOptimisedWithEvenLengthNegative() {
        PalindromeSinglyLinkedList.Node node1 = new PalindromeSinglyLinkedList.Node(10);
        PalindromeSinglyLinkedList.Node node2 = new PalindromeSinglyLinkedList.Node(20);
        PalindromeSinglyLinkedList.Node node3 = new PalindromeSinglyLinkedList.Node(20);
        PalindromeSinglyLinkedList.Node node4 = new PalindromeSinglyLinkedList.Node(20);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        assertFalse(PalindromeSinglyLinkedList.isPalindromeOptimised(node1));
    }
}
