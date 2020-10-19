import java.util.Scanner;
public class PoundsToKilograms {
  public static double convert(double pounds) {
    double equation = pounds / 2.205;
    return equation;
  }
  public static int rounded(double pounds) {
    int equation = (int)(pounds / 2.205);
    return equation;
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("How many pounds do you want to convert: ");
    double pounds = sc.nextDouble();
    System.out.println("Here is the answer: " + convert(pounds));
    System.out.println("Here is the answer rounded to the nearest whole number: " + rounded(pounds));
  }
}