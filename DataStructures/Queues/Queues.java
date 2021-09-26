package DataStructures.Queues;

/**
 * This implements Queues by using the class Queue.
 *
 * <p>A queue data structure functions the same as a real world queue. The elements that are added
 * first are the first to be removed. New elements are added to the back/rear of the queue.
 */
class Queue {
  /** Default initial capacity. */
  private static final int DEFAULT_CAPACITY = 10;

  /** Max size of the queue */
  private int maxSize;
  /** The array representing the queue */
  private int[] queueArray;
  /** Front of the queue */
  private int front;
  /** Rear of the queue */
  private int rear;
  /** How many items are in the queue */
  private int nItems;

  /** init with DEFAULT_CAPACITY */
  public Queue() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * Constructor
   *
   * @param size Size of the new queue
   */
  public Queue(int size) {
    maxSize = size;
    queueArray = new int[size];
    front = 0;
    rear = -1;
    nItems = 0;
  }

  /**
   * Inserts an element at the rear of the queue
   *
   * @param x element to be added
   * @return True if the element was added successfully
   */
  public boolean insert(int x) {
    if (isFull()) return false;
    // If the back of the queue is the end of the array wrap around to the front
    rear = (rear + 1) % maxSize;
    queueArray[rear] = x;
    nItems++;
    return true;
  }

  /**
   * Remove an element from the front of the queue
   *
   * @return the new front of the queue
   */
  public int remove() {
    if (isEmpty()) {
      return -1;
    }
    int temp = queueArray[front];
    front = (front + 1) % maxSize;
    nItems--;
    return temp;
  }

  /**
   * Checks what's at the front of the queue
   *
   * @return element at the front of the queue
   */
  public int peekFront() {
    return queueArray[front];
  }

  /**
   * Checks what's at the rear of the queue
   *
   * @return element at the rear of the queue
   */
  public int peekRear() {
    return queueArray[rear];
  }

  /**
   * Returns true if the queue is empty
   *
   * @return true if the queue is empty
   */
  public boolean isEmpty() {
    return nItems == 0;
  }

  /**
   * Returns true if the queue is full
   *
   * @return true if the queue is full
   */
  public boolean isFull() {
    return nItems == maxSize;
  }

  /**
   * Returns the number of elements in the queue
   *
   * @return number of elements in the queue
   */
  public int getSize() {
    return nItems;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = front; ; i = ++i % maxSize) {
      sb.append(queueArray[i]).append(", ");
      if (i == rear) {
        break;
      }
    }
    sb.replace(sb.length() - 2, sb.length(), "]");
    return sb.toString();
  }
}

/**
 * This class is the example for the Queue class
 *
 * @author Unknown
 */
public class Queues {
  /**
   * Main method
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    Queue myQueue = new Queue(4);
    myQueue.insert(10);
    myQueue.insert(2);
    myQueue.insert(5);
    myQueue.insert(3);
    // [10(front), 2, 5, 3(rear)]

    System.out.println(myQueue.isFull()); // Will print true

    myQueue.remove(); // Will make 2 the new front, making 10 no longer part of the queue
    // [10, 2(front), 5, 3(rear)]

    myQueue.insert(7); // Insert 7 at the rear which will be index 0 because of wrap around
    // [7(rear), 2(front), 5, 3]

    System.out.println(myQueue.peekFront()); // Will print 2
    System.out.println(myQueue.peekRear()); // Will print 7
    System.out.println(myQueue.toString()); // Will print [2, 5, 3, 7]
  }
}
