import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueues {
    public static void main(String[] args) {
        StackQueues stack = new StackQueues();

        // push operation
        stack.push(5);
        stack.push(2);
        stack.push(9);

        // top operation
        System.out.println("Top element: " + stack.top()); 

        // pop operation
        System.out.println("Popped element: " + stack.pop()); 

        // top element
        System.out.println("Current top element: " + stack.top()); 
        
        System.out.println("\n");
        
         // push operation
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // top operation
        System.out.println("Top element: " + stack.top()); 

        // pop operation
        System.out.println("Popped element: " + stack.pop()); 

        // top element
        System.out.println("Current top element: " + stack.top()); 
        
        System.out.println("\n");
        
         // push operation
        stack.push(4);
        stack.push(7);
        stack.push(1);

        // top operation
        System.out.println("Top element: " + stack.top()); 

        // pop operation
        System.out.println("Popped element: " + stack.pop()); 

        // top element
        System.out.println("Current top element: " + stack.top()); 
        
        
    }

    static class StackQueues {
        private Queue<Integer> queue1;
        private Queue<Integer> queue2;

        public StackQueues() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        // Push operation
        public void push(int x) {
            queue1.add(x);
        }

        // Pop operation
        public int pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            while (queue1.size() > 1) {
                queue2.add(queue1.remove());
            }
            int popValue = queue1.remove();
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
            return popValue;
        }

        // Top operation
        public int top() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            while (queue1.size() > 1) {
                queue2.add(queue1.remove());
            }
            int topValue = queue1.peek();
            queue2.add(queue1.remove());
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
            return topValue;
        }

        // Check if stack is empty
        public boolean isEmpty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }
}
