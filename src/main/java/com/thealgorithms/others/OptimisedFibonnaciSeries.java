/*
 * Usually the time complexity of fibonacchi series in recursion is T(2^N), i.e., exponential
 * but in the below code the time complexity of this is Linear 
 * here we used basic maths and golden ratio formula on Fibonacci Series
 */
public class OptimisedFibonacciSeries {
	static long fiboFormula(int n) {
	return (int) ((Math.pow(((1+ Math.sqrt(5))/2), n) - Math.pow(((1 - Math.sqrt(5)) / 2), n)) / Math.sqrt(5));
	
	}
	public static void main(String[] args) {
		for (int i = 0; i <= 10; i++) {
			System.out.print(fiboFormula(i)+" ");
		}
	}
}ass