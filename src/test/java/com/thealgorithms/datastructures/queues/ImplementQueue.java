public class ImplementQueue {
    // implementation of queue using array
    // Queue Algorith type:FIFO (First Come First Out)

    // create Queue class
    public static class Queue {
        int arr[];
        int size;
        int rear;

        public Queue(int n) {
            arr = new int[n];
            size = n;
            rear = -1; // initizalation of the rear
        }

        // operations

        // IsEmpty() method
        public boolean isEmpty() {
            return rear == -1; // when rear will be -1 that means no element is there in the queue
        }

        public boolean isOverflow() {

            return rear == size - 1;
        }

        // add() method
        public void add(int data) {
            if (isOverflow()) {
                System.out.println("Queue is full now !!");
            }
            rear = rear + 1;
            arr[rear] = data;
        }

        // remove() method
        public int remove() {
            if (isEmpty()) {
                System.out.println("Queue is Empty !!");
                return -1;
            }
            int front = arr[0]; // 1 2 3 4 here front will point to 1
                                // now replacing the 1 to 2 and 2 to 3 and so on..
            for (int i = 0; i < rear; i++) {
                arr[i] = arr[i + 1];
            }
            rear = rear - 1;
            return front; // removed element
        }

        // peek() method

        public int peek() {
            if (isEmpty()) {
                System.out.println("No element is there");
                return -1;
            }
            return arr[0];
        }

    }

    public static void main(String[] args) {
        Queue q = new Queue(5);
        q.add(1);
        q.add(9);
        q.add(10);

        System.out.println("Front Element:" + q.peek());
        System.out.println("Removed Element:" + q.remove());

        q.add(90);
        q.add(100);

        q.add(120);
        q.add(56);
        System.out.println(q.isOverflow());

    }
}