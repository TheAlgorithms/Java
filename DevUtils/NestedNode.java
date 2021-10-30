package DevUtils;

public class NestedNode<E> extends Node<E> {
    private NestedNode<Node<E>> nestedNode;

    public NestedNode() {}

    public NestedNode(E data) {
        super(data);
    }

    public NestedNode(E data, NestedNode<Node<E>> nestedNode) {
        super(data);
        this.nestedNode = nestedNode;
    }

    public NestedNode<Node<E>> getnestedNode() {
        return nestedNode;
    }

    public void setNestedNode(NestedNode<Node<E>> nestedNode) {
        this.nestedNode = nestedNode;
    }
}
