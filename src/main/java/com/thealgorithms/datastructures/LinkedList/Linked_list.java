import java.util.*;
public class LinkedListt{
    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data=data;
            next=null;
        }
    }
    public static Node head;
    public static Node tail;
    public void addFirst(int data){
        Node newNode=new Node(data);
        if(head==null){
            head=tail=newNode;
            return;
        }

        newNode.next=head;
        head=newNode;
    }
    public void addlast(int data){
        Node newNode=new Node(data);
        if(head==null){
            head=tail=newNode;
            return;
        }
//        while(tail.next!=null){
//            tail=tail.next;
//        }
        tail.next=newNode;
        tail=newNode;
    }
    public  void addMiddle(int data,int index){
        Node newNode=new Node(data);
        int i=1;
        Node curr=head;
        while(i<index){
            curr=curr.next;
            i++;
        }
        newNode.next=curr.next;
        curr.next=newNode;
    }
    public void removeFirst(){
        if(head==null){
            System.out.println("LL is empty()");
            return;
        }
        else if (head.next==null){
            head=tail=null;
            return;
        }
        System.out.println("deleted data:"+head.data);
        head=head.next;
        return;
    }
    public  void removeLast(){
        if(head==null){
            System.out.println("LL is empty()");
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
        System.out.println("deleted data:"+tail.data);
        curr.next=null;
        tail=curr;
    }
    public void show(){
        Node curr=head;
        while(curr!=null){
            System.out.print(curr.data+" ");
            curr=curr.next;
        }
        System.out.println();
    }

    public int size(){
        Node curr=head;
        int count=0;
        while(curr!=null){
            count++;
            curr=curr.next;
        }
        return count;
    }

    public static  void main(String args[]){
       LinkedListt ll=new LinkedListt();
       ll.addFirst(1);
       ll.addFirst(2);
       ll.addFirst(3);
       ll.addlast(4);
       ll.addlast(5);
       ll.addlast(6);
       ll.show();
       int siz=ll.size();
       System.out.println("Size of LinkedList:"+siz);
      // int index=siz%2==0?siz/2 : siz/2 +1;
      ll.addMiddle(55,siz/2);
       ll.show();
      System.out.println("Remove the first data");
      ll.removeFirst();
      ll.show();
      ll.removeLast();
      ll.show();

    }
}
