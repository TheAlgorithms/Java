//Find the loop in Linked list

/*
Input Format:
First line contains number of Test case i.e. T.
Each test case contains 3 lines. First line contains the total number of nodes
Second line contains the elements, and the third line contains two space-separated integers denoting the index (head node has index 1) of loop start and end node.
*/


import java.util.Scanner;
// Other imports go here
// Do NOT change the class name
class Node
{
  int data;
  Node next;
  Node(int d)
  {
    data=d;
  }
}

class Result {
    static int loopInList(Node head) {
      
      Node temp1=head;
      Node temp2 = head;
      int count = 0;
      while(temp1!=null && temp2!=null && temp2.next!=null)
      {
        temp1 = temp1.next;
        temp2 = temp2.next.next;
        if(temp1==temp2)
        {
          Node temp = temp1;
          count=1;
      while(temp.next!=temp1)
      {
        count++;
        temp=temp.next;
      }
          return count;
        }
        
        
      }
      return count;
  
    }
  }


class Main
{
  static Node insertEnd(Node head, int data)
  {
    Node newLink = new Node(data);
    Node last = head;
    newLink.next = null;   // link new node to NULL as it is last node
    if (head == null)  // if list is empty add in beginning.
    {
      head = newLink;
      return head;
    }
    while (last.next != null)  // Find the last node
      last = last.next;
    last.next = newLink;  // Add the node after the last node of list
    return head;
  }

  static void forwardPrint(Node head)
  {
    Node current = head; // start at beginning of list
    while(current != null) // until end of list,
    {
      System.out.print(current.data + " "); // print data
      current = current.next; // move to next link
    }
  }
  public static void main(String[] args)
  {
    int t,n,m;
    Scanner s = new Scanner(System.in);
    t=Integer.parseInt(s.nextLine().trim());
    while(t>0)
    {
      Node head = null;
      Node t1, t2;
      int c, k, ans;
      n = s.nextInt();
      while(n>0)
      {
        m = s.nextInt();
        head = insertEnd(head, m);
        n--;
      }
      k = s.nextInt();
      c = s.nextInt();
      if(c>0 && k>0)
      {
        k--;c--;
        t1=head;
        t2=head;
        while(k>0){
          t1 = t1.next; 
          k--;
        }
        while(c>0){
          t2 = t2.next;
          c--;
        }
        t1.next = t2;
      }
      ans = Result.loopInList(head);
      System.out.println(ans);
      t--;
    }
    s.close();
  }
}