package com.thealgorithms.devutils.nodes;

/**
 * Base class for any tree node which holds a reference to the parent node.
 *
 * All known subclasses: {@link SimpleTreeNode}, {@link LargeTreeNode}.
 *
 * @param <E> The type of the data held in the Node.
 *
 * @author <a href="https://github.com/aitorfi">aitorfi</a>
 */
public abstract class TreeNode<E> extends Node<E> {

    /**
     * Refernce to the parent Node.
     */
    private TreeNode<E> parentNode;
    /**
     * Indicates the depth at which this node is in the tree.
     */
    private int depth;

    /**
     * Empty contructor.
     */
    public TreeNode() {
        super();
        depth = 0;
    }

    /**
     * Initializes the Nodes' data.
     *
     * @param data Value to which data will be initialized.
     * @see Node#Node(Object)
     */
    public TreeNode(E data) {
        super(data);
        depth = 0;
    }

    /**
     * Initializes the Nodes' data and parent node reference.
     *
     * @param data Value to which data will be initialized.
     * @param parentNode Value to which the nodes' parent reference will be set.
     */
    public TreeNode(E data, TreeNode<E> parentNode) {
        super(data);
        this.parentNode = parentNode;
        depth = this.parentNode.getDepth() + 1;
    }

    /**
     * @return True if the node is a leaf node, otherwise false.
     */
    public abstract boolean isLeafNode();

    /**
     * @return True if the node is the root node, otherwise false.
     */
    public boolean isRootNode() {
        return (parentNode == null);
    }

    public TreeNode<E> getParent() {
        return parentNode;
    }

    public void setParent(TreeNode<E> parentNode) {
        this.parentNode = parentNode;
        depth = this.parentNode.getDepth() + 1;
    }

    public int getDepth() {
        return depth;
    }
}
