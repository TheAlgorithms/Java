//Java Program to Find the Roots of a Quadratic Equation
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class Quadratic {
    // Function to find and display the roots of the equation.
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double a,b,c;
        try{
            System.out.println("Enter the coefficients of the quadratic equation");
            a = Double.parseDouble(br.readLine());
            b = Double.parseDouble(br.readLine());
            c = Double.parseDouble(br.readLine());
        }catch (Exception e){
            System.out.println("An error occurred");
            return;
        }
        double determinant = Math.pow(b,2) - 4*a*c;
        if(determinant > 0){
            System.out.println("Roots are " + (-b+Math.sqrt(determinant))/(2*a)
                                  + " and " + (-b-Math.sqrt(determinant))/(2*a));
        }else if (determinant == 0){
            System.out.println("Roots are " + -b/(2*a));
        }
        else{
            System.out.println("Roots are " + -b/(2*a) + "+i" + 
                                Math.sqrt(-determinant)/(2*a) + " and "
                                + -b/(2*a) + "-i" + Math.sqrt(-determinant)/(2*a));
        }
    }
}
