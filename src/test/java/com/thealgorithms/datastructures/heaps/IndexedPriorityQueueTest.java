package com.thealgorithms.datastructures.heaps;

import java.util.Comparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link IndexedPriorityQueue}.
 *
 * Notes:
 * - We mainly use a Node class with a mutable "prio" field to test changeKey/decreaseKey/increaseKey.
 * - The queue is a min-heap, so smaller "prio" means higher priority.
 * - By default the implementation uses IdentityHashMap so duplicate-equals objects are allowed.
 */
public class IndexedPriorityQueueTest {

    // ------------------------
    // Helpers
    // ------------------------

    /** Simple payload with mutable priority. */
    static class Node {
        final String id;
        int prio; // lower is better (min-heap)

        Node(String id, int prio) {
            this.id = id;
            this.prio = prio;
        }

        @Override
        public String toString() {
            return id + "(" + prio + ")";
        }
    }

    /** Same as Node but overrides equals/hashCode to simulate "duplicate-equals" scenario. */
    static class NodeWithEquals {
        final String id;
        int prio;

        NodeWithEquals(String id, int prio) {
            this.id = id;
            this.prio = prio;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof NodeWithEquals)) {
                return false;
            }
            NodeWithEquals other = (NodeWithEquals) o;
            // Intentionally naive equality: equal if priority is equal
            return this.prio == other.prio;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(prio);
        }

        @Override
        public String toString() {
            return id + "(" + prio + ")";
        }
    }

    private static IndexedPriorityQueue<Node> newNodePQ() {
        return new IndexedPriorityQueue<>(Comparator.comparingInt(n -> n.prio));
    }

    // ------------------------
    // Basic operations
    // ------------------------

    @Test
    void testOfferPollWithIntegersComparableMode() {
        // cmp == null -> elements must be Comparable
        IndexedPriorityQueue<Integer> pq = new IndexedPriorityQueue<>();
        Assertions.assertTrue(pq.isEmpty());

        pq.offer(5);
        pq.offer(1);
        pq.offer(3);

        Assertions.assertEquals(3, pq.size());
        Assertions.assertEquals(1, pq.peek());
        Assertions.assertEquals(1, pq.poll());
        Assertions.assertEquals(3, pq.poll());
        Assertions.assertEquals(5, pq.poll());
        Assertions.assertNull(pq.poll()); // empty -> null
        Assertions.assertTrue(pq.isEmpty());
    }

    @Test
    void testPeekAndIsEmpty() {
        IndexedPriorityQueue<Node> pq = newNodePQ();
        Assertions.assertTrue(pq.isEmpty());
        Assertions.assertNull(pq.peek());

        pq.offer(new Node("A", 10));
        pq.offer(new Node("B", 5));
        pq.offer(new Node("C", 7));

        Assertions.assertFalse(pq.isEmpty());
        Assertions.assertEquals("B(5)", pq.peek().toString());
    }

    @Test
    void testRemoveSpecificElement() {
        IndexedPriorityQueue<Node> pq = newNodePQ();
        Node a = new Node("A", 10);
        Node b = new Node("B", 5);
        Node c = new Node("C", 7);

        pq.offer(a);
        pq.offer(b);
        pq.offer(c);

        // remove by reference (O(log n))
        Assertions.assertTrue(pq.remove(b));
        Assertions.assertEquals(2, pq.size());
        // now min should be C(7)
        Assertions.assertEquals("C(7)", pq.peek().toString());
        // removing an element not present -> false
        Assertions.assertFalse(pq.remove(b));
    }

    @Test
    void testContainsAndClear() {
        IndexedPriorityQueue<Node> pq = newNodePQ();
        Node a = new Node("A", 2);
        Node b = new Node("B", 3);

        pq.offer(a);
        pq.offer(b);

        Assertions.assertTrue(pq.contains(a));
        Assertions.assertTrue(pq.contains(b));

        pq.clear();
        Assertions.assertTrue(pq.isEmpty());
        Assertions.assertFalse(pq.contains(a));
        Assertions.assertNull(pq.peek());
    }

    // ------------------------
    // Key updates
    // ------------------------

    @Test
    void testDecreaseKeyMovesUp() {
        IndexedPriorityQueue<Node> pq = newNodePQ();
        Node a = new Node("A", 10);
        Node b = new Node("B", 5);
        Node c = new Node("C", 7);

        pq.offer(a);
        pq.offer(b);
        pq.offer(c);

        // current min is B(5)
        Assertions.assertEquals("B(5)", pq.peek().toString());

        // Make A more important: 10 -> 1 (smaller is better)
        pq.decreaseKey(a, n -> n.prio = 1);

        // Now A should be at the top
        Assertions.assertEquals("A(1)", pq.peek().toString());
    }

    @Test
    void testIncreaseKeyMovesDown() {
        IndexedPriorityQueue<Node> pq = newNodePQ();
        Node a = new Node("A", 1);
        Node b = new Node("B", 2);
        Node c = new Node("C", 3);

        pq.offer(a);
        pq.offer(b);
        pq.offer(c);

        // min is A(1)
        Assertions.assertEquals("A(1)", pq.peek().toString());

        // Make A worse: 1 -> 100
        pq.increaseKey(a, n -> n.prio = 100);

        // Now min should be B(2)
        Assertions.assertEquals("B(2)", pq.peek().toString());
    }

    @Test
    void testChangeKeyChoosesDirectionAutomatically() {
        IndexedPriorityQueue<Node> pq = newNodePQ();
        Node a = new Node("A", 10);
        Node b = new Node("B", 20);
        Node c = new Node("C", 30);

        pq.offer(a);
        pq.offer(b);
        pq.offer(c);

        // Decrease B to 0 -> should move up
        pq.changeKey(b, n -> n.prio = 0);
        Assertions.assertEquals("B(0)", pq.peek().toString());

        // Increase B to 100 -> should move down
        pq.changeKey(b, n -> n.prio = 100);
        Assertions.assertEquals("A(10)", pq.peek().toString());
    }

    @Test
    void testDirectMutationWithoutChangeKeyDoesNotReheapByDesign() {
        // Demonstrates the contract: do NOT mutate comparator fields directly.
        IndexedPriorityQueue<Node> pq = newNodePQ();
        Node a = new Node("A", 5);
        Node b = new Node("B", 10);

        pq.offer(a);
        pq.offer(b);

        // Illegally mutate priority directly
        a.prio = 100; // worse than b now, but heap wasn't notified

        // The heap structure is unchanged; peek still returns A(100) (was A(5) before)
        // This test documents the behavior/contract rather than relying on it.
        Assertions.assertEquals("A(100)", pq.peek().toString());

        // Now fix properly via changeKey (no change in value, but triggers reheap)
        pq.changeKey(a, n -> n.prio = n.prio);
        Assertions.assertEquals("B(10)", pq.peek().toString());
    }

    // ------------------------
    // Identity semantics & duplicates
    // ------------------------

    @Test
    void testDuplicateEqualsElementsAreSupportedIdentityMap() {
        IndexedPriorityQueue<NodeWithEquals> pq = new IndexedPriorityQueue<>(Comparator.comparingInt(n -> n.prio));

        NodeWithEquals x1 = new NodeWithEquals("X1", 7);
        NodeWithEquals x2 = new NodeWithEquals("X2", 7); // equals to X1 by prio, but different instance

        // With IdentityHashMap internally, both can coexist
        pq.offer(x1);
        pq.offer(x2);

        Assertions.assertEquals(2, pq.size());
        // Poll twice; both 7s should be returned (order between x1/x2 is unspecified)
        Assertions.assertEquals(7, pq.poll().prio);
        Assertions.assertEquals(7, pq.poll().prio);
        Assertions.assertTrue(pq.isEmpty());
    }

    // ------------------------
    // Capacity growth
    // ------------------------

    @Test
    void testGrowByManyInserts() {
        IndexedPriorityQueue<Integer> pq = new IndexedPriorityQueue<>();
        int n = 100; // beyond default capacity (11)

        for (int i = n; i >= 1; i--) {
            pq.offer(i);
        }

        Assertions.assertEquals(n, pq.size());
        // Ensure min-to-max order when polling
        for (int expected = 1; expected <= n; expected++) {
            Integer v = pq.poll();
            Assertions.assertEquals(expected, v);
        }
        Assertions.assertTrue(pq.isEmpty());
        Assertions.assertNull(pq.poll());
    }

    // ------------------------
    // remove/contains edge cases
    // ------------------------

    @Test
    void testRemoveHeadAndMiddleAndTail() {
        IndexedPriorityQueue<Node> pq = newNodePQ();
        Node a = new Node("A", 1);
        Node b = new Node("B", 2);
        Node c = new Node("C", 3);
        Node d = new Node("D", 4);

        pq.offer(a);
        pq.offer(b);
        pq.offer(c);
        pq.offer(d);

        // remove head
        Assertions.assertTrue(pq.remove(a));
        Assertions.assertFalse(pq.contains(a));
        Assertions.assertEquals("B(2)", pq.peek().toString());

        // remove middle
        Assertions.assertTrue(pq.remove(c));
        Assertions.assertFalse(pq.contains(c));
        Assertions.assertEquals("B(2)", pq.peek().toString());

        // remove tail (last)
        Assertions.assertTrue(pq.remove(d));
        Assertions.assertFalse(pq.contains(d));
        Assertions.assertEquals("B(2)", pq.peek().toString());

        // remove last remaining
        Assertions.assertTrue(pq.remove(b));
        Assertions.assertTrue(pq.isEmpty());
        Assertions.assertNull(pq.peek());
    }

    // ------------------------
    // Error / edge cases for coverage
    // ------------------------

    @Test
    void testInvalidInitialCapacityThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new IndexedPriorityQueue<Integer>(0, Comparator.naturalOrder()));
    }

    @Test
    void testChangeKeyOnMissingElementThrows() {
        IndexedPriorityQueue<Node> pq = newNodePQ();
        Node a = new Node("A", 10);

        Assertions.assertThrows(IllegalArgumentException.class, () -> pq.changeKey(a, n -> n.prio = 5));
    }

    @Test
    void testDecreaseKeyOnMissingElementThrows() {
        IndexedPriorityQueue<Node> pq = newNodePQ();
        Node a = new Node("A", 10);

        Assertions.assertThrows(IllegalArgumentException.class, () -> pq.decreaseKey(a, n -> n.prio = 5));
    }

    @Test
    void testIncreaseKeyOnMissingElementThrows() {
        IndexedPriorityQueue<Node> pq = newNodePQ();
        Node a = new Node("A", 10);

        Assertions.assertThrows(IllegalArgumentException.class, () -> pq.increaseKey(a, n -> n.prio = 15));
    }
}
