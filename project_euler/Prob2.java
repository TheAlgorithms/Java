public class Prob2
{
	public static final int MAX_NUM = 4000000;
	
	public static void main(String[] args)
	{
		int nMinus2 = 1;
		int nMinus1 = 2;
		int sum = nMinus1;
		int temp;
		
		while(true)
		{
			temp = nMinus1 + nMinus2;
			
			if(temp > MAX_NUM)
			{
				break;
			}
			else if(temp % 2 == 0)
			{
				sum += temp;
			}
			nMinus2 = nMinus1;
			nMinus1 = temp;
		}
		System.out.println("sum: " + sum);
	}
}