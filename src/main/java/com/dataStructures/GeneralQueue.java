package src.main.java.com.dataStructures;

import src.main.java.com.types.Queue;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * linkedList based implementation of queue.
 * This implementation is not thread safe and need exclusive thread safety measures from the client.
 * @param <T>
 */
public class GeneralQueue<T> implements Queue<T> {

    private LinkedList<T> queue;
    private Iterator<T> itr;

    //Overloaded constructor to create queue of specific size
    public GeneralQueue() {
        queue = new LinkedList<>();
    }

    @Override
    public boolean add(T t) {

        if(queue == null) {
            throw new NullPointerException();
        }

        queue.add(t);
        return true;
    }

    @Override
    public boolean remove(T t) {
        if(null == queue || queue.size() == 0){
            throw new NullPointerException();
        }
        queue.remove(t);
        return true;
    }

    @Override
    public boolean isEmpty() {

        if(null == queue || queue.size() == 0){
            return true;
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {

        if(queue == null) {
            return null;
        }
        itr = queue.iterator();
        return itr;
    }

    @Override
    public boolean offer(T t) {
        if(null == queue) {
            return false;
        }

        queue.add(t);
        return true;
    }

    @Override
    public T poll() {

        if(queue == null || queue.isEmpty()){
            return  null;
        }

        return queue.pollFirst();
    }

    @Override
    public T element() {

        if(queue == null || queue.isEmpty()) {
            throw new NullPointerException();
        }

        return queue.peekFirst();
    }

    @Override
    public T peek() {
        if(null == queue ||  queue.size() == 0){
            return  null;
        }

        return queue.peekFirst();
    }

    @Override
    public boolean hasNext() {

        if(itr.hasNext()){
            return true;
        }
        return false;
    }

    @Override
    public T next() {

        return itr.next();
    }

    @Override
    public Object[] toArray() {

        Object[] elements = {};
        if(null == queue || queue.isEmpty()){
            return elements;
        }
        elements = new Object[queue.size()];
        for(int i=0;i<queue.size();i++){
            elements[i] = queue.get(i);
        }

        return elements;
    }

    @Override
    public int size() {
        if(null == queue || queue.isEmpty()) {
            return 0;
        }

        return queue.size();
    }


}
