package LinkedList.singlyLinkedList;

public class nthFromLast {
    static Node head;
    static class Node{
        int data;
        Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    void print(Node head){

        while(head != null){
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("Null");
    }

    Node nthNode(Node head, int n){
        if(head == null) return null;
        int count  = 0;
        Node refPtr = head;
        Node mainPtr = head;
        while(count < n){
            refPtr = refPtr.next;
            count++;
        }

        if(refPtr == null) return head.next;

        while(refPtr.next != null){
            mainPtr = mainPtr.next;
            refPtr = refPtr.next;
        }

        mainPtr.next = mainPtr.next.next;

        return head;
    }


    public static void main(String[] args) {
        nthFromLast list = new nthFromLast();
        list.head = new Node(1);
        list.head.next = new Node(2);
        list.head.next.next = new Node(3);
        list.head.next.next.next = new Node(4);
        list.head.next.next.next.next = new Node(5);

        list.print(list.head);
        list.print(list.nthNode(list.head, 2));
    }
}
