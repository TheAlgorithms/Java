package Lists;

/**
 * An utility which detects a cycle in linked list using floyd algorithm
 * 
 * @author mani manasa mylavarapu
 * 
 */
class FloydCycleDetection {
	Node head;

	class Node {
		int data;
		Node next;

		Node(int d) {
			data = d;
			next = null;
		}
	}

	public void insert(int val) {
		Node node = new Node(val);
		node.next = head;
		head = node;
	}

	boolean detectLoop() {
		Node slow_p = head, fast_p = head;
		while (slow_p != null && fast_p != null && fast_p.next != null) {
			slow_p = slow_p.next;
			fast_p = fast_p.next.next;
			if (slow_p == fast_p) {
				return true;
			}
		}
		return false;
	}

	public static void main(String args[]) {
		FloydCycleDetection llist = new FloydCycleDetection();

		llist.insert(2);
		llist.insert(4);
		llist.insert(5);
		llist.insert(10);

		/* Create loop for testing */
		llist.head.next.next.next.next = llist.head;

		if (llist.detectLoop()) {
			System.out.println("found loop");
		} else {
			System.out.println("No loops detected");
		}
	}
}