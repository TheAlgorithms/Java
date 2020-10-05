import java.util.*; 
import java.io.*; 
  
// a node of the doubly linked list 
class Node  
{ 
    int data; 
    Node next, prev; 
} 
  
class RemovingAllOccuranceOfGivenKeyUsingDoublyLinkedList 
{ 
    /* Function to delete a node in a Doubly Linked List.  
        head_ref --> pointer to head node pointer.  
        del --> pointer to node to be deleted. */
    static Node deleteNode(Node head, Node del)  
    { 
        // base case 
        if (head == null || del == null) 
            return null; 
  
        /* If node to be deleted is head node */
        if (head == del) 
            head = del.next; 
  
        /* Change next only if node to be deleted  
            is NOT the last node */
  
        if (del.next != null) 
            del.next.prev = del.prev; 
  
        /* Change prev only if node to be deleted  
            is NOT the first node */
        if (del.prev != null) 
            del.prev.next = del.next; 
  
        del = null; 
  
        return head; 
    }  
          
    /* function to delete all occurrences of the given  
        key 'x' */
    static Node deleteAllOccurOfX(Node head, int x) 
    { 
        // if list is empty 
        if (head == null) 
            return null; 
  
        Node current = head; 
        Node next; 
  
        /* traverse the list up to the end */
        while (current != null) 
        { 
            // if node found with the value 'x'  
            if (current.data == x) 
            { 
                      
                /* save current's next node in the  
                pointer 'next' */
                next = current.next; 
  
                /* delete the node pointed to by  
                'current' */
                head = deleteNode(head, current); 
  
                /* update current */
                current = next; 
            } 
  
            /* else simply move to the next node */
            else
                current = current.next; 
  
        } 
  
        return head; 
  
    } 
  
    /* Function to insert a node at the beginning  
        of the Doubly Linked List */
    static Node push (Node head, int new_data) 
    { 
        // allocate node  
        Node new_node = new Node(); 
              
        // put in the data 
        new_node.data = new_data; 
  
        /* since we are adding at the beginning,  
        prev is always NULL */
        new_node.prev = null; 
  
        // link the old list off the new node 
        new_node.next = head; 
  
        // change prev of head node to new node 
        if (head != null) 
            head.prev = new_node; 
  
        // move the head to point to the new node 
        head = new_node; 
          
        return head; 
    } 
  
    /* Function to print nodes in a given doubly  
        linked list */
    static void printList (Node temp) 
    { 
        if (temp == null) 
            System.out.print("Doubly Linked list empty"); 
  
        while (temp != null)  
        { 
                System.out.print(temp.data + " "); 
                temp = temp.next; 
        } 
    }  
  
    // Driver code 
    public static void main(String args[]) 
    { 
        // Start with the empty list 
        Node head = null; 
  
        /* Create the doubly linked list:  
        2<->2<->10<->8<->4<->2<->5<->2 */
        head = push(head, 2); 
        head = push(head, 5); 
        head = push(head, 2); 
        head = push(head, 4); 
        head = push(head, 8); 
        head = push(head, 10); 
        head = push(head, 2); 
        head = push(head, 2); 
  
        System.out.println("Original Doubly linked list: "); 
        printList(head);  
  
        int x = 2; 
              
        // delete all occurrences of 'x' 
        head = deleteAllOccurOfX(head, x); 
        System.out.println("\nDoubly linked list after deletion of" + x +":"); 
        printList(head); 
    } 
}
