package DataStructures.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/** @author Arun Pandey (https://github.com/pandeyarun709) */
public class Merge_K_SortedLinkedlist {

  /**
   * This function merge K sorted LinkedList
   *
   * @param a array of LinkedList
   * @param N size of array
   * @return node
   */
  Node mergeKList(Node[] a, int N) {
    // Min Heap
    PriorityQueue<Node> min = new PriorityQueue<>(Comparator.comparingInt(x -> x.data));

    // adding head of all linkedList in min heap
    min.addAll(Arrays.asList(a).subList(0, N));

    // Make new head among smallest heads in K linkedList
    Node head = min.poll();
    min.add(head.next);
    Node curr = head;

    // merging LinkedList
    while (!min.isEmpty()) {

      Node temp = min.poll();
      curr.next = temp;
      curr = temp;

      // Add Node in min Heap only if temp.next is not null
      if (temp.next != null) {
        min.add(temp.next);
      }
    }

    return head;
  }

  private class Node {
    private int data;
    private Node next;

    public Node(int d) {
      this.data = d;
      next = null;
    }
  }
}
