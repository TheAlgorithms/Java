class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class SortSinglyLinkedList {

    //Driver function
    public static void main(String[] args) {
        ListNode dummy = new ListNode();
        ListNode temp = dummy;
        for (int i = 10; i > 0; i--) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        temp = dummy.next;
        print(temp);
        temp = sortList(temp);
        print(temp);
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode middle = middle(head);
        ListNode next = middle.next;
        middle.next = null;
        return mergeTwoLists(sortList(head), sortList(next));
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode temp, head;
        if (l1.val <= l2.val) {
            temp = l1;
            l1 = l1.next;
        } else {
            temp = l2;
            l2 = l2.next;
        }

        head = temp;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = l1;
                temp = temp.next;
                l1 = l1.next;
            } else {
                temp.next = l2;
                temp = temp.next;
                l2 = l2.next;
            }
        }

        if (l1 != null) {
            temp.next = l1;
        } else {
            temp.next = l2;
        }
        return head;
    }

    private static ListNode middle(ListNode head) {
        ListNode prev = null;
        ListNode temp = head;
        ListNode slow = head;

        while (temp != null && temp.next != null) {
            prev = slow;
            slow = slow.next;
            temp = temp.next.next;
        }
        return prev;
    }

    private static void print(ListNode head) {
        ListNode temp = head;
        while (temp.next != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println(temp.val);
    }

}