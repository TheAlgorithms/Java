package com.thealgorithms.devutils.nodes;

/**
 * Simple TreeNode extension that holds references to two child Nodes (left and
 * right).
 *
 * @param <E> The type of the data held in the Node.
 *
 * @author <a href="https://github.com/aitorfi">aitorfi</a>
 */
public class SimpleTreeNode<E> extends TreeNode<E> {

    /**
     * Refrence to the child Node on the left.
     */
    private SimpleTreeNode<E> leftNode;
    /**
     * Refrence to the child Node on the right.
     */
    private SimpleTreeNode<E> rightNode;

    /**
     * Empty contructor.
     */
    public SimpleTreeNode() {
        super();
    }

    /**
     * Initializes the Nodes' data.
     *
     * @param data Value to which data will be initialized.
     * @see TreeNode#TreeNode(Object)
     */
    public SimpleTreeNode(E data) {
        super(data);
    }

    /**
     * Initializes the Nodes' data and parent node reference.
     *
     * @param data Value to which data will be initialized.
     * @param parentNode Value to which the nodes' parent reference will be set.
     * @see TreeNode#TreeNode(Object, Node)
     */
    public SimpleTreeNode(E data, SimpleTreeNode<E> parentNode) {
        super(data, parentNode);
    }

    /**
     * Initializes the Nodes' data and parent and child nodes references.
     *
     * @param data Value to which data will be initialized.
     * @param parentNode Value to which the nodes' parent reference will be set.
     * @param leftNode Value to which the nodes' left child reference will be
     * set.
     * @param rightNode Value to which the nodes' right child reference will be
     * set.
     */
    public SimpleTreeNode(
        E data,
        SimpleTreeNode<E> parentNode,
        SimpleTreeNode<E> leftNode,
        SimpleTreeNode<E> rightNode
    ) {
        super(data, parentNode);
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    /**
     * @return True if the node is a leaf node, otherwise false.
     * @see TreeNode#isLeafNode()
     */
    @Override
    public boolean isLeafNode() {
        return (leftNode == null && rightNode == null);
    }

    public SimpleTreeNode<E> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(SimpleTreeNode<E> leftNode) {
        this.leftNode = leftNode;
    }

    public SimpleTreeNode<E> getRightNode() {
        return rightNode;
    }

    public void setRightNode(SimpleTreeNode<E> rightNode) {
        this.rightNode = rightNode;
    }
}
