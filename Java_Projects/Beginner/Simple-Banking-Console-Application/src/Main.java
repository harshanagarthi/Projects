// package com.mybank;

import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static Account loggedInAccount = null;

    public static void main(String[] args) {
        createSampleAccounts();
        System.out.println("Welcome to MyBank Console Application!");

        while (true) {
            if (loggedInAccount == null) {
                System.out.println("\n---Authentication---");
                System.out.println("1. Login");
                System.out.println("2. Create Account");
                System.out.println("3. Exit");
                System.out.println("Enter your choice: ");
                int authChoice = sc.nextInt();
                sc.nextLine();

                switch (authChoice) {
                    case 1:
                        authenticateUser();
                        break;
                    case 2:
                        createAccount();
                    case 3:
                        System.out.println("Thank you for using MyBank. Goodbye!");
                        sc.close();
                        return;
                    
                    default:
                        System.out.println("Invalid choice. PLease try again.");
                }
            } else {
                displayBankingMenu();
                System.out.println("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        performDeposit();
                        break;
                    case 2:
                        performWithdrawal();
                        break;
                    case 3:
                        checkBalance();
                        break;
                    case 4:
                        performTransfer();
                        break;
                    case 5:
                        loggedInAccount = null;
                        System.out.println("Logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try agian.");
                }
            }
        }
    }

    private static void createSampleAccounts() {
        accounts.add(new Account("9515251297", "Harsha", 40000.0, "1409"));
        accounts.add(new Account("9966934486", "Kalpana", 50000.0, "9966"));
        accounts.add(new Account("8639715090", "Sathvika", 60000.0, "5090"));
    }

    private static void authenticateUser() {
        System.out.println("Enter Account Number: ");
        String accNum = sc.nextLine();
        System.out.println("Enter PIN: ");
        String pin = sc.nextLine();
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accNum) && acc.validatePin(pin)) {
                loggedInAccount = acc;
                System.out.println("Login successful. Welcome, " + acc.getAccountHolderName() + "!");
                return;
            }
        }
        System.out.println("Authentication failed. Invalid account number or PIN.");
        loggedInAccount = null;
    }

    private static void createAccount() {
        System.out.println("Enter Account Number: ");
        String accNum = sc.nextLine();
        System.out.println("Enter Account Holder Name: ");
        String accHolderName = sc.nextLine();
        System.out.println("Enter Initial Balance: ");
        double initialBalance = sc.nextDouble();
        sc.nextLine();
        System.out.println("Set a PIN for your account: ");
        String pin = sc.nextLine();

        Account newAccount = new Account(accNum, accHolderName, initialBalance, pin);
        accounts.add(newAccount);
        System.out.println("Account created successfully for " + accHolderName + " with account number " + accNum + ".");
    }

    private static void displayBankingMenu() {
        System.out.println("\n---Banking Operations ---");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. Transfer Funds");
        System.out.println("5. Logout");
    }

    private static void performDeposit() {
        System.out.println("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        loggedInAccount.deposit(amount);
    }

    private static void performWithdrawal() {
        System.out.println("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        loggedInAccount.withdraw(amount);
    }

    private static void checkBalance() {
        System.out.println("Current balance for " + loggedInAccount.getAccountHolderName() + " ("
                + loggedInAccount.getAccountNumber() + ") : " + loggedInAccount.getBalance());
    }
    
    private static void performTransfer() {
        System.out.println("Enter recipient's account number: ");
        String recipientAccNum = sc.nextLine();
        Account recipientAccount = null;
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(recipientAccNum)) {
                recipientAccount = acc;
                break;
            }
        }
        if (recipientAccount == null) {
            System.out.println("Recipient account not found.");
            return;
        }
        if (recipientAccNum.equals(loggedInAccount.getAccountNumber())) {
            System.out.println("Cannot transfer to the same account.");
            return;
        }

        System.out.println("Enter amount to transfer: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive.");
            return;
        }
        if (loggedInAccount.withdraw(amount)) {
            recipientAccount.deposit(amount);
            System.out.println("Transfer successful! Rupees " + amount + "/ has been transferred to "
                    + recipientAccount.getAccountHolderName() + " (" + recipientAccount.getAccountNumber() + ").");
        }
    }
}