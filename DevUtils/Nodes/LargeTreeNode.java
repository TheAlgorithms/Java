package DevUtils.Nodes;

import java.util.Collection;

/**
 * {@link TreeNode} extension that holds a {@link Collection}
 * of refrences to child Nodes.
 * 
 * @param <E> The type of the data held in the Node.
 * 
 * @author aitorfi (https://github.com/aitorfi)
 */
public class LargeTreeNode<E> extends TreeNode<E> {
    /** {@link Collection} that holds the Nodes' child nodes. */
    private Collection<Node<E>> childNodes;

    /** Empty contructor. */
    public LargeTreeNode() {
        super();
    }

    /**
     * Initializes the Nodes' data.
     * 
     * @param data Value to which data will be initialized.
     * @see TreeNode#TreeNode(Object)
     */
    public LargeTreeNode(E data) {
        super(data);
    }

    /**
     * Initializes the Nodes' data and parent node reference.
     * 
     * @param data Value to which data will be initialized.
     * @param parentNode Value to which the nodes' parent reference will be set.
     * @see TreeNode#TreeNode(Object, Node)
     */
    public LargeTreeNode(E data, Node<E> parentNode) {
        super(data, parentNode);
    }

    /**
     * Initializes the Nodes' data and parent and child nodes references.
     * 
     * @param data Value to which data will be initialized.
     * @param parentNode Value to which the nodes' parent reference will be set.
     * @param childNodes {@link Collection} of child Nodes.
     * @see TreeNode#TreeNode(Object, Node)
     */
    public LargeTreeNode(E data, Node<E> parentNode, Collection<Node<E>> childNodes) {
        super(data, parentNode);
        this.childNodes = childNodes;
    }

    /**
     * @return True if the node is a leaf node, otherwise false.
     * @see TreeNode#isLeafNode()
     */
    @Override
    public boolean isLeafNode() {
        return (childNodes == null || childNodes.size() == 0);
    }

    public Collection<Node<E>> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(Collection<Node<E>> childNodes) {
        this.childNodes = childNodes;
    }
}
