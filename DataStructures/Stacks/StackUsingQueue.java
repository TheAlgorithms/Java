package DataStructures.Stacks;
import java.util.*;

public class StackUsingQueue {
    private Queue<Integer> queue;
    public StackUsingQueue() {
        queue = new LinkedList<Integer>();
    }
    
    public void push(int x) {
        queue.add(x);
        int size = queue.size();
        while(size-- > 1) {
            queue.add(queue.remove());
        }
    }
    
    public int pop() {
        return queue.remove();
    }
    
    public int top() {
        return queue.peek();
    }
    
    public boolean empty() {
        return queue.isEmpty();
    }
}
