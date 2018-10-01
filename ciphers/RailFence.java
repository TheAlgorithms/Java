import java.util.*;
class RailFence {
	public static void main(String[] args) {
		int i,j;
		Scanner sc = new Scanner(System.in);
		int option = 1;
		do {
			System.out.println("Rail Fence Cipher...");
			System.out.println("1. Encryption");
			System.out.println("2. Decryption");
			System.out.println("3. Exit");
			System.out.println("Enter your choice(1/2/3): ");
			option = sc.nextInt();
			if(option == 1) {
				String input;
				System.out.print("Enter the input string: ");
				sc.nextLine();
				input = sc.nextLine();
				int len = input.length();
				System.out.println("Enter the number of rows: ");
				int numRows = sc.nextInt();
				len = (len%numRows == 0) ? (len/numRows) : ((len/numRows)+1);
				char mat[][] = new char[numRows][len];
				for(i = 0 ; i < numRows ; i++) {
					for (j = 0 ; j < len ; j++ ) {
						mat[i][j] = '*';
					}
				} 
				int index = 0;
				for(j = 0 ; j < len ; j++) {
					for (i = 0 ; i < numRows && index < input.length() ; i++ ) {
						mat[i][j] = input.charAt(index);
						index++; 
					}
				} 
				String cipher_text = "";
				for(i = 0 ; i < numRows ; i++) {
					for (j = 0 ; j < len ; j++ ) {
						cipher_text = cipher_text + String.valueOf(mat[i][j]);
					}
				} 
				System.out.println("The cipher text is: "+cipher_text);
			}
			else if(option == 2) {
				String cipher_text;
				System.out.print("Enter the received cipher text: ");
				sc.nextLine();
				cipher_text = sc.nextLine();
				int len = cipher_text.length();
				System.out.println("Enter the number of rows: ");
				int numRows = sc.nextInt();
				len = len/numRows;
				char mat[][] = new char[numRows][len];
				int index = 0;
				for(i = 0 ; i < numRows ; i++) {
					for (j = 0 ; j < len ; j++ ) {
						mat[i][j] = cipher_text.charAt(index);
						index++;
					}
				} 
				String plain_text = "";
				index = 0;
				for(j = 0 ; j < len ; j++) {
					for (i = 0 ; i < numRows ; i++ ) {
						plain_text = plain_text + String.valueOf(mat[i][j]);
						//mat[i][j] = input.charAt(index);
						index++; 
					}
				}
				String newString = "";
				for(i = 0 ; i < plain_text.length() ; i++) {
					if(plain_text.charAt(i) != '*')
						newString = newString+String.valueOf(plain_text.charAt(i));
				}
				System.out.println("The decrypted text is: "+newString);		
			}
			else if(option == 3) {
				break;
			}
		} while(option != 3);
	}
}
/*
OUTPUT OF THE PROGRAM:

C:\Users\app\Desktop>java RailFence
Rail Fence Cipher...
1. Encryption
2. Decryption
3. Exit
Enter your choice(1/2/3):
1
Enter the input string: meetmeatthepark
Enter the number of rows:
3
The cipher text is: mtahaemtereetpk

Enter your choice(1/2/3):
2
Enter the received cipher text: mtahaemtereetpk
Enter the number of rows:
3
The decrypted text is: meetmeatthepark

Enter your choice(1/2/3):
3
*/