package main.java.com.dataStructures;// import org.graalvm.compiler.graph.DeqNode;

public class DequeueList<T extends Comparable> {
    private DeqNode<T> front = null, tail = null;

    public DequeueList() {
    }

    public boolean insertHead(T key) {
        try {
            DeqNode n = new DeqNode(key);
            if (front == null) {
                tail = front = n;
                return true;
            }
            n.setNext(front);
            front.setPrev(n);
            front = n;
            return true;
        } catch (OutOfMemoryError em) {
            System.out.println("Unable to allocate DeqNode for key " + key + " .");
            return false;
        }
    }


    public boolean insertTail(T key) {
        try {
            DeqNode<T> n = new DeqNode(key);
            if (tail == null) {
                tail = front = n;
                return true;
            }
            tail.setNext(n);
            n.setPrev(tail);
            tail = n;
            return true;
        } catch (OutOfMemoryError memoryException) {
            System.out.println("Unable to allocate DeqNode for key " + key + " .");
            return false;
        }
    }


    public DeqNode<T> removeHead() {
         if ( front == null || tail == null )
         return null;
        DeqNode<T> n = front;
         if ( front == tail) {
         front = tail = null;
         return n;
         }
        front = front.getNext();
        front.setPrev(null);
        return n;
    }


    public DeqNode<T> removeTail() {
         if ( front == null || tail == null) {
         return null;
         }
        DeqNode<T> n = tail;
         if ( front == tail ) {
            front = tail = null;
            return n;
         }
        tail = tail.getPrev();
        tail.getNext().setPrev(null);
        tail.setNext(null);
        return n;
    }

    public DeqNode<T> getFront() {
            return front;
    }

    public DeqNode<T> getTail() {
        return tail;
    }
}

