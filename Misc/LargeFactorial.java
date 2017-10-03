import java.util.Scanner;
import java.util.Stack;
class LargeFactorial
{
	public static void main(String []args)
	{
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int x,carry=0;
		Stack a=new Stack();
		Stack b=new Stack();
		a.push(1);
		for(int i=2;i<=n;i++)
		{
			while(!a.isEmpty()){
			x=(int)a.pop();
			b.push((x*i+carry)%10);
			carry=(x*i+carry)/10;}
			while(carry!=0)
			{
				b.push(carry%10);
				carry/=10;
			}
			while(!b.isEmpty())
			a.push(b.pop());
		}
		while(!a.isEmpty())
		b.push(a.pop());
		while(!b.isEmpty())
		System.out.print((int)b.pop());
	}
}
