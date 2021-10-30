package DevUtils;

public class NestedNode<E> extends Node<E> {
    private Node<E> nestedNode;

    public NestedNode() {}

    public NestedNode(E data) {
        super(data);
    }

    public NestedNode(E data, Node<E> nestedNode) {
        super(data);
        this.nestedNode = nestedNode;
    }

    public Node<E> getNestedNode() {
        return nestedNode;
    }

    public void setNestedNode(Node<E> nestedNode) {
        this.nestedNode = nestedNode;
    }
}
