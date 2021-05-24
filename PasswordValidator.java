import java.util.*;

class PasswordValidator
{
  public static void main(String args[])
  {
    PasswordValidator passwordValidator=new PasswordValidator();
    Scanner sc=new Scanner(System.in);
    System.out.print("Input Password: ");
    String Password=sc.nextLine();
    System.out.println();
    boolean valid=passwordValidator.isValidPassword(Password);
    if(valid==false)
    {
      System.out.println("Invalid Password.\n");
    }
    else
    {
      System.out.println("Valid Password.\n");
    }
    System.out.println("Password is: "+Password);
  }
  public static boolean isValidPassword(String password)
  {
     boolean isValid = true;
     if (password.length() >= 20 || password.length() <= 5)
     {
       System.out.println("Password should be more than 5 but less than 20 characters in length.");
       isValid = false;
     }
     String numbers = "(.*[0-9].*)";
     if (!password.matches(numbers))
     {
       System.out.println("Password should contain at least one number.");
       isValid = false;
     }
     String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
     if (!password.matches(specialChars))
     {
       System.out.println("Password should contain at least one special character.");
       isValid = false;
     }
     for (int i=0; i<password.length();i++)
     {
       char c=password.charAt(i);
       if(c==' ')
       {
    	   System.out.println("Password should not contain any spaces.");
           isValid = false;
           break;
       }
     }
     return isValid;
  }
}
