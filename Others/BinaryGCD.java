

//Binary GCD algorithm also knows as Stein's algorithm
public class BinaryGCD {
	
	public static int gcdBinary(int a, int b) {
		int s;
		if(a == 0)
			return b;
		if(b == 0)
			return a;
		for(s=0;((a|b)&1) == 0;s++) {
			a = a >> 1;
			b = b >> 1;
		}
		while((a&1)==0)
			a = a >> 1;	
		do {
			while((b&1)==0)
				b = b >> 1;
			if(a > b) {
				int t = b;
				b = a;
				a = t;
			}
			b = b - a;
		} while(b!=0);		
		return a << s;
    }

}
