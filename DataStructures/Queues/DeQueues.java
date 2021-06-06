package DataStructures.Queues;

/**
 * This implements Queues by using the class DeQueue.
 *
 * <p>A queue data structure functions the same as a real world queue. The elements that are added
 * first are the first to be removed. New elements are added to the back/rear of the queue.
 */
class DeQueue {
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
  public DeQueue() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * Constructor
   *
   * @param size Size of the new queue
   */
  public DeQueue(int size) {
    maxSize = size;
    queueArray = new int[size];
    front = -1;
    rear = -1;
    nItems = 0;
  }

  /**
   * Inserts an element at the rear of the queue
   *
   * @param x element to be added
   * @return True if the element was added successfully
   */
  public boolean insertFront(int x) {
    if (isFull()) return false;
    if (isEmpty()) {
    	front = 0;
    	rear = 0;
    	queueArray[front] = x;
    	nItems++;
    	return true;
    }
    // If the back of the queue is the end of the array wrap around to the front
    front = (front - 1); 
    if(front < 0 ) {
    	front = maxSize + front;
    }
    queueArray[front] = x;
    nItems++;
    return true;
  }
  
  public boolean insertRear(int x) {
	    if (isFull()) return false;
	    if (isEmpty()) {
	    	front = 0;
	    	rear = 0;
	    	queueArray[rear] = x;
	    	nItems++;
	    	return true;
	    }
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
  public int removeFront() {
    if (isEmpty()) {
      return -1;
    }
    int temp = queueArray[front];
    queueArray[front] = 0;
    front = (front + 1) % maxSize;
    nItems--;
    return temp;
  }
  
  public int removeRear() {
	    if (isEmpty()) {
	      return -1;
	    }
	    int temp = queueArray[rear];
	    queueArray[rear] = 0;
	    rear = (rear - 1);
	    if (rear < 0) {
	    	rear = maxSize + rear;
	    }
	    nItems--;
	    return temp;
	  }
  /**
   * Checks what's at the front of the queue
   *
   * @return element at the front of the queue
   */
  public int peekFront() {
	if (front < 0) return -1;
    return queueArray[front];
  }

  /**
   * Checks what's at the rear of the queue
   *
   * @return element at the rear of the queue
   */
  public int peekRear() {
	if (rear < 0) return -1;
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
    for (int i = 0; i < maxSize ; ++i) {
      sb.append(queueArray[i]).append(", ");
     
    }
    sb.replace(sb.length() - 2, sb.length(), "]");
    return sb.toString();
  }
}

/**
 * This class is the example for the DeQueue class
 *
 * @author Unknown
 */
public class DeQueues {
  /**
   * Main method
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {
  
	  
	DeQueue myQueue = new DeQueue(7);
	
	System.out.println(myQueue.peekFront());
    
    System.out.println(myQueue.insertFront(10)); // will print true
    System.out.println(myQueue.insertFront(20)); // will print true
    System.out.println(myQueue.insertFront(30)); // will print true
    
    System.out.println(myQueue.insertRear(40));  // will print true
    System.out.println(myQueue.insertRear(50)); // will print true
   
    System.out.println(myQueue.insertFront(60)); // will print true
    System.out.println(myQueue.insertRear(70));  // will print true
    
    System.out.println(myQueue.insertRear(80));  // will print false
    System.out.println(myQueue.insertFront(90)); // will print false
    
    System.out.println(myQueue.peekFront()); // // will print 60

    System.out.println(myQueue.isFull()); // Will print true

    
    System.out.println(myQueue.toString()); // Will print [10, 40, 50, 70, 60, 30, 20]
    
    myQueue.removeFront();
    myQueue.removeFront();
    System.out.println(myQueue.toString()); 
    
    myQueue.removeRear();
    myQueue.removeRear();
    System.out.println(myQueue.toString());
    
    myQueue.removeRear();
    myQueue.removeFront();
    System.out.println(myQueue.toString());
    
    myQueue.removeFront();
    System.out.println(myQueue.toString()); 
    
    System.out.println(myQueue.removeFront()); // -1
    
    System.out.println(myQueue.isEmpty()); //true
    
    System.out.println(myQueue.peekFront());
    
  }
}
