package LinkedList;

public class deleteHeadofCircularLinkedList {
    class node{
        int data;
        node next;
        node(int data)
        {
            this.data=data;

        }
    }
    node head;
    void add(int data)
    {   node toAdd=new node(data);
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
    void print()
    {   if(isEmpty())
        {
            System.err.println("Empty List");
        }
        else
        {  
            node temp=head.next;
            System.out.print(head.data+" ");
        while(temp!=head)
        {
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
        
        }
       
    }
    //Naive approach
    void deleteHead()
    {
         if(isEmpty())
         {
             System.err.println("Empty list");
         }
         else
         {
             node temp=head;
             while(temp.next!=head)
             {
                 temp=temp.next;
             }
             temp.next=head.next;
             head=head.next;
         }
    }
    void deleteNode(int pos)
    {
            if(pos==1)
            {
                deletehead();
            }
            else if(pos==length())
            {
                node temp=head;
                while(temp.next.next!=head)
                {
                    temp=temp.next;
                }
                temp.next=head;
            }
            else 
            {
                int count=1;
                node temp=head;
                while(count!=pos-1)
                {
                    temp=temp.next;
                    count++;
                }
                temp.next=temp.next.next;
            }
    }
    boolean isEmpty()
    {
        return head==null;
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
    // better approach
    void deletehead()
    {
        if(isEmpty())
        {
            return;
        }
        else
        {
            int t=head.data;
            head.data=head.next.data;
            head.next.data=t;
            head.next=head.next.next;
           
        
        }
    }
    public static void main(String s[])
    {
        deleteHeadofCircularLinkedList obj=new deleteHeadofCircularLinkedList();
        obj.add(12);
        obj.add(13);
        obj.add(14);
        obj.add(15);
        obj.print();
        System.out.println();
        obj.deleteNode(4);
        obj.print();
    }
}
