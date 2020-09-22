package DataStructures.Lists;

public class MergeSortedSinglyLinkedList extends SinglyLinkedList {

    public static void main(String[] args) {
        SinglyLinkedList listA = new SinglyLinkedList();
        SinglyLinkedList listB = new SinglyLinkedList();

        for (int i = 2; i <= 10; i += 2) {
            listA.insert(i);
            listB.insert(i - 1);
        }
        assert listA.toString().equals("2->4->6->8->10");
        assert listB.toString().equals("1->3->5->7->9");
        assert merge(listA, listB).toString().equals("1->2->3->4->5->6->7->8->9->10");
    }

    /**
     * Merge two sorted SingleLinkedList
     *
     * @param listA the first sorted list
     * @param listB the second sored list
     * @return merged sorted list
     */
    public static SinglyLinkedList merge(SinglyLinkedList listA, SinglyLinkedList listB) {
        Node headA = listA.getHead();
        Node headB = listB.getHead();

        int size = listA.size() + listB.size();

        Node head = new Node();
        Node tail = head;
        while (headA != null && headB != null) {
            if (headA.value <= headB.value) {
                tail.next = headA;
                headA = headA.next;
            } else {
                tail.next = headB;
                headB = headB.next;
            }
            tail = tail.next;
        }
        if (headA == null) {
            tail.next = headB;
        }
        if (headB == null) {
            tail.next = headA;
        }
        return new SinglyLinkedList(head.next, size);
    }
}
