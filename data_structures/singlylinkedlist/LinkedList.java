package data_structures.singlylinkedlist;

/*
 * A linked list is similar to an array, it holds values. However, links in a linked list do not have indexes.
 * With a linked list you do not need to predetermine it's size as it grows and shrinks as it is edited.
 * This is an example of a singly linked list. Elements can only be added/removed at the head/front of the list.
*/
public class LinkedList {
    private Link head; //Head refers to the front of the list

    public LinkedList() {
        head = null;
    }

    public void insertHead(int x) {    //Insert an element at the head
        Link newLink = new Link(x); //Create a new link with a value attached to it
        newLink.next = head;        //Set the new link to point to the current head
        head = newLink;            //Now set the new link to be the head
    }

    public void deleteHead() { //Delete the element at the head
        head = head.next; //Make the second element in the list the new head, the Java garbage collector will later remove the old head
    }

    public boolean isEmpty() { //Returns true if list is empty
        return (head == null);
    }

    public void display() { //Prints contents of the list
        Link current = head;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }

    }
}
