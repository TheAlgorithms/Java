
import java.util.*;

class Solution
{
	public static Stack<Integer> sortstack(Stack<Integer> input)
	{
		Stack<Integer> tmpStack = new Stack<Integer>();
		while(!input.isEmpty())
		{
			int tmp = input.pop();
			while(!tmpStack.isEmpty() && tmpStack.peek() > tmp)
			{
			input.push(tmpStack.pop());
			}
			tmpStack.push(tmp);
		}
		return tmpStack;
	}
	
	public static void main(String args[])
	{
		Stack<Integer> st = new Stack<Integer>();
		st.add(34);
		st.add(3);
		st.add(31);
		st.add(98);
	
		Stack<Integer> tmpStack = Solution(st);
	
		while (!tmpStack.empty())
		{
			System.out.print(tmpStack.pop()+" ");
		}
	}
}
