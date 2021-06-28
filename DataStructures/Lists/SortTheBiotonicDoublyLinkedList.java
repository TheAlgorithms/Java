class SortTheBiotonicDoublyLinkedList
{ 
  
// a node of the doubly linked list 
static class Node  
{ 
    int data; 
    Node next; 
    Node prev; 
} 
  
// Function to reverse a Doubly Linked List 
static Node reverse( Node head_ref) 
{ 
    Node temp = null; 
    Node current = head_ref; 
  
    // swap next and prev for all nodes 
    // of doubly linked list 
    while (current != null) 
    { 
        temp = current.prev; 
        current.prev = current.next; 
        current.next = temp; 
        current = current.prev; 
    } 
  
    // Before changing head, check for the cases  
    // like empty list and list with only one node 
    if (temp != null) 
        head_ref = temp.prev; 
        return head_ref; 
} 
  
// Function to merge two sorted doubly linked lists 
static Node merge(Node first, Node second) 
{ 
    // If first linked list is empty 
    if (first == null) 
        return second; 
  
    // If second linked list is empty 
    if (second == null) 
        return first; 
  
    // Pick the smaller value 
    if (first.data < second.data) 
    { 
        first.next = merge(first.next, second); 
        first.next.prev = first; 
        first.prev = null; 
        return first; 
    }  
    else 
    { 
        second.next = merge(first, second.next); 
        second.next.prev = second; 
        second.prev = null; 
        return second; 
    } 
} 
  
// function to sort a biotonic doubly linked list 
static Node sort(Node head) 
{ 
    // if list is empty or if it contains  
    // a single node only 
    if (head == null || head.next == null) 
        return head; 
  
    Node current = head.next; 
  
    while (current != null)  
    { 
  
        // if true, then 'current' is the first node 
        // which is smaller than its previous node 
        if (current.data < current.prev.data) 
            break; 
  
        // move to the next node 
        current = current.next; 
    } 
  
    // if true, then list is already sorted 
    if (current == null) 
        return head; 
  
    // spilt into two lists, one starting with 'head' 
    // and other starting with 'current' 
    current.prev.next = null; 
    current.prev = null; 
  
    // reverse the list starting with 'current' 
    current = reverse(current); 
  
    // merge the two lists and return the 
    // final merged doubly linked list 
    return merge(head, current); 
} 
  
// Function to insert a node at the beginning 
// of the Doubly Linked List 
static Node push( Node head_ref, int new_data) 
{ 
    // allocate node 
    Node new_node = new Node(); 
  
    // put in the data 
    new_node.data = new_data; 
  
    // since we are adding at the beginning, 
    // prev is always null 
    new_node.prev = null; 
  
    // link the old list off the new node 
    new_node.next = (head_ref); 
  
    // change prev of head node to new node 
    if ((head_ref) != null) 
        (head_ref).prev = new_node; 
  
    // move the head to point to the new node 
    (head_ref) = new_node; 
    return head_ref; 
} 
  
// Function to print nodes in a given doubly  
// linked list 
static void printList( Node head) 
{ 
    // if list is empty 
    if (head == null) 
        System.out.println("Doubly Linked list empty"); 
  
    while (head != null) 
    { 
        System.out.print(head.data + " "); 
        head = head.next; 
    } 
} 
  
// Driver Code 
public static void main(String args[]) 
{ 
    Node head = null; 
  
    // Create the doubly linked list: 
    // 2<.5<.7<.12<.10<.6<.4<.1 
    head = push(head, 1); 
    head = push(head, 4); 
    head = push(head, 6); 
    head = push(head, 10); 
    head = push(head, 12); 
    head = push(head, 7); 
    head = push(head, 5); 
    head = push(head, 2); 
  
    System.out.println("Original Doubly linked list:n"); 
    printList(head); 
  
    // sort the biotonic DLL 
    head = sort(head); 
  
    System.out.println("\nDoubly linked list after sorting:n"); 
    printList(head); 
} 
} 
