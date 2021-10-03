public class RemoveDuplicateNodes {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        // sentinel
        ListNode sentinel = new ListNode(0, head);

        // predecessor = the last node 
        // before the sublist of duplicates
        ListNode pred = sentinel;

        while (head != null) {
            // if it's a beginning of duplicates sublist 
            // skip all duplicates
            if (head.next != null && head.val == head.next.val) {
                // move till the end of duplicates sublist
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // skip all duplicates
                pred.next = head.next;
                // otherwise, move predecessor
            } else {
                pred = pred.next;
            }

            // move forward
            head = head.next;
        }
        return sentinel.next;
    }

    public void print(ListNode head) {
        ListNode temp = head;
        while (temp != null && temp.next != null) {
            System.out.print(temp.val + "->");
            temp = temp.next;
        }
        if (temp != null) {
            System.out.print(temp.val);
        }
    }

    public static void main(String arg[]) {
        RemoveDuplicateNodes instance = new RemoveDuplicateNodes();
        ListNode head = new ListNode(0, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4)))));
        head = instance.deleteDuplicates(head);
        instance.print(head);
    }
}