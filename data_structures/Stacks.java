/*
 *A stack is exactly what it sounds like. An element gets added to top of the stack and only the element on the top may be removed.
 *This is an example of an array implementation of a Stack. So an element can only be added/removed from the end of the array.
 *In theory stacks have no fixed size, but with an array implementation it does.
*/
class Stack{
	private int maxSize;
	private int[] stackArray;
	private int top;

	public Stack(int size){ //Constructor
		maxSize = size;
		stackArray = new int[maxSize];
		top = -1;
	}

	public void push(int value){ //Adds an element to the top of the stack
		top++;
		stackArray[top] = value;
	}

	public int pop(){ //Removes the top element of the stack and returns the value you've removed
		return stackArray[top--];
	}

	public int peek(){ //Returns the element at the top of the stack
		return stackArray[top];
	}

	public boolean isEmpty(){ //Returns true if the stack is empty
		return(top == -1);
	}

	public boolean isFull(){ //Returns true if the stack is full
		return(top+1 == maxSize);
	}
	public void makeEmpty(){ //Doesn't delete elements in the array but if you call
		top = -1;			 //push method after calling makeEmpty it will overwrite previous values
	}
}


/* This is ArrayList Implementation of stack , Where size is not
 	a problem we can extend the stack as much as we want*/ 
class Stack2{
		
		
		ArrayList<Integer> stackList;
		
		Stack2(){ //constructor
			stackList=new ArrayList<>();
		}
		
		
		void push(int value){ //adds value to the end of list which is the top for stack
			stackList.add(value);
		}
		
		int pop(){ //pops last element of list which is indeed the top for Stack 
			
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
		
		boolean isEmpty(){ //checks for empty Stack
			if(stackList.isEmpty())
				return true;
			
			else return false;
			
		}
		
		int peek(){ //top element of stack
			return stackList.get(stackList.size()-1);
		}
	}

//Example
public class Stacks{
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
