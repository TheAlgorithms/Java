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
    void addFirst(int data){
        Node newNode=new Node(data);
        if(head==null){
            head=tail=newNode;
            return;
        }
        newNode.next=head;
        head=newNode;
    }
    public Node findMid(){
        Node slow=head;
        Node fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    Node reverseMid(Node mid){
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
    void show(Node prev){
        Node curr=prev;
        while(curr!=null){
            System.out.print(curr.data+"-->");
            curr=curr.next;
        }
         System.out.println();
    }
    
    
    
	public static void main(String[] args) {
	   LinkedList li=new LinkedList();
	   li.addFirst(1);
	   li.addFirst(2);
	   li.addFirst(3);
	   li.addFirst(4);
	   li.addFirst(5);
	 //  li.show();
	   Node mid=li.findMid();
	   Node prev=li.reverseMid(mid);//2nd half ka head prev hi hoga
	   li.show(prev);
	   
	   
	   
	   
	}
}
