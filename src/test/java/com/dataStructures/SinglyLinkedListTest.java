package com.dataStructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SinglyLinkedListTest {
    @Test
    void testSinglyLinkedList() {
        SinglyLinkedList myList = new SinglyLinkedList();

        assert myList.isEmpty();

        myList.insertHead(5);
        myList.insertHead(7);
        myList.insertHead(10);

        myList.display(); // 10 -> 7 -> 5

        myList.deleteHead();

        myList.display(); // 7 -> 5

        myList.insertNth(11, 2);

        myList.display(); // 7 -> 5 -> 11

        myList.deleteNth(1);

        myList.display(); // 7-> 11
    }
}