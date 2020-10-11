
import java.util.*; 
  
public class LinkedList { 
    Node head;
    public static void main(String args[]) 
    { 
       LinkedList list=new LinkedList();
       Scanner sc=new Scanner(System.in);
      
       System.out.println("How many nodes you want to insert?");
       int n=sc.nextInt();
       for(int i=1;i<=n;i++)
       {
           System.out.print("Enter value of node");
           int value=sc.nextInt();
           list.push(value);
       }
      
       boolean condition = isPalindrome(list.head); 
        System.out.println("isPalindrome :" + condition); 
    } 
    public void push(int new_data){
        Node new_node=new Node(new_data);
        new_node.next=head;
        head=new_node;
    }
    static boolean isPalindrome(Node head) 
    { 
  
        Node slow = head; 
        boolean ispalin = true; 
        Stack<Integer> stack = new Stack<Integer>(); 
  
        while (slow != null) { 
            stack.push(slow.data); 
            slow = slow.next; 
        } 
  
        while (head != null) { 
  
            int i = stack.pop(); 
            if (head.data == i) { 
                ispalin = true; 
            } 
            else { 
                ispalin = false; 
                break; 
            } 
            head = head.next; 
        } 
        return ispalin; 
    } 
} 
  
class Node { 
    int data; 
    Node next; 
    Node(int d) 
    { 
        next = null; 
        data = d; 
    } 
} 