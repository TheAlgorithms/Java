public abstract class Bank
{
    String accountName;
    double balance; 
    
    public abstract void Deposit(double depositAmount);
    public abstract void Withdraw(double withdrawal);
    public abstract double getBalance();
}