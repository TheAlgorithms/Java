package com.thealgorithms.datastructures.lists;

/**
 * The SearchSinglyLinkedListRecursion class extends SinglyLinkedList and provides
 * a method to search for a value in a singly linked list using recursion.
 * <p>
 * This class demonstrates a recursive approach to check if a given integer value is
 * present in the linked list. The search method calls a private recursive helper method
 * `searchRecursion`, which checks each node's value and moves to the next node if necessary.
 * </p>
 * <p>
 * Example:
 * Given a list containing the values 1 -> 2 -> 3 -> 4, calling search(3) will return `true`,
 * while calling search(5) will return `false`.
 * </p>
 * <p>
 * Complexity:
 * <ul>
 *   <li>Time Complexity: O(n), where n is the number of nodes in the linked list.</li>
 *   <li>Space Complexity: O(n), due to the recursive call stack in the worst case.</li>
 * </ul>
 * </p>
 */
public class SearchSinglyLinkedListRecursion extends SinglyLinkedList {

    /**
     * Recursively searches for a given value in the linked list.
     *
     * @param node the head node to start the search.
     * @param key the integer value to be searched for.
     * @return {@code true} if the value `key` is present in the list; otherwise, {@code false}.
     */
    private boolean searchRecursion(SinglyLinkedListNode node, int key) {
        return (node != null && (node.value == key || searchRecursion(node.next, key)));
    }

    /**
     * Public search method to determine if a key is present in the linked list.
     *
     * @param key the integer value to be searched for.
     * @return {@code true} if the value `key` is present in the list; otherwise, {@code false}.
     */
    @Override
    public boolean search(int key) {
        return searchRecursion(getHead(), key);
    }
}
