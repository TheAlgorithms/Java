import java.util.ArrayList;

public class GenericArrayListQueue<T> {
    ArrayList<T> _queue = new ArrayList<T>();

    private boolean hasElements() {
        return !_queue.isEmpty();
    }

    public T peek() {
        T result = null;
        if(this.hasElements()) { result = _queue.get(0); }
        return result;
    }

    public boolean add(T element) {
        return _queue.add(element);
    }

    public T poll() {
        T result = null;
        if(this.hasElements()) { result = _queue.remove(0); }
        return result;
    }

    public static void main(String[] args) {
        GenericArrayListQueue<Integer> queue = new GenericArrayListQueue<Integer>();
        System.out.println("Running...");
        assert queue.peek() == null;
        assert queue.poll() == null;
        assert queue.add(1) == true;
        assert queue.peek() == 1;
        assert queue.add(2) == true;
        assert queue.peek() == 1;
        assert queue.poll() == 1;
        assert queue.peek() == 2;
        assert queue.poll() == 2;
        assert queue.peek() == null;
        assert queue.poll() == null;
        System.out.println("Finished.");
    }
}
