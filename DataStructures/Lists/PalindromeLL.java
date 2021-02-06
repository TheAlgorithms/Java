/** Example 
 * Input 1 : 9 2 3 3 2 9 -1 
 *    Output 1 : true
 *    Exaplanation : If we reverse the above Linked List ,it will become "9 2 3 3 2 9"  which is same as original so it is Palindrome.
 * 
 *    Input 2 : 0 2 3 2 5 -1
 *    Output 2 : false
 *    Exaplanation : If we reverse the above Linked List ,it will become "5 2 3 2 0"  which is not same as original so it is not Palindrome.
 */



import java.util.*;

public class PalindromeLL
{
    
/**
 * This class is the nodes of the SinglyLinked List. They consist of a value and a pointer to the
 * node after them.
 */
     public static class Node<T>{
           /** The value of the node */
            int data;
             /** Point to the next node */
            Node<T> next;
             /**
             * Constructor
             *
             * @param value Value to be put in the node
             * @param next Reference to the next node
             */
            Node(int data){
                  this.data =  data;
                  next =  null;
    }
    /** Function to  take input from user */
    private static Node<Integer> takeinput(){
        Scanner s =  new  Scanner(System.in);
        int data  = s.nextInt();
        Node<Integer> head  = null,tail =null;
        while(data != -1)
        {
              Node<Integer> curr  = new Node<Integer>(data);
              if(head  == null){
                    head  = curr;
                    tail =  curr;
              }
              else{
                    tail.next  = curr;
                    tail = curr;
              }
              data = s.nextInt();
        }
        return head;
  }

  /** Function to reverse the linked list */
    private static Node<Integer> reverseI(Node<Integer> head){
        Node<Integer> curr = head;
        Node<Integer> prev = null;
        Node<Integer> fwd = null;
        while( curr != null) {
            fwd = curr.next;
             curr.next = prev;
             prev = curr;
             curr = fwd;
       }
           return prev;
   }
    
       /** Function to  check whether the Linked List is Palindrome or not */
       public static boolean isPalindrome(Node<Integer> head) {
            if (head == null || head.next == null) {
                return true;
           }
    
           Node<Integer> fast = head;
           Node<Integer> slow = head;
           while (fast.next != null && fast.next.next != null){
               fast = fast.next.next;
               slow = slow.next;
           }
            Node<Integer> sechead = slow.next;
                slow.next = null;
                sechead = reverseI(sechead);
           Node<Integer> a = sechead;	
           Node<Integer> b = head;
           while (a != null){
                if(a.data != b.data){
                   return false;
               }
            a = a.next;
            b = b.next;
       }
            return true;
       }
       
	public static void main (String[] args) throws java.lang.Exception
	{
		
	 Scanner s = new Scanner(System.in);
      Node<Integer> head = takeinput();
      boolean ans  = isPalindrome(head);
      System.out.print(ans);
	}