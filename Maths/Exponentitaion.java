package Maths;

public class Exponentitaion {
	public static void main(String args[]) {
		int x = 2;
		int n = 10;
		System.out.println(x+"^"+n+" = "+exponentitation(2, 10));
	}
	
	public static long exponentitation(int x, int n) {
		if(n == 0)
			return 1;
		else if(n == 1)
			return x;
		else
			return exponentitation(x, n/2) * exponentitation(x, n/2 + n%2);
	}
}
