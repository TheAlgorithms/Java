package LinkedList;

public class CircularDoublyLinkedList {
    class node{
        int data;
        node next;
        node prev;
        node(int data)
        {
         this.data=data;
        }
    }
    node head;
    boolean isEmpty()
    {
        return head==null;
    }
    void add(int data)
    {
        node toAdd=new node(data);
     if(isEmpty())
     {
        head=toAdd;
        head.next=head;
        head.prev=head;


     }
     else
     {
         node temp=head;
         while(temp.next!=head)
         {
             temp=temp.next;
         }
         temp.next=toAdd;
         toAdd.prev=temp;
         toAdd.next=head;
         head.prev=toAdd;
     }
    }
    void print()
    {
        if(isEmpty())
        {
            System.err.println("Empty List");
        }
        else
        {
            System.out.print(head.data+" ");
            node temp=head.next;
            while(temp!=head)
            {
                System.out.print(temp.data+" ");
                temp=temp.next;
            }
            
        }
    }
    public static void main(String s[])
    {
        CircularDoublyLinkedList obj=new CircularDoublyLinkedList();
        obj.add(12);
        obj.add(13);
        obj.add(14);
        obj.add(15);
        obj.print();
    }
    }
