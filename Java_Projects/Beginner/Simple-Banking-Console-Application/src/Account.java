// package com.mybank;

public class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String pin;

    public Account(String accountNumber, String accountHolderName, double initialBalance, String pin) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.pin = pin;
    }

    public boolean validatePin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposit of rupees " + amount + "/ is successful. New balance : " + this.balance);
        } else {
            System.out.println("Deposit amount must be positive");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive");
            return false;
        }
        if (this.balance >= amount) {
            this.balance -= amount;
            System.out.println("Withdrawal of rupees " + amount + "/ is successful. New balance :" + this.balance);
            return true;
        } else {
            System.out.println("Insufficeint funds. Current balance : " + this.balance);
            return false;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return this.balance;
    }


}