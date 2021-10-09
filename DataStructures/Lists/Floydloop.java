/* THIS CODE IS USED TO DETECT CYCLE OR LOOPS IN lINKED LIST USING FLOYD'S ALGORITHM 
for  better understanding the algorithm go through https://en.wikipedia.org/wiki/Cycle_detection*/

 import java.util.*;
 class Floydloop{
  static ListNode head;
 class ListNode{     //The class ised to contruct the Linked list
     int val;
     ListNode next;
     ListNode (int x)
     {
         int val=x;
         next=null;
     }
 }
 public void add(int new_data) /// adds new node at the front of the linkedlist 
    {
        ListNode new_node = new ListNode(new_data);
        new_node.next = head;
        head = new_node;
    }

     public boolean Cycle(ListNode head)
     {
         ListNode slow= head; //Declaring 2 pointers slow and fast 
         ListNode fast= head;
         while (slow!=null && fast!= null && fast.next!=null)
         {
             slow=slow.next;// this poiter runs slow in one steps 
             fast=fast.next.next;// this pointer runs faster with two steps
             if(slow==fast)//if value of both are same then there is a loop
             return true;
         }
         return false;

     }

     public static void main(String args[])
     {
         Floydloop list1 = new Floydloop();
         list1.add(1);
         list1.add(2);
         list1.add(3);
         list1.add(5);
         list1.head.next.next.next.next = list1.head;// Just to create a loop
         list.Cycle(); 
        }

 }
