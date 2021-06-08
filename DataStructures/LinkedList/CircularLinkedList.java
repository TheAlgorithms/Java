package LinkedList;

public class CircularLinkedList {
    class node{
        int data;
      
        node next;
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
        }
        else
        {
            node temp=head;
            while(temp.next!=head)
            {
                temp=temp.next;
            }
            temp.next=toAdd;
         
            toAdd.next=head;
           
        }
    }
    void addAtBegin(int data) // Better approach as Time Complexity is O(1)
    {
        node toAdd=new node(data);
        if(isEmpty())
        {
            head=toAdd;
            head=head.next;
        }
        else
        {
            toAdd.next=head.next;
            head.next=toAdd;
            int t=head.data;
            head.data=toAdd.data;
            toAdd.data=t;
            
        }
    }
    
    void print()
    {
        if(isEmpty())
        {
            System.err.println("Empty List");
        }
        else
        {   System.out.print(head.data+" ");
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
        CircularLinkedList obj=new CircularLinkedList();
        obj.add(10);
        obj.add(11);
        obj.add(12);
        obj.add(13);
        obj.addAtBegin(9);
        
        obj.print();
    }
}
