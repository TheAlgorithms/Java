package LinkedList;

public class deletenodesofDoublyLL {
    class node{
        int data;
        node next;
        node prev;
        node(int data){
            this.data=data;
        }
    }
    node head;
    void add(int data){
        node toAdd=new node(data);
        if(isEmpty()){
            head=toAdd;
        }
        else
        {
            node temp=head;
            while(temp.next!=null)
            {
                temp=temp.next;
            }
            temp.next=toAdd;
            toAdd.prev=temp;
            toAdd.next=null;
        }
    }
    void print()
    {
        if(isEmpty())
        {
            System.err.println("The list is empty");
        }
        else
        {
            node temp=head;
            while(temp!=null)
            {
                System.out.print(temp.data+" ");
                temp=temp.next;
            }
        }
    }
    void deleteHead()
    {
        if(isEmpty())
        {
            System.err.println("Cannot delete empty node");
        }
        else
        {
            head=head.next;
            head.prev=null;
        }
    }
    int length()
    {
        if(isEmpty())
        {
            return 0;
        }
        else
        {
            int count=1;
            node temp=head;
            while(temp!=null)
            {
               count++;
               temp=temp.next;
            }
            return count;
        }
    }
    void deleteNode(int pos)
    {    if(isEmpty())
        {
            System.err.println("Cannot delete empty list");
        }
        else if(pos==1)
        {
            deleteHead();
        }
        else if(pos>length())
        {
            System.err.println("Given position is bigger than the lenght of the list");
        }
        else if(pos==length())
        {
            deleteLastNode();
        }
      else{  
          node temp=head;
        int count=1;
        while(count!=pos-1)
        {
            temp=temp.next;
            count++;
        }
        temp.next.next.prev=temp;
        temp.next=temp.next.next;
        
    }
    }
    void deleteLastNode()
    {
        if(isEmpty())
        {
            System.err.println("Cannot delete empty node");
        }
        else
        {
            node temp=head;
            while(temp.next!=null)
            {
                temp=temp.next;
            }
            temp=temp.prev;
            temp.next=null;
        }
    }
    boolean isEmpty()
    {
        return head==null;
    }
    public static void main(String s[]){
        deletenodesofDoublyLL obj=new deletenodesofDoublyLL();
        obj.add(12);
        obj.add(13);
        obj.add(14);
        obj.add(15);
        obj.print();
        System.out.println();
        obj.deleteNode(3);
        obj.print();
    }
}
