package com.thealgorithms.misc;

import com.thealgorithms.datastructures.lists.SinglyLinkedList;
import java.util.Stack;

/**
 * A simple way of knowing if a singly linked list is palindrome is to push all
 * the values into a Stack and then compare the list to popped vales from the
 * Stack.
 *
 * See more:
 * https://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
 */
public final class PalindromeSinglyLinkedList {
    private PalindromeSinglyLinkedList() {
    }

    public static boolean isPalindrome(final SinglyLinkedList linkedList) {
        Stack<Integer> linkedListValues = new Stack<>();

        for (final var x : linkedList) {
            linkedListValues.push(x);
        }

        for (final var x : linkedList) {
            if (x != linkedListValues.pop()) {
                return false;
            }
        }

        return true;
    }
}
