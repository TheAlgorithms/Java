class Main {
    
    //Linked list Node
    //Definition for singly-linked list.
    class Node {
        int val;
        Node next;
        Node(int data)
        {
            val = data;
            next = null;
        }
    }
  // head of list
    Node head; 
 
  //Given a key, deletes the firstoccurrence of key in the linked list 
  
    void deleteNode(int key)
    {
        // Store head node
        Node temp = head, prev = null;
 
        // If head should be deleted
        if (temp != null && temp.val == key) {
            head = temp.next; // Changed head
            return;
        }
 
        while (temp != null && temp.val != key) {
            prev = temp;
            temp = temp.next;
        }
 
        // If key was not present in linked list
        if (temp == null)
            return;
        prev.next = temp.next;
    }
 
    //Insert a new Node at front of the list. 
    public void push(int new_data)
    {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }
 
    
    public void printList()
    {
        Node node = head;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
 
    public static void main(String[] args)
    {
        Main ll = new Main();
 
        ll.push(5);
        ll.push(1);
        ll.push(3);
        ll.push(2);
 
        System.out.println("\nLinked list is:");
        ll.printList();
 
        ll.deleteNode(3); // Delete node with data 3
 
        System.out.println(
            "\nLinked List after Deletion of 1:");
        ll.printList();
    }
}


