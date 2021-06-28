import java.util.Scanner;

public class ConvertTemp {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            
            double userTempConverted;
            System.out.println("Please enter the value of the temperature you'd like to convert: ");
            String tempValue = scan.nextLine();
            double userTempInput = Double.parseDouble(tempValue);
            boolean validInput = true;

            while(validInput) {
                System.out.println("Is the temperature in Celsius (C) or Fahrenheit (F)? Enter C for Celsius, F for Fahrenheit.");
                String celsiusOrFahr = scan.nextLine();
                if (celsiusOrFahr.equalsIgnoreCase("c")) {
                    userTempConverted = userTempInput * 1.8 + 32;
                    System.out.println(tempValue + "C is " + Math.round(userTempConverted) + "F");
                    validInput = false;
                }
                else if (celsiusOrFahr.equalsIgnoreCase("f")) {
                    userTempConverted = (userTempInput - 31) / 1.8;
                    System.out.println(tempValue + "F is " + Math.round(userTempConverted) + "C");
                    validInput = false;
                }
                else {
                    System.out.println("Please enter a valid value.");
                }
            }
            System.out.println("Thank you for using my temperature conversion program!");
        }
    }
}