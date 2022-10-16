import java.util.Scanner;

public class Contribute {

    public static void main(String[] args) {
        int a,b,result;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a:");
        a = sc.nextInt();

        System.out.println("Enter b");
        b= sc.nextInt();

        System.out.println("Addition");
        result = a+b;
        System.out.println(a + " + " + b + " = " + result);

        System.out.println("Subtraction");
        result = a-b;
        System.out.println(a + " - " + b + " = " + result);

        System.out.println("Multiplication");
        result = a*b;
        System.out.println(a + " * " + b + " = " + result);

        try{
            System.out.println("Division");
            result=a/b;
            System.out.println(a + " / "+ b + " = "+ result);
        }
        catch (ArithmeticException e){
            System.out.println("Invalid divisor !!!   Exception type----->" + e);
            System.out.println("Please enter a valid divisor");
        }
}

}
