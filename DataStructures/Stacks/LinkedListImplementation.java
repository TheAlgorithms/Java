package stack;
import linkedList.Node;
public class LinkedListImplementation {
	Node head;
	Node top;
	Node prev;
	int counter =0;
	
	
	public void push(int data)
	{
		
		Node  n = new Node();
		n.data=data;
		
		if(isEmpty())
		{
			head=n;
			
		}
		else
		{
			top.next=n;
			prev=top;
			
			
		}
		
		top=n;
		counter++;
	}
	public void pop()
	{
		if(isEmpty())
		{
			System.out.println("stack is emtpy");
		}
		else
		{
			if(counter==1)
			{
				top=null;
				head=null;
				System.out.println("Done!!!");
			}
			else
			{
				Node i = head;
				while(i.next!=null)
				{
					prev =i ;
					i=i.next;
				}
				prev.next=null;
				top =prev;
				
				counter++;
				
			}
		}
	}
	public boolean isEmpty()
	{
		
		if(head==null)
		{
			return true;
		}
		else
		{
		return false;
		}
	}
	public void show()
	{
		
		if(isEmpty())
		{
			System.out.println("Nothing in stack to show ");
			return;
		}
		else
		{
			Node i =head;
			
		int arr[]= new int[counter];
		
		for(int x=counter-1; x>=0;x--)
		{
			arr[x]=i.data;   ////
			i=i.next;
		}
		
		for(int c =0;c<arr.length;c++)
		{
			System.out.println(arr[c]);
		}
		}
	}
	
	
public static void main(String[] args) {
		
	LinkedListImplementation l = new LinkedListImplementation();
	l.push(10);
	l.push(20);
	l.push(30);
	l.push(40);

	l.pop();
	
	l.show();
	l.pop();
	System.out.println("....");
	l.show();
	
	System.out.println("....");
	System.out.println(l.prev.data);
	
	
	
	

	
	}

}
