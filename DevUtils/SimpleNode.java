package DevUtils;

public class SimpleNode<E> extends Node<E> {
    private Node<E> nextNode;

    public SimpleNode() {
        super();
    }

    public SimpleNode(E data) {
        super(data);
    }

    public SimpleNode(E data, Node<E> nextNode) {
        super(data);
        this.nextNode = nextNode;
    }

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
