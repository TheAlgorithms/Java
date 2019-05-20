package main.java.com.dataStructures;

public class DeqNode<T> {
    private T key;
    private DeqNode<T> next = null;
    private DeqNode<T> prev = null;

    DeqNode() {

    }

    public DeqNode(T key) {
        this.key = key;
    }

    /**
     * @return Integer return the front
     */
    public DeqNode<T> getPrev() {
        return prev;
    }

    /**
     * @param prev the front to set
     */
    public void setPrev(DeqNode<T> prev) {
        this.prev = prev;
    }

    /**
     * @return T return the key
     */
    public T getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(T key) {
        this.key = key;
    }

    /**
     * @return DeqNode<T> return the next
     */
    public DeqNode<T> getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(DeqNode<T> next) {
        this.next = next;
    }

}
