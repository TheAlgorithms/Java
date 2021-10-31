package DevUtils;

public abstract class Node<E> {
    private E data;

    public Node() {}

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
