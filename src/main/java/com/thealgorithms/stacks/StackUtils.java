import java.util.Stack;

/** 
 * This utility provides commonly used operations related to stacks, which can be beneficial for solving various problems such as expression evaluations, balanced parentheses, or maintaining a history of operations
 * MinStack: This custom stack keeps track of the minimum element so that getMin() can return the minimum in O(1) time.
 * Next Greater Element: For each element in an array, the utility finds the next greater element on the right side using a stack.
 * Balanced Parentheses Checker: It checks if an expression has matching and balanced parentheses.
 * Reverse a Stack: This utility reverses the elements in a stack using recursion.
 * @author Mohit Singh
 * @author <a href="https://github.com/mohit-gogitter">mohit-gogitter<a>
 */

public class Main {

    // 1. MinStack - Stack supporting push, pop, and retrieving minimum in O(1) time
    static class MinStack {
        private Stack<Integer> mainStack;
        private Stack<Integer> minStack;

        public MinStack() {
            mainStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int value) {
            mainStack.push(value);
            if (minStack.isEmpty() || value <= minStack.peek()) {
                minStack.push(value);
            }
        }

        public void pop() {
            if (!mainStack.isEmpty()) {
                int poppedValue = mainStack.pop();
                if (poppedValue == minStack.peek()) {
                    minStack.pop();
                }
            }
        }

        public int getMin() {
            return minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peek();
        }

        public int top() {
            return mainStack.isEmpty() ? -1 : mainStack.peek();
        }

        public boolean isEmpty() {
            return mainStack.isEmpty();
        }
    }

    // 2. Next Greater Element for each element in an array
    public static int[] nextGreaterElement(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return result;
    }

    // 3. Balanced Parentheses Checker
    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty())
                    return false;
                char open = stack.pop();
                if (!isMatchingPair(open, c))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }

    // 4. Reverse a Stack
    public static <T> void reverseStack(Stack<T> stack) {
        if (stack.isEmpty())
            return;
        T temp = stack.pop();
        reverseStack(stack);
        insertAtBottom(stack, temp);
    }

    private static <T> void insertAtBottom(Stack<T> stack, T value) {
        if (stack.isEmpty()) {
            stack.push(value);
        } else {
            T temp = stack.pop();
            insertAtBottom(stack, value);
            stack.push(temp);
        }
    }

    public static void main(String[] args) {
        // Example usage of MinStack
        MinStack minStack = new MinStack();
        minStack.push(3);
        minStack.push(5);
        minStack.push(2);
        minStack.push(1);
        System.out.println("Current Min: " + minStack.getMin()); // Output: 1
        minStack.pop();
        System.out.println("Current Min after pop: " + minStack.getMin()); // Output: 2

        // Example usage of nextGreaterElement
        int[] nums = { 4, 5, 2, 10, 8 };
        int[] nextGreater = nextGreaterElement(nums);
        System.out.println("Next Greater Elements: ");
        for (int val : nextGreater) {
            System.out.print(val + " ");
        } // Output: 5 10 10 -1 -1

        // Example usage of isBalanced
        String expr = "{[()]}";
        System.out.println("\nIs the expression balanced? " + isBalanced(expr)); // Output: true

        // Example usage of reverseStack
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        reverseStack(stack);
        System.out.println("Reversed Stack: " + stack); // Output: [3, 2, 1]
    }
}
