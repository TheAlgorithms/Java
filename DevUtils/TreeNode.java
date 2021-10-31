package DevUtils;

public abstract class TreeNode<E> extends Node<E> {
    private Node<E> parentNode;

    public TreeNode() {
        super();
    }

    public TreeNode(E data) {
        super(data);
    }

    public TreeNode(E data, Node<E> parentNode) {
        super(data);
        this.parentNode = parentNode;
    }

    public abstract boolean isLeafNode();

    public boolean isRootNode() {
        return (parentNode == null);
    }

    public Node<E> getParent() {
        return parentNode;
    }

    public void setParent(Node<E> parentNode) {
        this.parentNode = parentNode;
    }
}
