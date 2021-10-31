package DevUtils.Nodes;

public class SimpleTreeNode<E> extends TreeNode<E> {
    private Node<E> leftNode;
    private Node<E> rightNode;

    public SimpleTreeNode() {
        super();
    }

    public SimpleTreeNode(E data) {
        super(data);
    }

    public SimpleTreeNode(E data, Node<E> parentNode) {
        super(data, parentNode);
    }

    public SimpleTreeNode(E data, Node<E> parentNode, Node<E> leftNode, Node<E> rightNode) {
        super(data, parentNode);
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public boolean isLeafNode() {
        return (leftNode == null && rightNode == null);
    }

    public Node<E> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node<E> leftNode) {
        this.leftNode = leftNode;
    }

    public Node<E> getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node<E> rightNode) {
        this.rightNode = rightNode;
    }
}
