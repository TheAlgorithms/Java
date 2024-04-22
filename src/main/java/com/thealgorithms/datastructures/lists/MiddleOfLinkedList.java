/* THE EXPLAINATION OF THE CODE IN STEPS 
AUTHOR = Sheryar-bit 
profile = https://github.com/Sheryar-bit
# `Node` Class:
1. Node Definition:
   - Represents a node in a linked list.
   - Each node has an integer `data` and a reference `next` to the next node.

2. Constructor (`Node(int data)`):
   - Initializes a node with the given `data`.
   - Sets `next` to `null` initially.

# `LinkedList` Class:
1. Fields:
   - `head`: Points to the first node of the linked list.

2. Constructor (`LinkedList()`):
   - Initializes an empty linked list with `head` set to `null`.

3. `insert(int data)` Method:
   - Adds a new node with the given `data` to the end of the linked list.
   - If the list is empty (`head` is `null`), sets `head` to the new node.
   - Otherwise, traverses the list to find the last node and appends the new node.

4. `findMiddle()` Method:
   - Finds the middle node of the linked list.
   - Uses two pointers (`slow` and `fast`) to achieve this:
     - `slow` pointer moves one node at a time.
     - `fast` pointer moves two nodes at a time.
   - When `fast` reaches the end (`fast` or `fast.next` is `null`), `slow` will be at the middle node:
     - If the list has an odd number of nodes, `slow` will be exactly at the middle.
     - If the list has an even number of nodes, `slow` will be the second middle node.

# `Main` Class (Entry Point):
1. `main(String[] args)` Method:
   - Creates a new `LinkedList` object (`list`).
   - Reads input from the user to determine the number of elements (`n`) and the values of these elements.
   - Inserts each element into the linked list using the `insert` method.
   - Calls `findMiddle` to retrieve the middle node of the linked list.
   - Prints the data value of the middle node if it exists, or indicates that the list is empty.

# Program Flow:
1. Initialization:
   - Create a `LinkedList` object (`list`).
   - Initialize the list with nodes based on user input.

2. Insertion:
   - Use the `insert` method to add nodes to the end of the list.

3. Middle Node Identification:
   - Use the `findMiddle` method to locate the middle node of the list.
   - This method efficiently determines the middle node using two pointers (`slow` and `fast`) to traverse the list.

4. Output:
   - Display the data value of the middle node if the list is not empty.
   - If the list is empty, indicate that the list is empty.

The code demonstrates basic linked list operations (insertion) and a common algorithm to find the middle node of a linked
list using two pointers. The overall design allows for dynamic creation of linked lists and efficient identification of the
middle node without storing the entire list in memory.*/

package com.thealgorithms.datastructures.lists;

import java.util.Scanner;
public class MiddleOfLinkedList {
    
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    public LinkedList() {
        this.head = null;
    }

    // Method to insert a new node at the end of the linked list
    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Method to find the middle node of the linked list
    public Node findMiddle() {
        if (head == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;

        // Move 'fast' pointer by two steps and 'slow' pointer by one step
        // When 'fast' reaches the end, 'slow' will be at the middle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList list = new LinkedList();

        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            list.insert(num);
        }

        Node middleNode = list.findMiddle();
        if (middleNode != null) {
            System.out.println("Middle of the linked list: " + middleNode.data);
        } else {
            System.out.println("The linked list is empty.");
        }

        scanner.close();
    }
}

}