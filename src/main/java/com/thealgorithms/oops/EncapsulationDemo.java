package com.thealgorithms.oopconcepts;

class Account {
    private String holder;
    private double balance;

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }
}

public class EncapsulationTest {
    public static void main(String[] args) {
        Account acc = new Account();
        acc.setHolder("Suryanshu");
        acc.deposit(50000);
        acc.withdraw(12000);
        System.out.println(acc.getHolder() + " has balance ₹" + acc.getBalance());
    }
}
