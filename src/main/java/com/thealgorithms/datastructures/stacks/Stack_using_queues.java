// Java program to implement stack using two queues
import java.util.*;

class Stack_using_queues {

    static class Stack {
        // Two inbuilt queues
        static Queue<Integer> q1 = new LinkedList<Integer>();
        static Queue<Integer> q2 = new LinkedList<Integer>();


        // To maintain current number of
        // elements
        static int curr_size;

        static void push(int x) {
            // Push x first in empty q1
            q1.add(x);

            // Push all the remaining
            // elements in q2 to q1.
            while (!q2.isEmpty()) {
                q1.add(q2.peek());
                q2.remove();
            }

            // swap the names of two queues
            Queue<Integer> q = q1;
            q1 = q2;
            q2 = q;
        }

        static int pop() {

            // if no elements are there in q2
            if (q2.isEmpty()) {
                return -1;
            }
            return (q2.remove());
        }

        static int top() {
            if (q2.isEmpty()) return -1;
            return q2.peek();
        }

        static int size() {
            return q2.size(); 
        }
    }


    public static void main(String[] args) {
        Stack stack = new Stack();
        Scanner in = new Scanner(System.in);
        int choice, element;
        System.out.println("Enter 1 to push,2 to pop ,3 to peek elements and 4 for the size and -1 to exit ");
        choice = in.nextInt();
        while (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
            switch (choice) {
            case 1:
                System.out.println("Enter the element to be pushed:");
                element = in.nextInt();
                stack.push(element);
                break;
            case 2:
                System.out.println("Poped element " + stack.pop());
                break;
            case 3:
                System.out.println("Topmost element " + stack.top());
                break;
            case 4:
                System.out.println("Size of the stack " + stack.size());
                break;
            default:
                System.out.println("Enter correctly");
             }
             System.out.println("Enter 1 to push,2 to pop ,3 to peek elements and 4 for the size and -1 to exit ");
             choice = in.nextInt();
        }
    }
}


