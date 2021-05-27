package LinkedList;

// import java.util.LinkedList;

public class insertElemtnetinasortedLinkedList {
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
    {   node toAdd=new node(data);
        if(isEmpty())
        {
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
        }

        
    }
    void print()
    {
        if(isEmpty())
        {
            System.err.println("Empty list");
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
    void insertElement(int data)
    {              
        node toAdd=new node(data);
      if(isEmpty())
        {
          head=toAdd;  
        }
       else if(head.next==null){
           if(head.data<toAdd.data)
           {
               head.next=toAdd;
           }
           else
           {
               toAdd.next=head;
               head=toAdd;
           }
       }
       else if(head.data>toAdd.data)
       {
           toAdd.next=head;
           head=toAdd;
       }

       else {
        node temp=head.next;
        node prev=head;
       int count=1;
        while(temp.data<toAdd.data&&temp.next!=null)
        {
            temp=temp.next;
            prev=prev.next;
          count++;
          if(temp.next==null)
          {
              prev=prev.next;
              break;
          }
        }
        if(count==length())
        {
            temp.next=toAdd;
            toAdd.next=null;
        }
        else
        {
            toAdd.next=prev.next;
            prev.next=toAdd;
        }
        

       }
        
    }
    int length()
    {
        node temp=head;
        int count=1;
        while(temp!=head)
        {
            temp=temp.next;
            count++;
        }
        return count;
    }
    public static void main(String s[])
    {

         insertElemtnetinasortedLinkedList obj=new insertElemtnetinasortedLinkedList();
        obj.add(12);
        obj.add(13);
        obj.add(15);
        obj.add(16);
        obj.print();
        System.out.println();
        obj.insertElement(17);
        obj.print();
       
    }
}
