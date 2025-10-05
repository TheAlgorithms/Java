import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MiddleOfLinkedListTest {

    @Test
    void testMiddleNodeOddLength() {
        MiddleOfLinkedList sol = new MiddleOfLinkedList();
        int[] values = {1, 2, 3, 4, 5}; // Odd-length list
        ListNode head = MiddleOfLinkedList.createList(values);

        ListNode middle = sol.middleNode(head);

        assertEquals(3, middle.val); // Expect middle to be 3
    }

    @Test
    void testMiddleNodeEvenLength() {
        MiddleOfLinkedList sol = new MiddleOfLinkedList();
        int[] values = {1, 2, 3, 4, 5, 6}; // Even-length list
        ListNode head = MiddleOfLinkedList.createList(values);

        ListNode middle = sol.middleNode(head);

        assertEquals(4, middle.val); // Expect middle to be 4 (second middle)
    }

    @Test
    void testMiddleNodeSingleElement() {
        MiddleOfLinkedList sol = new MiddleOfLinkedList();
        int[] values = {1};
        ListNode head = MiddleOfLinkedList.createList(values);

        ListNode middle = sol.middleNode(head);

        assertEquals(1, middle.val); // Expect middle to be 1
    }

    @Test
    void testMiddleNodeEmptyList() {
        MiddleOfLinkedList sol = new MiddleOfLinkedList();
        int[] values = {};
        ListNode head = MiddleOfLinkedList.createList(values);

        ListNode middle = sol.middleNode(head);

        assertNull(middle); // Expect null for empty list
    }
}
