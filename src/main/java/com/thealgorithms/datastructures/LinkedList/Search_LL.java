import java.util.*;
public class LinkedList{
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
    void addLast(int data){
         Node newNode=new Node(data);
        if(head==null){
            head=tail=newNode;
            return;
        }
        tail.next=newNode;
        tail=newNode;
    }
    int search(Node curr,int val,int i){
        if(curr==null){
            return -1;
        }
        if(curr.data==val){
            return i+1;
        }
        return search(curr.next,val,i+1);
    }
    public static void main(String args[]){
        LinkedList ll=new LinkedList();
        ll.addFirst(4);
        ll.addFirst(3);
        ll.addLast(2);
     
       System.out.println(ll.search(head,3,0));
       
    }
}
