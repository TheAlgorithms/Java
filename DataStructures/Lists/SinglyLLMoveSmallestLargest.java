//Move the Smallest and largest to head and tail of Singly Linked List

import java.util.*;

class linkedList
{
    Node head;
    static class Node
    {
        int data;
        Node next;
        Node(int d)
        {
            data = d;
            next=null;
        }
    }

    static linkedList insertBeg(linkedList list, int val)
    {
        Node temp = new Node(val);
        temp.next = list.head;
        list.head = temp;
        return list;
    }

    static linkedList insertEnd(linkedList list, int val)
    {
        Node temp = new Node(val);
        if(list.head == null)
        {
            list.head= temp;
        }
        else
        {
            Node temp1 = list.head;
            while(temp1.next!=null)
            {
                temp1 = temp1.next;
            }
            temp1.next = temp;
        }
        return list;
        
    }

    static void display(Node temp)
    {
        while(temp!=null)
        {
            System.out.print(temp.data + " ");
            temp=temp.next;
        }
        System.out.println();
    }
    
    
    static Node shiftSmallLarge(Node org)
    {
        if(org==null || org.next==null)
        {}
        else
        {
        Node temp = org;
        Node s=org;
        Node lg =org;
        while(temp!=null)
        {
            s=s.data<temp.data?s:temp;
            lg=lg.data>temp.data?lg:temp;
            temp=temp.next;
        }
      temp = org;
          if(temp!=s)
          {
      while(temp.next!=s)
      {
        temp=temp.next;
       }
       temp.next=s.next;
        s.next = org;
        org = s;
          }
      
      temp = org;
      while(temp.next!=lg)
      {
        temp=temp.next;
       }
      temp.next = lg.next;
      lg.next = null;
        temp = org;
      while(temp.next != null)
      {
        temp=temp.next;
      }
      temp.next=lg;
    }
  return org;
    }
    public static void main(String args[])
    {
        linkedList l = new linkedList();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter total numbers to enter: ");
        int n = sc.nextInt();
        System.out.println("Select the following:\n1. Insert at beginning. \n2. Insert at end.");
        int cs = sc.nextInt();
        switch(cs)
        {
            case 1:
            {
                for(int i=0;i<n;i++)
                {
                    int val = sc.nextInt();
                    l = insertBeg(l,val);
                }
                break;
            }
            case 2:
            {
                for(int i=0;i<n;i++)
                {
                    int val = sc.nextInt();
                    l = insertEnd(l,val);
                }
                break;
            }
            default:
                System.out.println("Enter valid number");
                break;
        }
        
        display(l.head);

        Node r = shiftSmallLarge(l.head);

        display(r);

        sc.close();
        
    }
}