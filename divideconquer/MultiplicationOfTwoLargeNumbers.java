import java.util.*;

public class Q1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("a : ");
		String a = scan.nextLine();
		System.out.print("b : ");
		String b = scan.nextLine();
	    System.out.println("Result : "+mul(a,b));
        scan.close();
	    System.out.println("Result : "+mul(a,b));
	}
    static String mul(String A, String B)
    {
    	int n = 0;
    	int n1 = A.length();
    	int n2 = B.length();
    	if(n1<n2)
		{
			for(int i=0;i<n2-n1;i++)
			{
				A = '0'+ A;
			}
			n = n2;
		}
    	else
    	{
			for(int i=0;i<n1-n2;i++)
			{
				B = '0'+ B;
			}
			n = n1;
		}
    	if(n==1)
    		return Integer.toString((A.charAt(0)-'0')*(B.charAt(0)-'0'));
    	else
    		{
    		String al="",ar="",bl="",br="";
    		String x1;
			String x2;
			String x3;
			String x4;
			int l = n/2, r = n-n/2;
    	    /*for(int i=0;i<l;i++)
    	    {
    	    	al = al+A.charAt(i);
    	    	bl = bl+B.charAt(i);
    	    }
    	    for(int i=l;i<n;i++)
    	    {
    	    	ar = ar+A.charAt(i);
    	    	b
    	    }*/
    	    al = A.substring(0, l);
    	    ar = A.substring(l, n);
    	    bl = B.substring(0, l);
    	    br = B.substring(l, n);
    	    
    	    x1 = mul(al,bl);
    	    x2 = mul(al,br);
    	    x3 = mul(ar,bl);
    	    x4 = mul(ar,br);
    	    
    	    x2 = add(x2,x3);
    	    String s1 = "";
    	    for(int i=0;i<2*r;i++)
    	    	x1 += '0';
    	    for(int i=0;i<r;i++)
    	    	x2 += '0';
    	    s1 = add(x1,x4);
    	    s1 = add(s1,x2);
    	    return s1;
           }
    }
    static String add(String x, String y)
    {
    	int n = 0;
    	int n1 = x.length();
    	int n2 = y.length();
    	if(n1<n2)
		{
			for(int i=0;i<n2-n1;i++)
			{
				x = '0'+ x;
			}
			n = n2;
		}
    	else
    	{
			for(int i=0;i<n1-n2;i++)
			{
				y = '0'+ y;
			}
			n = n1;
		}
    	String s = "";
    	int sum = 0,c = 0;
    		for(int i=n-1;i>=0;i--)
    		{
    			sum = (x.charAt(i)-'0')+(y.charAt(i)-'0')+c;
    			c = sum/10;
    			sum = sum%10;
    			s += (char)(sum+'0');
    		}
    		if(c>0)
    			s += (char)(c+'0');
    		
    		String A="";
    		for(int i=s.length()-1;i>=0;i--)
    		{
    			A = A + s.charAt(i);
    		}
        return A;
    }
}