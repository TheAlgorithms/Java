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
        private Queue<Integer> q1;
        private Queue<Integer> q2;

        public StackQueues() {
            q1 = new LinkedList<>();
            q2 = new LinkedList<>();
        }

        // Push operation
        public void push(int x) {
            q1.add(x);
        }

        // Pop operation
        public int pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            while (q1.size() > 1) {
                q2.add(q1.remove());
            }
            int popValue = q1.remove();
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            return popValue;
        }

        // Top operation
        public int top() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            while (q1.size() > 1) {
                q2.add(q1.remove());
            }
            int top_res = q1.peek();
            q2.add(q1.remove());
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            return top_res;
        }

        // Check if stack is empty
        public boolean isEmpty() {
            return q1.isEmpty() && q2.isEmpty();
        }
    }
}
