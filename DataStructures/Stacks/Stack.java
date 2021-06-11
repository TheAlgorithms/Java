import java.util.*;
public class StackUnderFlows extends RuntimeException
{
    public StackUnderFlows(String S)
	{
		super(S);
	}
	
	public StackUnderFlows()
	{
		super();
	}

}

public class Node
{
	public int data;
	public Node next;
	
	public Node()
	{
		this.next = null;
	}
}


public class Stack
{
	Node head;
	int size;
	
	public Stack()
	{
		head = null;
		size = 0;
	}
	
	public void push(int x)
	{
		Node newNode = new Node();
		newNode.data = x;
		newNode.next = head;
		head = newNode;
		size++;
		System.out.println("Item pushed "+x);
	}
	
	public int pop() throws StackUnderFlows
	{   
		if(head==null)
			throw new StackUnderFlows("Stack Underflows");
		
		Node temp = head;
		head = head.next;
		size--;
		System.out.println("Item popped "+temp.data);
		return temp.data;
		
	}
	
	public int peek() throws StackUnderFlows
	{
		if(head==null)
			throw new StackUnderFlows("Stack Underflows");
		
		else
			return head.data;	
	}
	
	public int getSize()
	{
		return size;
	}
	
	public void Display() throws StackUnderFlows
	{   
	    if(head==null)
			throw new StackUnderFlows("Stack Underflows");
	
	
	    else
		{
		    Node disp = head;
		    System.out.print("\nElements present in the Stack ");
		    while(disp!=null)
		    {   
			    System.out.printf("%d ",disp.data);
			    disp = disp.next;
		    }
			
			System.out.println();
		}
	}
	
	public static void main(String[] args)
	{
	    Stack obj = new Stack();
		  obj.push(5);
	    obj.push(7);
		  obj.push(8);
		  obj.push(9);
		  obj.pop();
		  obj.push(6);
		  obj.pop();
		  obj.Display();
		  System.out.println("Top Element is "+obj.peek());
		  System.out.println("Size of Stack is "+obj.getSize());
	}
}
