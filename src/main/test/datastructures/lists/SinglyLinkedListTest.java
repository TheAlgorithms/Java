package datastructures.lists;

import org.junit.Assert;

class SinglyLinkedListTest {
    public static void main(String args[]) {
        SinglyLinkedList myList = new SinglyLinkedList();
        Assert.assertTrue(myList.isEmpty());
        myList.insertHead(5);
        myList.insertHead(7);
        myList.insertHead(10);
        Assert.assertFalse(myList.isEmpty());
        myList.display(); // 10(head) --> 7 --> 5
        myList.deleteHead();
        myList.display(); // 7(head) --> 5
    }
}