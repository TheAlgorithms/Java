package com.thealgorithms.datastructures.lists;

public class SearchSinglyLinkedListRecursion extends SinglyLinkedList {

    public static void main(String[] args) {
        SearchSinglyLinkedListRecursion list = new SearchSinglyLinkedListRecursion();
        for (int i = 1; i <= 10; ++i) {
            list.insert(i);
        }

        for (int i = 1; i <= 10; ++i) {
            assert list.search(i);
        }
        assert !list.search(-1) && !list.search(100);
    }

    /**
     * Test if the value key is present in the list using recursion.
     *
     * @param node the head node.
     * @param key the value to be searched.
     * @return {@code true} if key is present in the list, otherwise
     * {@code false}.
     */
    private boolean searchRecursion(Node node, int key) {
        return (node != null && (node.value == key || searchRecursion(node.next, key)));
    }

    @Override
    public boolean search(int key) {
        return searchRecursion(getHead(), key);
    }
}
