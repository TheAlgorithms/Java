package main.java.com.thealgorithms.datastructures.lists;

import java.util.Scanner;



public class FindLinkedListCycle {
    
    
    Node head; //first Node of linked list
    int size; //number of elements within the linked list

    public class Node {
        int data; //each node of linked list has data and pointer to next Node in list
        Node next;
    
        public Node(int _data) { //constructor for Node given data
            this.data = _data;
            this.next = null;
        }
    }

    FindLinkedListCycle() { //Initially, list has no nodes
        head = null;
        size = 0;
    }

    public Node getHead() { //returns first Node in linked list
        return head;
    }

    public Node getTail() { //returns last Node in linked list
        if(this.head == null) {
            return null;
        } else {
            Node temp = this.head;
            while(temp.next != null) { //tail will have a next value of null
                temp = temp.next;
            }
            return temp;
        }
    }

    public int getSize() { //returns size of linked list
        return size;
    }

    public void addNode(int _data) {
        if(getSize() == 0) {
            this.head = new Node(_data); //set head Node if no nodes in list
            this.size++;
        } else {
            Node temp = getTail();
            temp.next = new Node(_data); //add new node to end of linked list
            this.size++;
        }
    }

    public boolean findCycle(Node head) {
        if(head == null) {
            return false;
        }
        Node slow_ptr = head; //have two pointers, one slow and one fast
        Node fast_ptr = head;

        while(slow_ptr.next != null && fast_ptr.next != null && fast_ptr.next.next != null) { //need to check .next of slow and .next.next of fast since fast pointer skips two every time
            slow_ptr = slow_ptr.next;
            fast_ptr = fast_ptr.next.next; //moves faster than slow_ptr
            if(slow_ptr == fast_ptr) { //if they become equal, the slow pointer caught up to fast pointer which is only possible with a cycle
                return true;
            }
        }

        return false;
    }

    public static void main(String args[]) { //sample test code for this class
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("How big is your list?");
        int length = scanner.nextInt();
        System.out.println("Please enter the data.");
        FindLinkedListCycle list = new FindLinkedListCycle(); //initialize new linked list

        while(length > 0) {
            list.addNode(scanner.nextInt()); //values don't matter much, just testing for cycles
            length--;
        }

        System.out.println("Would you like to add a cycle? Y or N"); //decide if you want to test for cycles or not
        String response = scanner.nextLine();
        response = scanner.nextLine();
        if(response.equals("Y")) {
            System.out.println("At which index?"); //can choose which index the cycle is made at
            int index = scanner.nextInt();
            if(index < 0 || index > list.getSize() - 1) {
                System.out.println("Invalid Index");
            } else {
                int count = 0; 
                Node tail = list.getTail();
                Node temp = list.getHead();
                while(count != index) {
                    temp = temp.next;
                    count++;
                }
                tail.next = temp;

                System.out.println("Cycle Made Successfully\nResult of findCycle: " + list.findCycle(list.getHead())); //result should always be true
            }
        } else if(response.equals("N")) {
            System.out.println("Result of findCycle: " + list.findCycle(list.getHead())); //result should always be false
        } else {
            System.out.println("Invalid Input");
        }
    }
}




