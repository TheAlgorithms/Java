import java.util.Scanner;  // Import the Scanner class

class MyClass {
  public static void main(String[] args) {
    Scanner reader = new Scanner(new FileInputStream("infile.dat");

    System.out.println("Enter Vector 1");
    int v1a = myObj.nextInt();
    int v1b = myObj.nextInt();
    int v1c = myObj.nextInt();

    System.out.println("Enter Vector 2");
    int v2a = myObj.nextInt();
    int v2b = myObj.nextInt();
    int v2c = myObj.nextInt();

    int d = v1a * v2a + v1b * v2b + v1c * v2c;

    System.out.println("Your Result is " + d);
  }
}