import java.util.*;

public class LinekedList
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
     static void addLast(int data){
         Node newNode=new Node(data);
         if(head==null){
             head=tail=newNode;
             return;
         }
         tail.next=newNode;
         tail=newNode;
     }
     static void show(Node head){
         Node curr=head;
         while(curr!=null){
             System.out.print(curr.data+" ");
             curr=curr.next;
         } 
         System.out.println();
     }
     static Node getMid(Node head){
         Node slow=head;
         Node fast=head.next;
         
         while(fast!=null && fast.next!=null){
             slow=slow.next;
             fast=fast.next.next;
         }
         return slow;
     }
     static Node reverse(Node mid){
         
         Node curr=mid;
         Node prev=null;
         while(curr!=null){
             Node temp=curr.next;
             curr.next=prev;
             prev=curr;
             curr=temp;
         }
         return prev;
     }
	public static void main(String[] args) {
	   LinekedList ll=new LinekedList();
	   ll.addLast(1);
	   ll.addLast(2);
	   ll.addLast(3);
	   ll.addLast(4);
	  ll.addLast(5);
	   
	   Node mid=getMid(head);
	   Node head2=reverse(mid);
	   
	   Node head1=head;
	   Node dummy=new Node(-1);
	   Node temp=dummy;
	   while(head1!=mid && head2!=null){
	       temp.next=head1;
	       temp=temp.next;
	       head1=head1.next;
	       temp.next=head2;
	       head2=head2.next;
	       temp=temp.next;
	   }
	   show(dummy.next);
	}
}
