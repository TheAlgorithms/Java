package data_structures.singlylinkedlist;

//Example
public class SinglyLinkedList {
    public static void main(String args[]) {
        LinkedList myList = new LinkedList();

        System.out.println(myList.isEmpty()); //Will print true

        myList.insertHead(5);
        myList.insertHead(7);
        myList.insertHead(10);

        myList.display(); // 10(head) --> 7 --> 5

        myList.deleteHead();

        myList.display(); // 7(head) --> 5
    }
}
