import java.util.Scanner;
public class PowerOf2{
	static boolean powerOf2(int n){
		if(n==0){                      // we are excluding zero here since it is exception .
			return false;                                                                            
		}                                             
		 return ((n)&(n-1))==0;               // we are taking And operation of number enter by the user as an input( say n ) with it's previous digit (n-1)
	}
	public static void main(String[] args) {     
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();        //8 ,10,16
		System.out.println(powerOf2(n));
	}
}
