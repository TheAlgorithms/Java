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
public class PalindromeSinglyLinkedList {

    public static void main(String[] args) {
        SinglyLinkedList linkedList = new SinglyLinkedList();

        linkedList.insertHead(3);
        linkedList.insertNth(2, 1);
        linkedList.insertNth(1, 2);
        linkedList.insertNth(2, 3);
        linkedList.insertNth(3, 4);

        if (isPalindrome(linkedList)) {
            System.out.println("It's a palindrome list");
        } else {
            System.out.println("It's NOT a palindrome list");
        }
    }

    public static boolean isPalindrome(SinglyLinkedList linkedList) {
        boolean ret = true;
        Stack<Integer> linkedListValues = new Stack<>();

        for (int i = 0; i < linkedList.size(); i++) {
            linkedListValues.push(linkedList.getNth(i));
        }

        for (int i = 0; i < linkedList.size(); i++) {
            if (linkedList.getNth(i) != linkedListValues.pop()) {
                ret = false;
                break;
            }
        }

        return ret;
    }
}
