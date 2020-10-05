import java.util.*; 
  
class DoublyCircularLinkedList
{  
  
static Node start; 
  
// Structure of a Node  
static class Node  
{  
    int data;  
    Node next;  
    Node prev;  
};  
  
// Function to insert at the end  
static void insertEnd(int value)  
{  
    // If the list is empty, create a single node  
    // circular and doubly list  
    if (start == null)  
    {  
        Node new_node = new Node();  
        new_node.data = value;  
        new_node.next = new_node.prev = new_node;  
        start = new_node;  
        return;  
    }  
  
    // If list is not empty  
  
    /* Find last node */
    Node last = (start).prev;  
  
    // Create Node dynamically  
    Node new_node = new Node();  
    new_node.data = value;  
  
    // Start is going to be next of new_node  
    new_node.next = start;  
  
    // Make new node previous of start  
    (start).prev = new_node;  
  
    // Make last preivous of new node  
    new_node.prev = last;  
  
    // Make new node next of old last  
    last.next = new_node;  
}  
  
// Function to insert Node at the beginning  
// of the List,  
static void insertBegin(int value)  
{  
    // Pointer points to last Node  
    Node last = (start).prev;  
  
    Node new_node = new Node();  
    new_node.data = value; // Inserting the data  
  
    // setting up previous and next of new node  
    new_node.next = start;  
    new_node.prev = last;  
  
    // Update next and previous pointers of start  
    // and last.  
    last.next = (start).prev = new_node;  
  
    // Update start pointer  
    start = new_node;  
}  
  
// Function to insert node with value as value1.  
// The new node is inserted after the node with  
// with value2  
static void insertAfter(int value1,  
                                    int value2)  
{  
    Node new_node = new Node();  
    new_node.data = value1; // Inserting the data  
  
    // Find node having value2 and next node of it  
    Node temp = start;  
    while (temp.data != value2)  
        temp = temp.next;  
    Node next = temp.next;  
  
    // insert new_node between temp and next.  
    temp.next = new_node;  
    new_node.prev = temp;  
    new_node.next = next;  
    next.prev = new_node;  
}  
  
static void display()  
{  
    Node temp = start;  
  
    System.out.printf("\nTraversal in forward direction \n");  
    while (temp.next != start)  
    {  
        System.out.printf("%d ", temp.data);  
        temp = temp.next;  
    }  
    System.out.printf("%d ", temp.data);  
  
    System.out.printf("\nTraversal in reverse direction \n");  
    Node last = start.prev;  
    temp = last;  
    while (temp.prev != last)  
    {  
        System.out.printf("%d ", temp.data);  
        temp = temp.prev;  
    }  
    System.out.printf("%d ", temp.data);  
}  
  
/* Driver code*/
public static void main(String[] args)  
{  
    /* Start with the empty list */
    Node start = null;  
  
    // Insert 5. So linked list becomes 5.null  
    insertEnd(5);  
  
    // Insert 4 at the beginning. So linked  
    // list becomes 4.5  
    insertBegin(4);  
  
    // Insert 7 at the end. So linked list  
    // becomes 4.5.7  
    insertEnd(7);  
  
    // Insert 8 at the end. So linked list  
    // becomes 4.5.7.8  
    insertEnd(8);  
  
    // Insert 6, after 5. So linked list  
    // becomes 4.5.6.7.8  
    insertAfter(6, 5);  
  
    System.out.printf("Created circular doubly linked list is: ");  
    display();  
}  
} 
