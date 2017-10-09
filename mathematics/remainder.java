/**
* JAVA program to find remainder of a/b
* NOTE -- NUMBER OF DIGITS IN a <= 100000
*/
import java.util.Scanner;
public class remainder
{
	public static int function(String a, int b)
	{
		int res, i, j;
		j = a.length();
		for (res = 0, i = 0; i < j; ++i) {
			res = (res * 10 + (a.charAt(i) - 48)) % b;
		}
		return res;
	}
	public static void main(String[] args)
	{
		String a;
		int b, res;
		Scanner Sc = new Scanner(System.in);
		a = Sc.next();
		b = Sc.nextInt();
		res = function (a, b);
	}
}
