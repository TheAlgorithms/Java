package DataStructures.Lists;


public class PalindromeLinkedList extends SinglyLinkedList {

	/** isPalindrome method checks if the given LinkedList is a palindrome or not **/
    public static  boolean isPalindrome(Node head) 
    {
        if(head == null || head.next == null){
            return true;
        }
        
        Node temp = head;  // Storing the head node in a variable called temp
        
        Node mid = middleNode(temp);  //The middle of the LinkedList is stored in a variable called mid
        Node newHead = mid.next;  //newHead is the head of the second LinkedList
        mid.next = null;  //The LinkedList is being split here
        
        Node head2 = reverse(newHead);  //Reversing the second LinkedList whose head node is 'newHead'
        
        
        while(head2 != null){  //now comparing the two LinkedLists 
            if(head.value != head2.value){
                return false;
            }
                head = head.next;
                head2 = head2.next;   
        }
        return true;
    }   
    
    /** middleNode method find the middle node of the LinkedList and returns that Node **/
    public static Node middleNode(Node head){
    	Node slow = head;
    	Node fast = head;
        
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    /** reverse method reverses a LinkedList and returns the head of the reversed LinkedList **/
    public static Node reverse(Node head){
    	Node curr = head;
    	Node prev = null;
        
        while(curr != null){
        	Node nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
    
}
