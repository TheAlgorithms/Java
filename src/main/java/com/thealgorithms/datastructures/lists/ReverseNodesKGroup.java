package com.thealgorithms.datastructures.lists;

/**
 * Reverse Nodes in k-Group of LinkedList
 * 
 * Given the head of a linked list, you need to reverse the nodes of the list k at a time and return the modified list.
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k, then the left-out nodes at the end should remain as they are.
 * You are allowed to change only the nodes themselves, not their values.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * 
 * Example 2:
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *
 * Constraints:
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 * 
 * Additional Information
 * you can solve the problem without using extra memory (i.e., without creating a new list) while still reversing the nodes in groups of k. Solving it in O(1) extra memory space typically requires manipulating the pointers within the existing linked list.
 * 
 * Author: Frank Garcia (https://github.com/frank-xyz)
 */

public class ReverseNodesKGroup {
  public static class ListNode {
    int val;
    ListNode next;
    
    ListNode(int x) {
        val = x;
    }
  }

  public static void main(String[] args) throws Exception {
    ListNode node = new ListNode(1);
    node.next = new ListNode(2);
    node.next.next = new ListNode(3);
    node.next.next.next = new ListNode(4);
    node.next.next.next.next = new ListNode(5);
    
    // input: head = [1,2,3,4,5], k = 2
    ListNode result = new ReverseNodesKGroup().reverseKGroup(node, 2);
    while (result != null) {
      System.out.println(result.val + " ");
      result = result.next;
    }
    
    // input: head = [1,2,3,4,5], k = 3
    ListNode result2 = new ReverseNodesKGroup().reverseKGroup(node, 3);
    while (result2 != null) {
      System.out.println(result2.val + " ");
      result2 = result2.next;
    }
  }

  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || head.next == null) return head; // base case: list is empty or list with only one node
    
    ListNode dummy = new ListNode(0); // create a dummy node
    dummy.next = head; // connect the dummy node to the head of the linked list
    
    ListNode pointer = dummy; // pointer to reference the start of each k-group of nodes to reverse
    while (pointer != null) { // loop while the node referenced by pointer is not null
        ListNode temp = pointer; // temp pointer to reference the current node
        for (int i = 0; i < k && temp != null; i++) { // iterate through the linked list k times 
            temp = temp.next; // move temp pointer forward in the linked list
        }
        if (temp == null) break; // if the node referenced by temp is null, break (i.e. not enough nodes for a full k-group)
        
        // *** reverse the next k nodes *** (modified linked list reversal algorithm)
        ListNode prev = null; // pointer to reference the previous node
        ListNode current = pointer.next; // pointer to reference the current node (initially set to head of the current k-group)
        for (int i = 0; i < k; i++) { // loop to iterate through the linked list k times
            ListNode next = current.next; // pointer to reference the next node
            current.next = prev; // reverse the current node by pointing it to the previous node
            prev = current; // update prev to the current node
            current = next; // update current to the next node to reverse 
        }
        ListNode tail = pointer.next; // pointer to the reference the end of the current k-group
        tail.next = current; // connect the end of the reversed k-group to the next node
        pointer.next = prev; // update the pointer to point to the new head of the reversed k-group
        pointer = tail; // move the pointer to the end of the current k-group for the next iteration
    }
    
    return dummy.next; // return the head node of the modified linked list
  }
  // time: O(n), where n is the number of nodes in the linked list
  // space: O(1), constant space since no additional data structures were used
}