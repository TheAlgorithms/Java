package datastructures.queues;


/**
 * This class is the example for the Queue class
 *
 * @author Unknown
 */
class QueueTest {
    public static void main() {
        Queue myQueue = new Queue(4);
        myQueue.insert(10);
        myQueue.insert(2);
        myQueue.insert(5);
        myQueue.insert(3);
        //[10(front), 2, 5, 3(rear)]

        System.out.println(myQueue.isFull()); //Will print true

        myQueue.remove(); //Will make 2 the new front, making 10 no longer part of the queue
        //[10, 2(front), 5, 3(rear)]

        myQueue.insert(7); //Insert 7 at the rear which will be index 0 because of wrap around
        // [7(rear), 2(front), 5, 3]

        System.out.println(myQueue.peekFront()); //Will print 2
        System.out.println(myQueue.peekRear()); //Will print 7
    }
}

