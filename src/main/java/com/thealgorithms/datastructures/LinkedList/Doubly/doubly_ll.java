import java.util.*;

public class DoubelLL
{
    static class Node{
        int data;
        Node next;
        Node prev;
        
        Node(int data){
            this.data=data;
            next=prev=null;
        }
    }
    static Node head;
    static Node tail;
    public void addFirst(int data){
        Node newNode=new Node(data);
        if(head==null){
            head=tail=newNode;
            return;
        }
        newNode.next=head;
        head.prev=newNode;
        head=newNode;
    }
    static void removeLast(){
        if(head==null){
          System.out.println("Empty...");
          return;
        }
        else if(head.next==null){
            head=tail=null;
            return;
        }
        Node curr=head;
        while(curr.next!=tail){
            curr=curr.next;
        }
        curr.next.prev=null;
        curr.next=null;
        tail=curr;
    }
    static void removeFirst(){
         if(head==null){
          System.out.println("Empty...");
          return;
        }
        else if(head.next==null){
            head=tail=null;
            return;
        }
        head=head.next;
        head.prev=null;
        
        
    }
   
    static void show(){
        Node curr=head;
        while(curr!=null){
            System.out.print(curr.data+" ");
            curr=curr.next;
        }
        System.out.println();
    }
    static void printInReverse(){
        Node curr=tail;
        while(curr!=null){
            System.out.print(curr.data+" ");
            curr=curr.prev;
        }
    }
	public static void main(String[] args) {
		DoubelLL dl=new DoubelLL();
		dl.addFirst(5);
		dl.addFirst(4);
		dl.addFirst(3);
		dl.addFirst(2);
		dl.addFirst(1);
		show();
		removeLast();
		show();
		removeFirst();
		show();
		System.out.println("Now print in Reverse Order...");
		printInReverse();
	}
}
