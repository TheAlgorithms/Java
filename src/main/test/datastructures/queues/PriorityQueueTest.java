package datastructures.queues;


/**
 * This class implements the PriorityQueue class above.
 *
 * @author Unknown
 */
public class PriorityQueueTest {

    public static void main() {
        PriorityQueue myQueue = new PriorityQueue(4);
        myQueue.insert(10);
        myQueue.insert(2);
        myQueue.insert(5);
        myQueue.insert(3);
        //[2, 3, 5, 10] Here higher numbers have higher priority, so they are on the top

        for (int i = 3; i >= 0; i--)
            System.out.print(myQueue.remove() + " "); //will print the queue in reverse order [10, 5, 3, 2]

        //As you can see, a Priority Queue can be used as a sorting algotithm
    }
}