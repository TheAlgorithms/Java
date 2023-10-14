import java.util.LinkedList;
import java.util.Queue;

class StackUsingQueues {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public StackUsingQueues() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int item) {
        // Add the item to queue1.
        queue1.add(item);
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        // Move all elements from queue1 to queue2 except the last one.
        while (queue1.size() > 1) {
            queue2.add(queue1.remove());
        }

        // The last element in queue1 is the one to be popped.
        int poppedElement = queue1.remove();

        // Swap the references of queue1 and queue2.
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        return poppedElement;
    }

    public int top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        int topElement = 0;
        while (!queue1.isEmpty()) {
            topElement = queue1.remove();
            queue2.add(topElement);
        }

        // Swap the references of queue1 and queue2.
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        return topElement;
    }

    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    public int size() {
        return queue1.size();
    }

    public static void main(String[] args) {
        StackUsingQueues stack = new StackUsingQueues();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Top of stack: " + stack.top());  // Output: 3
        System.out.println("Stack size: " + stack.size());    // Output: 3

        stack.pop();
        System.out.println("Top of stack after pop: " + stack.top());  // Output: 2
        System.out.println("Stack size after pop: " + stack.size());    // Output: 2
    }
}
