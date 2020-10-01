
class FermatLittle 
{ 
	static int __gcd(int a, int b) 
	{ 
	
		if(b == 0) 
		{ 
			return a; 
		} 
		else
		{ 
			return __gcd(b, a % b); 
		} 
	} 
	
	
	static int power(int x,int y,int m) 
	{ 
		if (y == 0) 
			return 1; 
		int p = power(x, y / 2, m) % m; 
		p = (p * p) % m; 
	
		return (y % 2 == 0) ? p : (x * p) % m; 
	} 
	
	
	static void modInverse(int a, int m) 
	{ 
		if (__gcd(a, m) != 1) 
			System.out.print("Inverse doesn't exist"); 
	
		else { 
	
			
			System.out.print("Modular multiplicative inverse is "+power(a, m - 2, m)); 
		} 
	} 
	
	
	
	public static void main (String[] args) 
	{ 
		int a = 3, m = 11; 
		modInverse(a, m); 
	} 
} 



