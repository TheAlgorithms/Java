import java.util.*;
import java.util.Scanner;
public class LinkedList {

    static Node head; // head of list

    static class Node // Linked list Node
    {
        int data; //to store value
        Node next; //pointer
        Node(int d) {
            data = d;
            next = null;
        }
    }

    static int countNodes(Node ptr) //Function to count the number of nodes present
    {
        int count = 0;
        while (ptr != null) {
            ptr = ptr.next;
            count++;
        }
        return count;
    }

    static public void push(int new_data) // Function to inserts a new Node at front of the list 
    {

        Node new_node = new Node(new_data); //Allocate a pointer/node and store the data

        new_node.next = head; // make next of new Node as head 

        head = new_node; // Move the head to point to new Node.
    }

    static void printList(Node head, int total_nodes) //function to traverse through the list and print all data values
    {
        Node curr = head;
        int count = 0;
        while (count < total_nodes) {
            count++;
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    static Node makeloop(Node head_ref, int k) {
        Node temp = head_ref;
        int count = 1;
        while (count < k) //traverrse the list till point is found
        {
            temp = temp.next;
            count++;
        }

        Node connected_point = temp;

        while (temp.next != null) // traverse remaining nodes
            temp = temp.next;

        temp.next = connected_point; //connect last node to k-th element
        return head_ref;
    }

    static boolean detectLoop(Node h) //Function to detect loop, retuens true if loop is in linked list else returns false.
    {
        HashSet < Node > traverse = new HashSet < Node > ();
        while (n != null) {

            if (traverse.contains(n)) //if the hash a;ready contains a record of the node encountered true is returned as a loop isdetected
                return true;

            traverse.add(n);
            n = n.next;
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedList l = new LinkedList();

        Scanner sc = new Scanner(System.in);

        print("Enter elements in the list, to stop entering press any alphabetical key");
        while (true) {
            try {
                i = sc.nextInt();
                l.push(i);
            } catch (Exception e) {
                System.out.println("Creating loop for run");
            }
        }
        System.out.println("Enter the location to generate loop");
        int k = sc.nextInt()
        System.out.print("Given list");
        printList(head, total_nodes);
        head = makeloop(head, k);
        System.out.print("Modified list with loop");
        printList(head, total_nodes);

        if (detectLoop(head))
            System.out.println("Loop found");
        else
            System.out.println("No Loop");
    }
}
