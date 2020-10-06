package singlyLinkedLists;

public class SinglyLinkedList{
    public Node first;

    public SinglyLinkedList(){}


   //to check if the list is empty
    public boolean isEmpty() {return (first == null);}

    //to insert at the begginning of the list
    public void insertFirst(int data){
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = first;
        first= newNode;
    }

    //delete from the beginning
    public Node deleteFirst(){
    Node temp = first;
    first = temp.next;
    return temp;

    }

    //displayList Content from first to last
    public void displayList(){
        System.out.println("List (first --> last)");

        Node current = first;
        while(current != null){
            current.displayNode();
            current = current.next;
        }

        System.out.println();
    }

    //display total length of the list
    public int listLength(Node aNode){
        int length = 0;
        Node currentNode = aNode;

        while(currentNode != null){
            length++;
            currentNode = currentNode.next;
        }

        return length;
    }

}