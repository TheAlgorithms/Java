//Palindrome Linked List

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = reverse(slow);
        fast = head;
        
        while(slow !=null){
            if(slow.val != fast.val){
                return false;
            }
            slow = slow.next;
            fast = fast.next;
            
        }
        return true;
    }
    
    public ListNode reverse(ListNode head){
        ListNode prev = null;
        while(head !=null){
            //null  3->2->1
            ListNode next = head.next;
            head.next = prev;
            // null<-3 2->1
            prev = head;
            head = next;
            
        }
        return prev;
    }
}
