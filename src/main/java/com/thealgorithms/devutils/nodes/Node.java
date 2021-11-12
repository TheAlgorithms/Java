package com.thealgorithms.devutils.nodes;

/**
 * Base class for any node implementation which contains a generic type
 * variable.
 *
 * All known subclasses: {@link TreeNode}, {@link SimpleNode}.
 *
 * @param <E> The type of the data held in the Node.
 *
 * @author <a href="https://github.com/aitorfi">aitorfi</a>
 */
public abstract class Node<E> {

    /**
     * Generic type data stored in the Node.
     */
    private E data;

    /**
     * Empty constructor.
     */
    public Node() {
    }

    /**
     * Initializes the Nodes' data.
     *
     * @param data Value to which data will be initialized.
     */
    public Node(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
