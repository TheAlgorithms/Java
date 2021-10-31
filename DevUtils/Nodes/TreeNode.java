package DevUtils.Nodes;

/**
 * Base class for any tree node which
 * holds a reference to the parent node.
 * 
 * All known subclasses: {@link SimpleTreeNode}, {@link LargeTreeNode}.
 * 
 * @param <E> The type of the data held in the Node.
 * 
 * @author aitorfi (https://github.com/aitorfi)
 */
public abstract class TreeNode<E> extends Node<E> {
    /** Refernce to the parent Node. */
    private Node<E> parentNode;

    /** Empty contructor. */
    public TreeNode() {
        super();
    }

    /**
     * Initializes the Nodes' data.
     * 
     * @param data Value to which data will be initialized.
     * @see Node#Node(Object)
     */
    public TreeNode(E data) {
        super(data);
    }

    /**
     * Initializes the Nodes' data and parent node reference.
     * 
     * @param data Value to which data will be initialized.
     * @param parentNode Value to which the nodes' parent reference will be set.
     */
    public TreeNode(E data, Node<E> parentNode) {
        super(data);
        this.parentNode = parentNode;
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

    public Node<E> getParent() {
        return parentNode;
    }

    public void setParent(Node<E> parentNode) {
        this.parentNode = parentNode;
    }
}
