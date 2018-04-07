public interface Queue {

	public void add(Object object);

	public Object first();

	public Object remove();

	public int size();

}
public class RingQueue implements Queue {
	private Object[] objArray = null;
	private int size;
	private int front;
	private int rear;

	public RingQueue(int n) {
		// Create an array of size n
		objArray = new Object[n];
		size = 0;
		front = 0;
		rear = 0;
	}

	@Override
	public void add(Object obj) {
		// type obj if there is empty space in the queue
		// if there is no empty space in the queue throw new IllegalStateException ("The queue is full");
		if (size == objArray.length)
			throw new IllegalStateException("The queue is full");

		else {
			rear = (rear + 1) % objArray.length;
			objArray[rear] = obj;
			size++;
		}

	}

	@Override
	public Object first() {
		// return the first element if the queue is not empty
		// if the queue is empty throw new IllegalStateException ("The queue is empty");
		if (size == 0)
			throw new IllegalStateException("The queue is empty");

		return objArray[(front + 1) % objArray.length];
	}

	@Override
	public Object remove() {
		// If the queue is not empty, delete the first element and return
		// if the queue is empty throw new IllegalStateException ("The queue is empty");
		Object object = new Object();

		if (size == 0)
			throw new IllegalStateException("The queue is empty");

		else {
			front = (front + 1) % objArray.length;
			object = objArray[front];

			size--;
		}
		return object;
	}

	@Override
	public int size() {
		// returns the number of elements stored in the queue
		return size;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		if (objArray == null)
			return sb.toString();

		if (front == rear)
			return sb.toString();

		int tempFront = front;
		do {
			tempFront = (tempFront + 1) % objArray.length;
			sb.append(objArray[tempFront]);
		} while (tempFront != rear);

		return sb.toString();
	}

}
