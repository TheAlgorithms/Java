
public class KaratSuba_Algorithm {
	static long multiple(long a,long b) {
		int n=(int) (Math.floor(Math.log10(a))+1);
		int m=(int) (Math.floor(Math.log10(b))+1);
		n=Math.max(n, m);
		m=n/2;
		if(n==1||m==1) {
			return a*b;
		}
		long x1,x0,y1,y0;
		x1= a/(long)(Math.pow(10, m/2));
		y1= (b/(long)(Math.pow(10, m/2)));
		x0= (a%(long)Math.pow(10, m/2));
		y0= (b%(long)Math.pow(10, m/2));
		long p=multiple(x1+x0, y1+y0);
		long x1y1=multiple(x1, y1);
		long x0y0=multiple(x0, y0);
		
		return (long) (x1y1*Math.pow(10, m)+((p-x1y1-x0y0)*Math.pow(10, m/2))+x0y0);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println(multiple(134,5678));
System.out.println(134*5678);
	}

}
