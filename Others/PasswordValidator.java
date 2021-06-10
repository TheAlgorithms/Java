import java.util.Scanner;

/**
 * 
 * Validates that inputed password meets the following criteria:
 * 		- Is greater than 5 characters but less than 20 characters in length
 * 		- Contains at least one number
 * 		- Contains at least one special character
 * 		- Contains no spaces
 *
 */

public class PasswordValidator {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input password: ");
		String password = scanner.nextLine();
		System.out.println();
		
		Squarelotron.validatePassword(password);	
		System.out.println("\nPassword is: " + password);
		scanner.close();
	}
	
	static void validatePassword(String input){
		boolean containsNum = false, containsSpecialChar = false, containsSpace = false;
	    String numbers = "0123456789";
	    String specialChars = "!@#$%^&*(){}?";
	    
		if(input.length() <= 5 || input.length() >= 20) {
			System.out.println("Password should be more than 5 but less than 20 characters in length.");
		}
		
		for(int index = 0; index < input.length(); index++) {
			for(int i = 0; i < numbers.length(); i++) {
				if(input.charAt(index) == numbers.charAt(i)) {
					containsNum = true;
					i = numbers.length();
				}
			}
			for(int i = 0; i < specialChars.length(); i++) {
				if(input.charAt(index) == specialChars.charAt(i)) {
					containsSpecialChar = true;
					i = specialChars.length();
				}
			}
			if(input.charAt(index) == ' ') {
				containsSpace = true;
			}
		}
		
		if(!containsNum)
			System.out.println("Should contain at least one number.");
		
		if(!containsSpecialChar)
			System.out.println("Should contain at least one special character.");
		
		if(containsSpace)
			System.out.println("Password should not contain any spaces.");
		
		if(containsNum & containsSpecialChar & !containsSpace)
			System.out.println("Valid password.");
		else { 
			System.out.println("Invalid password.");
		}
	}
	
}
