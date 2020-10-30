package DataStructures.Queues;

import DataStructures.Stacks.StackArray;

class StackQueue {
	private static final int DEFAULT_CAPACITY = 20;
	
	private StackArray stack1;
	private StackArray stack2;
	
	public static void main(String[] args) {
	    StackQueue myQueue = new StackQueue(4);
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
	  }

	public StackQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	public StackQueue(int size) {
		this.stack1 = new StackArray(size);
		this.stack2 = new StackArray(size);
	}
	
	public boolean isFull() {
		return stack1.isFull();
	}
	
	public boolean isEmpty() {
		return stack1.isEmpty();
	}
	
	public boolean insert(int value) {
		if (stack1.isFull()) return false;
		
		stack1.push(value);
		return true;
	}
	
	public int remove() {
		if (stack1.isEmpty()) return -1;
		
		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		
		int temp = stack2.pop();
		
		while (!stack2.isEmpty()) {
			stack1.push(stack2.pop());
		}
		
		return temp;
	}
	
	public int peekFront() {
		if (stack1.isEmpty()) return -1;
		
		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		
		int temp = stack2.peek();
		
		while (!stack2.isEmpty()) {
			stack1.push(stack2.pop());
		}
		
		return temp;
	}
	
	public int peekRear() {
		return stack1.peek();
	}
	
	public int getSize() {
		return stack1.size();
	}
}
