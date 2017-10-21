package Others;

import java.util.Scanner;
/**
 * To check if a given number is armstrong or not.
 * @author mani manasa mylavarapu
 *
 */
public class Armstrong {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("please enter the number");
		int n = scan.nextInt();
		boolean isArmstrong = checkIfANumberIsAmstrongOrNot(n);
		if(isArmstrong)
		{
			System.out.println("the number is armstrong");
		}
		else
		{
			System.out.println("the number is not armstrong");
		}
	}

	public static boolean checkIfANumberIsAmstrongOrNot(int number) {
		int remainder, sum = 0,temp=0;
		temp=number;
		while (number > 0) {
			remainder = number % 10;
			sum = sum + (remainder * remainder * remainder);
			number = number / 10;
		}
		if (sum == temp) {
			return true;
		} else {
			return false;
		}

	}
}