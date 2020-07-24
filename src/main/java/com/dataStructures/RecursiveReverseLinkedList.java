package com.dataStructures;

public class RecursiveReverseLinkedList<T> {

	private Node<T> head;
	private Node<T> previous;
	private Node<T> nextTemp;

	/**
	 * Method that recursively reverse the list
	 *
	 * @param node nodes that is to be reversed
	 * @return nodes that is in reversed order
	 */
	Node<T> reverseList(Node<T> node) {
		Node<T> current = node;

		if (current == null)
			return previous;

		nextTemp = current.getNext();
		current.setNext(previous);
		previous = current;
		current = nextTemp;

		return reverseList(current);
	}

	/**
	 * Method to add a node to the end of the list
	 *
	 * @param value a generic value of a node
	 *
	 */
	void add(T value) {
		Node<T> newNode = new Node<>(value);
		if (head == null) {
			head = newNode;
		} else {
			Node<T> last = head;
			while (last.getNext() != null) {
				last = last.getNext();
			}
			last.setNext(newNode);
		}
	}

	/**
	 * Method that returns the value of the nodes to a string
	 *
	 * @param node
	 * @return value of nodes in a string
	 */
	public String toString(Node<T> node) {
		StringBuilder sb = new StringBuilder();
		Node<T> current = node;

		while (current != null) {
			sb.append(current.getValue());
			current = current.getNext();
		}

		return sb.toString();
	}

	public Node<T> getHead() {
		return head;
	}

}

/**
 * A node in a LinkedList
 */
class Node<T> {
	private Node<T> next;
	private T value;

	/**
	 * Initialize a generic type value in the constructor
	 *
	 * @param value A generic type value in the node
	 *
	 */
	Node(T value) {
		this.value = value;
	}

	/**
	 *
	 * @return the value of the current node
	 */
	public T getValue() {
		return value;
	}

	/**
	 *
	 * @return the next node
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 *
	 * @param next set a next node to the current pointed node
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}
}