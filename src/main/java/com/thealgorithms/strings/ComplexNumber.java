import java.util.Scanner;

public class ComplexMultiply {
     /**
     * Multiplies two complex numbers given as strings in "a+bi" format.
     * Parses the strings, performs multiplication, and returns the product.
     * 
     * num1 First complex number string
     * num2 Second complex number string
     * return the product as a string in "x+yi" format
     */
    public static String multiply(String num1, String num2) {
        int n1 = num1.indexOf('+');
        int a = Integer.parseInt(num1.substring(0, n1));
        int b = Integer.parseInt(num1.substring(n1 + 1, num1.length() - 1)); // exclude 'i'

        int n2 = num2.indexOf('+');
        int c = Integer.parseInt(num2.substring(0, n2));
        int d = Integer.parseInt(num2.substring(n2 + 1, num2.length() - 1)); // exclude 'i'

        int real = a * c - b * d; // real part of product
        int imag = a * d + b * c; // imaginary part of product

        return real + "+" + imag + "i";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter first complex number
        System.out.print("num1: ");
        String num1 = scanner.nextLine();

        // Prompt user to enter second complex number
        System.out.print("num2: ");
        String num2 = scanner.nextLine();

        // Perform multiplication and display result
        String result = multiply(num1, num2);
        System.out.println("Output: " + result);

        scanner.close();
    }
}
