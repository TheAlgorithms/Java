package DevUtils.Nodes;

/**
 * Simple Node implementation that holds
 * a reference to the next Node.
 * 
 * @param <E> The type of the data held in the Node.
 * 
 * @author aitorfi (https://github.com/aitorfi)
 */
public class SimpleNode<E> extends Node<E> {
    /** Reference to the next Node. */
    private Node<E> nextNode;

    /** Empty contructor. */
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
    public SimpleNode(E data, Node<E> nextNode) {
        super(data);
        this.nextNode = nextNode;
    }

    /**
     * @return True if there is a next node, otherwise false.
     */
    public boolean hasNext() {
        return (nextNode != null);
    }

    public Node<E> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<E> nextNode) {
        this.nextNode = nextNode;
    }
}
