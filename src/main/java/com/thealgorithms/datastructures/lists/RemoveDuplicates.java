class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}

public class RemoveDuplicates {
    public ListNode removeDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode current = head;
        
        while (current != null) {
            // Check for duplicates
            ListNode runner = current;
            while (runner.next != null) {
                if (runner.next.val == current.val) {
                    runner.next = runner.next.next; // Skip the duplicate node
                } else {
                    runner = runner.next;
                }
            }
            
            current = current.next;
        }
        
        return head;
    }
    
    // Helper method to print the linked list
    private void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        RemoveDuplicates solution = new RemoveDuplicates();
        
        // Create a sample linked list: 1 -> 2 -> 2 -> 3 -> 4 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);
        
        System.out.println("Original Linked List:");
        solution.printLinkedList(head);
        
        ListNode result = solution.removeDuplicates(head);
        
        System.out.println("Linked List after Removing Duplicates:");
        solution.printLinkedList(result);
    }
}
