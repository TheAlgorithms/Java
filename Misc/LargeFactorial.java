import java.util.Scanner;
import java.util.Stack;
class LargeFactorial
{
	public static void main(String []args)
	{	
		System.out.print("Enter the number whose factorial you want to calculate: ");
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int x,carry=0;
		Stack a=new Stack();
		Stack b=new Stack();
		a.push(1);//Initialize stack with value '1'
		for(int i=2;i<=n;i++)//Running loop only for n>1 else give output '1'
		{
			while(!a.isEmpty())//Loop will run until stack a is not empty
			{
			x=(int)a.pop();
			b.push((x*i+carry)%10);
		//Getting digit from stack a and multipling with i and pushing its unit digit to stack b
		// And storing its carry that can be added to next position in stack
			carry=(x*i+carry)/10;}
		//Now if any carry is left to be added to sstack will be now added this step is the main step increasing the nnumber of digit in the number
			while(carry!=0)
			{
				b.push(carry%10);
				carry/=10;
			}
			while(!b.isEmpty())
			a.push(b.pop());
		}

	//Stack a containg Least significant digit on the top and Most significant Digit on the bottom so reversing it in stack b to print the result
		while(!a.isEmpty())
		b.push(a.pop());
		while(!b.isEmpty())
		//Printing the content of stack b
		System.out.print((int)b.pop());
	}
}
