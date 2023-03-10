package com.thealgorithms.datastructures.lists;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

public class singlyLinkedListTest {
    @Test
    public void reverseList () {

        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.insert(5);
        singlyLinkedList.insert(4);
        singlyLinkedList.insert(6);
        singlyLinkedList.insert(8);
        singlyLinkedList.insert(1);
        singlyLinkedList.reverseList(singlyLinkedList.getHead());


        assertEquals(1, singlyLinkedList.getHead().value);
    }

}
