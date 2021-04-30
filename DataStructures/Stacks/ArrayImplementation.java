package stack;

public class ArrayImplementation {
  
	static int stack[] = new int[5];
	static int max=stack.length;
	static int top=-1;
	static int tmptop=0;
	static int tmpstack[];
	int temp ;
	public void push(int data )
		{

		  if(isFull())
		  {
			  System.out.println("stack is full");
		  }
		  else
		  {
			  top++;
			  stack[top]=data;
		  }
		}
	public int  pop()
	{
		if(isEmpty())
		{
			
			return  -1;
		}
		else
		{
			int  i =stack[top];
			stack[top]=0;
			top--;
			return i ;
		}
		
	}
	public void show(int stack[])
	{
		
		for(int i = max-1;i>-1;i--)
		{
			if(stack[i]==0)
			{
				System.out.println(" - ");
			}else
			{
			System.out.println(" " +stack[i]+ " ");
			}
		}
		
	}
	public void sort(int stack[])
	{
		tmpstack=new int[stack.length];
		
		while(!isEmpty())
		{
			temp =  pop();
			while(tmptop!=-1 && tmpstack[tmptop]>temp)
			{
				push(tmpstack[tmptop]);
				tmptop--;
				
			}
			tmpstack[tmptop]=temp;
			tmptop++;
			
		}
		top=max-1;
		stack=tmpstack;
	
		while(!isEmpty())
		{
			System.out.println(stack[top]);
			top--;
		}
		
	}
	public boolean isFull()
	{
		if(top>=max-1)
		{
          return true;			
		}
		return false;
	}
	public boolean isEmpty()
	{
		if(top==-1)
		{
		
			return true;
		}
		return false;
		
	}
	
	/*...................................................*/
	public static void main(String[] args)
		{
		
		ArrayImplementation a= new ArrayImplementation();
	
		a.push(10);
		a.push(20);
		a.push(30);
		a.push(40);
		a.push(50);
		a.push(100);
		a.show(stack);
		
		//System.out.println("after sorting In ascending order  ");
		//a.sort(stack);
		}
	
	
}
