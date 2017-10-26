import java.io.*;
import java.util.Scanner;

public class Pow{

	public static void main( String[] args ){
		Scanner s = new Scanner( System.in );
		System.out.println( "Enter the integer to calculate power" );
		int x = s.nextInt();
		System.out.println( "Enter the integer of the power" );
		int n = s.nextInt();

		if( x == 1 || n == 1 ){
			System.out.format( "The result is %d%n", x);
		}
		else if( n == 0 ){
			System.out.format( "The result is 1%n" );
		}
		else{
			int result = 1;
			for( int i = 0; i < n; i++ ){
				result *= x;
			}
			System.out.format( "The result is %d%n", result );
		}
	}
}
