import java.util.Scanner;

/**
 * Password Validator
 * @author esparev
 */
public class PasswordValidator {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		String password;

		System.out.print("Input password: ");
		password = sc.nextLine();
		
		System.out.println("");
		validator(password);

		System.out.println("\nPassword is: " + password);
	}

	/**
	 * Validates if the entered password has at least one special character, one number, 
	 * doesn't have spaces and has more than 5 but less than 20 characters in length
	 * @param password
	 */
	public static void validator(String password) {
		boolean value = true;

		String space = "(.*[ ].*)";
		String numbers = "(.*[0-9].*)";
		String specialChar = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";

		// Validates if the password has more than 5 characters but less than 20 in length
		if (password.length() < 5 || password.length() > 20) {
			System.out.println("Password should be more than 5 but less than 20 characters in length.");
			value = false;
		}

		// Validates if the password has at least one number
		if (!password.matches(numbers)) {
			System.out.println("Password should contain at least one number.");
			value = false;
		}

		// Validates if the password has at least one special character
		if (!password.matches(specialChar)) {
			System.out.println("Password should contain at least one special character.");
			value = false;
		}

		// Validates if the password has a space
		if (password.matches(space)) {
			System.out.println("Password should not contain any spaces.");
			value = false;
		}

		if (value) {
			System.out.println("Valid password.");
		}
		else {
			System.out.println("Invalid password.");
		}
	}
}