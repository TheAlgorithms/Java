package DevUtils.Nodes;

import java.util.Collection;
import java.util.ArrayList;

public class LargeTreeNode<E> extends TreeNode<E> {
    private Collection<Node<E>> childNodes;

    public LargeTreeNode() {
        super();
        childNodes = new ArrayList<>();
    }

    public LargeTreeNode(E data) {
        super(data);
        childNodes = new ArrayList<>();
    }

    public LargeTreeNode(E data, Node<E> parentNode) {
        super(data, parentNode);
        childNodes = new ArrayList<>();
    }

    public LargeTreeNode(E data, Node<E> parentNode, Collection<Node<E>> childNodes) {
        super(data, parentNode);
        this.childNodes = childNodes;
    }

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
