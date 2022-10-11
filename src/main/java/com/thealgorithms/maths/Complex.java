import java.util.Scanner;

public class Complex {
	int real, img;

	public Complex() // this is a default constructor
	{

	}

	void accept_data() // Method to accpet the data
	{
		Scanner r = new Scanner(System.in);
		System.out.println("Enter the REAL part of Number :");
		real = r.nextInt();
		System.out.println("Enter the IMAGINARY part of Number :");
		img = r.nextInt();
	}

	public Complex(Complex a, Complex b, int n) // Parameterized Constructor to store the data
	{
		if (n == 1) {
			System.out.println("\nADDITION :"); // If addition is selected in main function
			real = a.real + b.real;
			img = a.img + b.img;
		} else if (n == 2) // If substraction is selected in main function
		{
			System.out.println("\nSUBSTRACTION :");
			real = a.real - b.real;
			img = a.img - b.img;

		} else {
			System.out.println("\nMULTIPLICATION :"); // If multiplication is selected in main function
			real = (a.real * b.real) - (a.img * b.img);
			img = (a.real * b.img) + (a.img * a.real);
		}
		System.out.println("First Number is : \t" + a.real + " + i" + a.img);
		System.out.println("Second Number is : \t" + b.real + " + i" + b.img + "\n");
		System.out.println("Result is : \t" + real + " + i" + img);
	}

	public static void main(String[] args) {
		int choice;
		char fl;
		Complex x = new Complex(); // creating object of the class Comples
		Complex y = new Complex(); // creating object of the class Comples
		x.accept_data(); // calling the function accept_data using objects
		y.accept_data(); // calling the function accept_data using objects
		do {
			// do-while loop starts
			System.out.println(" What do you want to do ?\n");
			System.out.println(" 1. ADDITION \n 2. SUBSTRACTION \n 3. MULTIPLICATION \n"); // menu for switch case
			System.out.println("Enter your option :");
			Scanner r = new Scanner(System.in);
			choice = r.nextInt();
			switch (choice) {
				case 1:
					Complex k = new Complex(x, y, 1);
					break;
				case 2:
					Complex m = new Complex(x, y, 2);
					break;
				case 3:
					Complex n = new Complex(x, y, 3);
					break;
				default:
					System.out.println("Invalid Input !!!");
			}
			System.out.println("/n Do you want to continue (y/n) ?:");
			fl = r.next().charAt(0);
		} while (fl == 'Y' || fl == 'y'); // ending do-while loop
	}
}
/*
 * Enter the REAL part of Number :
 * 34
 * Enter the IMAGINARY part of Number :
 * 56
 * Enter the REAL part of Number :
 * 2
 * Enter the IMAGINARY part of Number :
 * 6
 * What do you want to do ?
 * 
 * 1. ADDITION
 * 2. SUBSTRACTION
 * 3. MULTIPLICATION
 * 
 * Enter your option :
 * 1
 * 
 * ADDITION :
 * First Number is : 34 + i56
 * Second Number is : 2 + i6
 * 
 * Result is : 36 + i62
 * /n Do you want to continue (y/n) ?:
 * y
 * What do you want to do ?
 * 
 * 1. ADDITION
 * 2. SUBSTRACTION
 * 3. MULTIPLICATION
 * 
 * Enter your option :
 * 2
 * 
 * SUBSTRACTION :
 * First Number is : 34 + i56
 * Second Number is : 2 + i6
 * 
 * Result is : 32 + i50
 * /n Do you want to continue (y/n) ?:
 * y
 * What do you want to do ?
 * 
 * 1. ADDITION
 * 2. SUBSTRACTION
 * 3. MULTIPLICATION
 * 
 * Enter your option :
 * 3
 * 
 * MULTIPLICATION :
 * First Number is : 34 + i56
 * Second Number is : 2 + i6
 * 
 * Result is : -268 + i2108
 * /n Do you want to continue (y/n) ?:
 */
