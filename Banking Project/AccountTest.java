import java.util.Scanner;

public class AccountTest
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String accName;
        Double accBalance, amount;
        int operation;
        
        System.out.println("Enter account holder's name:");
        accName = input.nextLine();
        System.out.println("Enter account balance:");
        accBalance = input.nextDouble();
        
        Account myAccount = new Account(accName, accBalance);        
        
        System.out.println("Choose Operation:\n1. Deposit\n2. Withdraw\n3. Check Balance");
        operation = input.nextInt();
        
        switch (operation)
        {
            case 1:
            System.out.println("Enter deposit amount:");
            amount = input.nextDouble();
            myAccount.Deposit(amount);
            break;
            case 2:
            System.out.println("Enter withdrawal amount:");
            amount = input.nextDouble();
            myAccount.Withdraw(amount);
            break;      
            case 3:
            System.out.println("Account Balance: $" + myAccount.getBalance());
            break;
            default:
            System.out.println("Invalid option!");
            break;
        }
    }
}