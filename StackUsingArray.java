
public class StackUsingArray {
	
    int[] data;
	private int tos;
	public static int DEFAULT_CAPACITY = 10;
	
	public StackUsingArray()throws Exception {
		this(DEFAULT_CAPACITY);
	}
	
	public StackUsingArray(int capacity)throws Exception {
		if(capacity<1) {
			throw new Exception("INvalid Capacity");
		}
		this.data =new int[capacity];
		this.tos= -1;
	}
	
	public int size() {
		return this.tos+ 1;
	}
	
	public boolean isempty() {
		if(this.size()==0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void push (int value)throws Exception {
		if(this.size()==this.data.length) {
			throw new Exception("Stack is Full");
		}
		this.tos++;
		this.data[this.tos]=value;
	}
	
	public int pop() throws Exception{
		if(this.size()==0) {
			throw new Exception("Stack is empty");
		}
		int value= this.data[this.tos];
		this.data[this.tos]=0;
		this.tos--;
		return value;
	}
	
	public void display() {
		for(int i=this.tos;i>=0;i--) {
			System.out.print(this.data[i]+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		StackUsingArray stack =new StackUsingArray();
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		stack.push(50);
		stack.display();
		for(int i=0;i<4;i++) {
			System.out.println("*********************************************");
			stack.pop();
			stack.display();
			System.out.println(stack.size());
		}	
		System.out.println("Is stack empty? :" + stack.isempty());
	}
}
