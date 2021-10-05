import java.util.*;
class Node {
    int data;
    Node next;
    Node(int d) {
        data = d;
        next = null;
    }
} 

class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt();
            
            int a = sc.nextInt();
            Node head = new Node(a);
            Node tail = head;
            
            for (int i=0; i<n-1; i++)
            {
                a = sc.nextInt();
                tail.next = new Node(a);
                tail = tail.next;
            }
            
            int k = sc.nextInt();
            
            Solution ob = new Solution();
            head = ob.rotate(head,k);
            printList(head);
        }
    }
    
    public static void printList(Node n) {
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
        System.out.println();
    }
}

class Solution{
    //Function to rotate a linked list.
    public Node rotate(Node head, int k) {
        
        int i =0;
        Node temp = head;
        Node first = null;
        Node last = null;
        while(i<=k && null!=temp)
        {
          first = last;
          last = temp;
          temp = temp.next;
          i++;
        }
        
        if(i==k)
        {
           return head; 
        }
        
        first.next = null;
        temp = last;
        while(null!=temp)
        {
           if(null==temp.next)
           {
               temp.next = head;
               break;
           }
           
           temp = temp.next;
        }
        
        return last;
        
    }
}
