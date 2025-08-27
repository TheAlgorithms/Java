import java.util.*;

public class LinkedList
{   
    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data=data;
            next=null;
        }
    }
    static Node head;
    static Node tail;
    void add(int data){
        Node newNode=new Node(data);
        if(head==null){
          head=tail=newNode;
          return;
        }
        newNode.next=head;
        head=newNode;
    }
    void show(){
      Node curr=head;
      while(curr!=null){
          System.out.print(curr.data+" ");
          curr=curr.next;
      }
     System.out.println();
    }
    void segregate(){
       Node odd=head;
       Node even=head.next;
       odd.next=even.next;
       odd=odd.next;
       System.out.println(odd.data);
    }
	public static void main(String[] args) {
		LinkedList ll=new LinkedList();
		ll.add(5);
		ll.add(4);
		ll.add(3);
		ll.add(2);
		ll.add(1);
		ll.show();
		ll.segregate();
	}
}
