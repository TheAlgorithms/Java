import java.util.Scanner;
public class LawnMowing{
  public static void main ( String [] args){

  Scanner input = new Scanner (System.in);
  System.out.print ("Lawn-mowing service");
  System.out.print (" \n Please Enter the Length and Width of your lawn: ");
  System.out.print ("\n Length: ");
    double length = input.nextDouble();
    System.out.print ("Width: ");
   double width = input.nextDouble();
   System.out.print ("How would you like to set up a payment? \n (1) once, (2) twice, (3) 20 times per year");
   int payment = input.nextInt();
     
   double lawn = length*width;
   double wcost = 0.0;
   if (lawn < 4000) {
   wcost = 25;}
   else if (lawn >= 4000 && lawn < 6000) {
   wcost = 35;}
   else {
   wcost = 50;}
   double costt = wcost*20;
   double costp = 0.0;

   
   if ( payment == 1) {
     costp = costt;}
   else if (payment == 2) { 
     costp = (costt/2);}
   else if (payment == 20) {
     costp = (costt/20);}
     
   
   System.out.printf (" \n Your lawn is %.2f ", lawn);
   System.out.print (" square feet.");
   System.out.printf ("\n your weekly fee: %.2f", wcost);
   System.out.printf ("\n 20-week seasonal fee: %.2f", costt);
  System.out.print ("\n your payment is a " + payment);
  System.out.printf(" time fee %.2f", costp);
   
   
  
  
  
  
  }}
