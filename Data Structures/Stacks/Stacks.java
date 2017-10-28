import java.util.ArrayList;

/**
 * This class implements a Stack using two different implementations.
 * Stack is used with a regular array and Stack2 uses an ArrayList.
 *
 * A stack is exactly what it sounds like. An element gets added to the top of
 * the stack and only the element on the top may be removed. This is an example
 * of an array implementation of a Stack. So an element can only be added/removed
 * from the end of the array. In theory stack have no fixed size, but with an
 * array implementation it does.
 *
 * @author Unknown
 *
 */
class Stack{
	/** The max size of the Stack */
	private int maxSize;
	/** The array representation of the Stack */
	private int[] stackArray;
	/** The top of the stack */
	private int top;

	/**
	 * Constructor
	 *
	 * @param size Size of the Stack
	 */
	public Stack(int size){
		maxSize = size;
		stackArray = new int[maxSize];
		top = -1;
	}

	/**
	 * Adds an element to the top of the stack
	 *
	 * @param value The element added
	 */
	public void push(int value){
		if(!isFull()){ //Checks for a full stack
			top++;
			stackArray[top] = value;
		}else{
			System.out.println("The stack is full, can't insert value");
		}
	}

	/**
	 * Removes the top element of the stack and returns the value you've removed
	 *
	 * @return value popped off the Stack
	 */
	public int pop(){
		if(!isEmpty()){ //Checks for an empty stack
			return stackArray[top--];
		}else{
			System.out.println("The stack is already empty");
			return -1;
		}
	}

	/**
	 * Returns the element at the top of the stack
	 *
	 * @return element at the top of the stack
	 */
	public int peek(){
		if(!isEmpty()){ //Checks for an empty stack
			return stackArray[top];
		}else{
			System.out.println("The stack is empty, cant peek");
			return -1;
		}
	}

	/**
	 * Returns true if the stack is empty
	 *
	 * @return true if the stack is empty
	 */
	public boolean isEmpty(){
		return(top == -1);
	}

	/**
	 * Returns true if the stack is full
	 *
	 * @return true if the stack is full
	 */
	public boolean isFull(){
		return(top+1 == maxSize);
	}

	/**
	 * Deletes everything in the Stack
	 *
	 * Doesn't delete elements in the array
	 * but if you call push method after calling
	 * makeEmpty it will overwrite previous
	 * values
	 */
	public void makeEmpty(){ //Doesn't delete elements in the array but if you call
		top = -1;			 //push method after calling makeEmpty it will overwrite previous values
	}
}

/**
 * This is an ArrayList Implementation of stack, Where size is not
 * a problem we can extend the stack as much as we want.
 *
 * @author Unknown
 *
 */
class Stack2{
		/** ArrayList representation of the stack */
		ArrayList<Integer> stackList;

		/**
		 * Constructor
		 */
		Stack2(){
			stackList=new ArrayList<>();
		}

		/**
		 * Adds value to the end of list which
		 * is the top for stack
		 *
		 * @param value value to be added
		 */
		void push(int value){
			stackList.add(value);
		}

		/**
		 * Pops last element of list which is indeed
		 * the top for Stack
		 *
		 * @return Element popped
		 */
		int pop(){

			if(!isEmpty()){ // checks for an empty Stack

				int popValue=stackList.get(stackList.size()-1);
				stackList.remove(stackList.size()-1);  //removes the poped element from the list
				return popValue;
			}
			else{
				System.out.print("The stack is already empty  ");
				return -1;
			}

		}

		/**
		 * Checks for empty Stack
		 *
		 * @return true if stack is empty
		 */
		boolean isEmpty(){
			if(stackList.isEmpty())
				return true;

			else return false;

		}

		/**
		 * Top element of stack
		 *
		 * @return top element of stack
		 */
		int peek(){
			return stackList.get(stackList.size()-1);
		}
	}

/**
 * This class implements the Stack and Stack2 created above
 *
 * @author Unknown
 *
 */
public class Stacks{
	/**
	 * Main method
	 *
	 * @param args Command line arguments
	 */
	public static void main(String args[]){
			Stack myStack = new Stack(4); //Declare a stack of maximum size 4
 		//Populate the stack
 		myStack.push(5);
 		myStack.push(8);
  		myStack.push(2);
  		myStack.push(9);

		System.out.println("*********************Stack Array Implementation*********************");
  		System.out.println(myStack.isEmpty()); //will print false
  		System.out.println(myStack.isFull()); //will print true
  		System.out.println(myStack.peek()); //will print 9
  		System.out.println(myStack.pop()); //will print 9
  		System.out.println(myStack.peek()); // will print 2

		Stack2 myStack2 = new Stack2(); //Declare a stack of maximum size 4
  		//Populate the stack
 		myStack2.push(5);
		myStack2.push(8);
 		myStack2.push(2);
 		myStack2.push(9);

 		System.out.println("*********************Stack List Implementation*********************");
 		System.out.println(myStack2.isEmpty()); //will print false
 		System.out.println(myStack2.peek()); //will print 9
 		System.out.println(myStack2.pop()); //will print 9
 		System.out.println(myStack2.peek()); // will print 2
 		System.out.println(myStack2.pop()); //will print 2
	}
}
