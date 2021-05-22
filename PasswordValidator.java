import java.util.*;

class PasswordValidator
{
  public static void main(String args[])
  {
    PasswordValidator pv=new PasswordValidator();
    Scanner sc=new Scanner(System.in);
    System.out.print("Input Password: ");
    String P=sc.nextLine();
    boolean v=pv.isValidPassword(P);
    if(v==false)
    {
      System.out.println("\nPassword should be more than 5 but less than 20 characters in length.\nPassword should contain at least one number.\nPassword should contain at least one special character.\nPassword should not contain any spaces.\nInvalid Password.\n");
    }
    else
    {
      System.out.println("\nValid Password.\n");
    }
    System.out.println("Password is: "+P);
  }
  public static boolean isValidPassword(String password)
  {
     boolean isValid = true;
     if (password.length() > 20 || password.length() < 5)
     {
       isValid = false;
     }
     String numbers = "(.*[0-9].*)";
     if (!password.matches(numbers))
     {
       isValid = false;
     }
     String specialChars = "(.*[@,#,$,%].*$)";
     if (!password.matches(specialChars))
     {
       isValid = false;
     }
     if (password.matches(" "))
     {
       isValid = false;
     }
     return isValid;
  }
}
