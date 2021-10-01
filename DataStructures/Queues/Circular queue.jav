import java.util.ArrayList;
import java.util.Scanner;
public class CircularQueue {

    int size, front, rear; // Declaring the class variables.
    ArrayList < Integer > queue = new ArrayList < Integer > (); // Declaring array list of integer type.

    CircularQueue(int size) // Class constructor
    {
        this.size = size;
        this.front = this.rear = -1;
    }

    public void enQueue(int data) // Function to insert a new element in the queue.
    {

        if ((front == 0 && rear == size - 1) || (rear == (front - 1) % (size - 1))) // Condition if queue is full.
        {
            System.out.print("Queue is Full");
        } else if (front == -1) // Condition for empty queue.
        {
            front = 0;
            rear = 0;
            queue.add(rear, data);
        } else if (rear == size - 1 && front != 0) {
            rear = 0;
            queue.set(rear, data);
        } else {
            rear = (rear + 1);

            if (front <= rear) // Adding a new element if
            {
                queue.add(rear, data);
            } else // Else updating old value
            {
                queue.set(rear, data);
            }
        }
    }

    public int deQueue() // Function to dequeue an element
    {
        int temp;

        if (front == -1) // Condition for empty queue, return -1 
        {
            System.out.print("Queue is Empty");

            return -1;
        }

        temp = queue.get(front);

        if (front == rear) // Condition for only one element
        {
            front = -1;
            rear = -1;
        } else if (front == size - 1) {
            front = 0;
        } else {
            front = front + 1;
        }

        return temp; //return the element deleted
    }

    // Method to display the elements of queue
    public void displayQueue() {

        if (front == -1) // Condition for empty queue.
        {
            System.out.print("Queue is Empty");
            return;
        }

        System.out.print("Elements in the " + "circular queue are: "); // If rear has not crossed the max size or queue rear is still greater then front.

        if (rear >= front) {

            for (int i = front; i <= rear; i++) // Loop to print elements from front to rear.
            {
                System.out.print(queue.get(i));
                System.out.print(" ");
            }
            System.out.println();
        } else // If rear crossed the max index and indexing has started in loop
        {

            for (int i = front; i < size; i++) // Loop to print elements from front to max size or last index
            {
                System.out.print(queue.get(i));
                System.out.print(" ");
            }

            for (int i = 0; i <= rear; i++) // Loop to print elements from 0th index till rear position
            {
                System.out.print(queue.get(i));
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter size of queue to help initialize. This can be adjusted depending on later operations");
        int n = sc.nextInt();
        CircularQueue q = new CircularQueue(n); // Initialising new object of CircularQueue class.

        for (int i = 1; i <= n; i++) {
            System.out.println("Enter element at " + i);
            j = sc.nextInt();
            q.enQueue(j);
        }
        System.out.println("The initial queue is-");
        q.displayQueue();
        int flag = 1;
        while (flag == 1) {
            int flag_1 = 1
            if (flag_1 == 1) {
                System.out.println("Enter 1 to deque, 2 to exit deque loop");
                flag_1 = sc.nextInt();
                int x = q.deQueue(); // Checking for empty queue.
                if (x != -1) {
                    System.out.print("Deleted value = ");
                    System.out.println(x);
                }
            }

            int flag_1 = 1
            if (flag_1 == 1) {
                System.out.println("Enter 1 to enqueue, 2 to exit enqueue loop");
                flag_1 = sc.nextInt();
                int x = q.enQueue();
            }
            if (q.isEmpty())
                System.out.println("The queue is empty");
            else {
                System.out.println("The queue is after operations is-");
                q.displayQueue();
            }

            System.out.println("Enter 1 to continue operations 2 to exit.");
            flag = sc.nextInt();
        }
    }
}
}