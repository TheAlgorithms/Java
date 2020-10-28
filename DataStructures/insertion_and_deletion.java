package duo;
import java.util.*;
public class linkedlist {
   static class Node{
   int data;
   Node next;
   public Node(int data)
   {
   this.data=data;
   this.next=null;
   }
   }  
   public Node head=null;
   public Node tail=null;
   public int size;
   
   //creating node
   void addnode(int data)
   {
   Node node = new Node(data);
   //checking 
   if(head==null)
   {
   head=node;
   tail=node;
   }
   else
   {
   tail.next=node;
   tail=node;
   }
   size++;
   }
   
   //inserting in front of node
   void addhead(int data)
   {
   Node node = new Node(data);
   //checking
   if(head==null)
   {
   head=node;
   tail=node;
   }
   else
   {
   Node temp=head;
   head=node;
   head.next=temp;
   }
   }
   
   //adding in tail
   void addtail(int data)
   {
   Node node = new Node(data);
   
   //checking
   if(head==null)
   {
   head=node;
   tail=node;
   }
   else
   {
   Node temp=head;
   while(temp.next!=null)
   {
   temp=temp.next;
   }
   temp.next=node;
   }
   }
   
   //deleting from head
   void deletehead()
   {
   Node temp=head;
   head=temp.next;
   }
   
   //deleting from tail
   void deletetail()
   {
   Node temp=head;
   while(temp.next!=tail)
   {
   temp=temp.next;
   }
   temp.next=null;
   tail=temp;
   }
   
   //inserting in middle position
   void insertAtMiddle(int data)
   {
   Node node = new Node(data);
   if(head==null)
   {
   head=node;
   tail=node;
   }
   else
   {
   Node temp,current;
   int count = (size%2==0)?(size/2):((size+1)/2);
   temp=head;
   current=null;
   
   //looping
   for(int i=0;i<count;i++)
   {
   current=temp;
   temp=temp.next;
   }
   current.next=node;
   node.next=temp;
   }
   size++;
   }
   
   //deleting from middle
   void deleteFromMiddle()
   {
   Node n, temp;
   int count = (size%2==0)?(size/2):((size+1)/2);
   n=head;
   temp=head;
   
   //loopint till count
   for(int i=0;i<count-1;i++)
   {
   n=n.next;
   temp=temp.next;
   }
   temp=n.next;
   n.next=temp.next;
   temp=null;
   }
   
   
   //deleting from index
   void deleteFromIndex(int index)
   {
   Node n=head;
   Node temp=null;
   for(int i=0;i<index-1;i++)
   {
   n=n.next;
   }
   temp=n.next;
   n.next=temp.next;
   temp=null;
   }
   //inserting at given index
   void insertAt(int index, int data)
   {
   Node node = new Node(data);
   
   //checking
   if(head==null)
   {
   head=node;
   tail=node;
   }
   else
   {
   Node n;
   n=head;
   for(int i=0;i<index-1;i++)
   {
       n=n.next;
   }
   node.next=n.next;
   n.next=node;
   }
   }
   
   //display method 
   void display()
   {
   Node current=head;
   if(head==null)
   {
       System.out.println("list is empty");
       return;
   }
   while(current!=null)
   {
       System.out.print(current.data+" ");
       current=current.next;
   }
   }
   
    public static void main(String[] args) {
        linkedlist list = new linkedlist();
        list.addnode(10);
        list.addnode(11);
        list.addnode(12);
        list.addnode(13);
        System.out.println("Orginal data : ");
        list.display();
        System.out.println();
        
        System.out.println("Deleting tail which is 13 : ");
        list.deletetail();
        list.display();
        System.out.println();
        
        System.out.println("Adding 5 in head position : ");
        list.addhead(5);
        list.display();
        System.out.println();
        
        System.out.println("Adding 6 in tail position : ");
        list.addtail(6);
        list.display();
        System.out.println();
        
        System.out.println("deleting head which is 5 : ");
        list.deletehead();
        list.display();
        System.out.println();
        
        System.out.println("Inserting 55 in middle which is in 12 position : ");
        list.insertAtMiddle(55);
        list.display();
        System.out.println();
        
        System.out.println("Deleting from middle which is 12 in this case : ");
        list.deleteFromMiddle();
        list.display();
        System.out.println();
        
        System.out.println("Inserting 35 at index 2 : ");
        list.insertAt(2, 35);
        list.display();
        System.out.println();
        
        System.out.println("Deleting at index 4 which is 6 : ");
        list.deleteFromIndex(4);
        list.display();
        System.out.println();
        
    }
}
