package DevUtils;

import java.util.Collection;

public class NestedNodeCollection<E> extends Node<E> {
    private Collection<Node<E>> nestedNodes;

    public NestedNodeCollection() {}

    public NestedNodeCollection(E data) {
        super(data);
    }

    public NestedNodeCollection(E data, Collection<Node<E>> nestedNodes) {
        super(data);
        this.nestedNodes = nestedNodes;
    }

    public Collection<Node<E>> getNestedNodes() {
        return nestedNodes;
    }

    public void setNestedNodes(Collection<Node<E>> nestedNodes) {
        this.nestedNodes = nestedNodes;
    }
}
