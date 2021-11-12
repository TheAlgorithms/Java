package com.thealgorithms.devutils.nodes;

/**
 * Simple Node implementation that holds a reference to the next Node.
 *
 * @param <E> The type of the data held in the Node.
 *
 * @author <a href="https://github.com/aitorfi">aitorfi</a>
 */
public class SimpleNode<E> extends Node<E> {

    /**
     * Reference to the next Node.
     */
    private SimpleNode<E> nextNode;

    /**
     * Empty contructor.
     */
    public SimpleNode() {
        super();
    }

    /**
     * Initializes the Nodes' data.
     *
     * @param data Value to which data will be initialized.
     * @see Node#Node(Object)
     */
    public SimpleNode(E data) {
        super(data);
    }

    /**
     * Initializes the Nodes' data and next node reference.
     *
     * @param data Value to which data will be initialized.
     * @param nextNode Value to which the next node reference will be set.
     */
    public SimpleNode(E data, SimpleNode<E> nextNode) {
        super(data);
        this.nextNode = nextNode;
    }

    /**
     * @return True if there is a next node, otherwise false.
     */
    public boolean hasNext() {
        return (nextNode != null);
    }

    public SimpleNode<E> getNextNode() {
        return nextNode;
    }

    public void setNextNode(SimpleNode<E> nextNode) {
        this.nextNode = nextNode;
    }
}
