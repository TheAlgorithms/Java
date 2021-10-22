import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Account extends Bank
{
    File file = new File("Bank.txt");
    
    public Account(String accountName, double balance)
    {
        this.accountName = accountName;
        this.balance = balance;
    }
    
    public void Deposit(double depositAmount)
    {
        
        if (depositAmount > 0.0)
        {
            balance += depositAmount;
            Log("Deposited $"+ depositAmount + " in "+ accountName + "'s account. New balance: $" + getBalance());
        }
    }
    
    public void Withdraw(double withdrawal)
    {
        if (withdrawal > balance)
        {
            System.out.println("Withdrawal amount exceeded account balance!");
        }
        else
        {
            balance -= withdrawal;
            Log("Withdrew $" + withdrawal + " from " + accountName + "'s account. New balance: $" + balance);
        }
    }
    
    public double getBalance()
    {
        return balance;
    }
    
    public void Log(String logData)
    {
        try
        {
            Scanner input = new Scanner(file);
            String data = "";
            
            while (input.hasNextLine())
            {
                data += input.nextLine() + "\n";
            }
            
            PrintWriter log = new PrintWriter(file);
            log.print(data + logData);
            log.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error accessing file!");
        }
    }
}