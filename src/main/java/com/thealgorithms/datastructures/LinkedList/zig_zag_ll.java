import java.util.*;
public class LinkedList
{  
      static class Node{
          char item;
          Node next;
          Node(char item){
              this.item=item;
              next=null;
          }
      }
     static Node head1;
     static Node tail1;
     static Node head2;
     static Node tail2;
     
    //   static void join_ll(Node left,Node right){
          
    //       if(left==null  || right==null){
    //           if(left==null){
    //               temp.next=right;
    //               return;
    //           }
    //           else{
    //               temp.next=left;
    //               return;
    //           }
    //       }
    //       temp.next=left;
    //       temp=temp.next;
    //       temp.next=right;
    //       temp=temp.next;
    //       join_ll(left.next,right.next);
    //   }
    static Node join_ll(Node left,Node right){
          Node dummy=new Node('z');
      Node temp=dummy;
        
        while(left!=null && right!=null){
            temp.next=left;
            left=left.next;
            temp=temp.next;
            temp.next=right;
            right=right.next;
             temp=temp.next;
        }
        while(left!=null){
            temp.next=left;
            left=left.next;
            temp=temp.next;
        }
        while(right!=null){
            temp.next=right;
            right=right.next;
             temp=temp.next;
        }
        
        return dummy.next;
         
    }
      static void show(Node head_new){
          Node curr=head_new;
          while(curr!=null){
              System.out.print(curr.item+"->");
              curr=curr.next;
          }
          System.out.println();
      }
      static void addLast_left(char item){
          Node newNode=new Node(item);
          if(head1==null){
              head1=tail1=newNode;
              return;
          }
          tail1.next=newNode;
          tail1=newNode;
      }
    static void addLast_right(char item){
          Node newNode=new Node(item);
          if(head2==null){
              head2=tail2=newNode;
              return;
          }
          tail2.next=newNode;
          tail2=newNode;
      }
	public static void main(String[] args) {
// 	  a->b->c->d;
// 	  1->2->3;
// 	  a->1->b->2->c-3->d;
      LinkedList left=new LinkedList();
      left.addLast_left('a');
      left.addLast_left('b');
      left.addLast_left('c');
      left.addLast_left('d');
      left.addLast_left('e');
      left.addLast_left('f');
      
     LinkedList right=new LinkedList();
     right.addLast_right('1');
     right.addLast_right('2');
     right.addLast_right('3');
    // if(right==null  && right==null){
    //     System.out.println("Botha re null");
    // }
    // 
    // print_ll(dummy.next);
    // show(head2);
    // show(head1);
    Node new_pointer=join_ll(head1,head2);
    show(new_pointer);
    
}
}
