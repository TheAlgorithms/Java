package Maths;

public class Main 
{
	/**
	 * 
	 * @param b the base
	 * @param p the exponent
	 * @return b "power" p 
	 */
	public static long binPower(long b, long p)
	{
		if(b == 0)
			return 0;
		
		long ans = 1;
		
		while(p != 0)
		{
			if((p & 1) == 1)
			{
				ans *= b;
			}
			
			b = b * b;
			p >>= 1;
		}
		
		return ans;
	}
	
	public static void main(String args[])
	{
		assert binPower(2, 0) == Math.pow(2, 0); // == 1
        assert binPower(0, 2) == Math.pow(0, 2); // == 0
        assert binPower(2, 10) == Math.pow(2, 10); // == 1024
        assert binPower(10, 2) == Math.pow(10, 2); // == 100
	}
}
