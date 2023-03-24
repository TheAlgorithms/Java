package com.thealgorithms.datastructures.lists;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SinglyLinkedListTest {

    /**
     * Initialize a list with natural order values with pre-defined length
     * @param length
     * @return linked list with pre-defined number of nodes
     */
    private SinglyLinkedList createSampleList(int length) {
        List<Node> nodeList = new ArrayList<>();
        for (int i = 1; i <= length; i++) {
            Node node = new Node(i);
            nodeList.add(node);
        }

        for (int i = 0; i < length - 1; i++) {
            nodeList.get(i).next = nodeList.get(i+1);
        }

        return new SinglyLinkedList(nodeList.get(0), length);
    }

    @Test
    void detectLoop() {
        //List has cycle
        Node firstNode = new Node(1);
        Node secondNode = new Node(2);
        Node thirdNode = new Node(3);
        Node fourthNode = new Node(4);

        firstNode.next = secondNode;
        secondNode.next = thirdNode;
        thirdNode.next = fourthNode;
        fourthNode.next = firstNode;

        SinglyLinkedList listHasLoop = new SinglyLinkedList(firstNode, 4);
        assertTrue(listHasLoop.detectLoop());

        SinglyLinkedList listHasNoLoop = createSampleList(5);
        assertFalse(listHasNoLoop.detectLoop());
    }

    @Test
    void middle() {
        int oddNumberOfNode = 7;
        SinglyLinkedList list = createSampleList(oddNumberOfNode);
        assertEquals(oddNumberOfNode/2 + 1, list.middle().value);
        int evenNumberOfNode = 8;
        list = createSampleList(evenNumberOfNode);
        assertEquals(evenNumberOfNode/2, list.middle().value);

        //return null if empty
        list = new SinglyLinkedList();
        assertNull(list.middle());

        //return head if there is only one node
        list = createSampleList(1);
        assertEquals(list.getHead(), list.middle());
    }

    @Test
    void swap() {
        SinglyLinkedList list = createSampleList(5);
        assertEquals(1, list.getHead().value);
        assertEquals(5, list.getNth(4));
        list.swapNodes(1,5);
        assertEquals(5, list.getHead().value);
        assertEquals(1, list.getNth(4));
    }

    @Test
    void clear() {
        SinglyLinkedList list = createSampleList(5);
        assertEquals(5, list.size());
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void search() {
        SinglyLinkedList list = createSampleList(10);
        assertTrue(list.search(5));
        assertFalse(list.search(20));
    }

    @Test
    void deleteNth() {
        SinglyLinkedList list = createSampleList(10);
        assertTrue(list.search(7));
        list.deleteNth(6); //Index 6 has value 7
        assertFalse(list.search(7));
    }
}