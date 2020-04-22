package com.sample;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Bank theBank = new Bank("Bank Of Kathmandu");

        // add a user to bank
        User aUser = theBank.addUser("Sijan", "Shrestha", "1234");

        // add a checking account for user
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while (true) {

            curUser = ATM.mainMenuPrompt(theBank, scanner);
            ATM.printUserMenu(curUser, scanner);
        }
    }

    private static void printUserMenu(User curUser, Scanner scanner) {

        // print a summary of user's accounts
        curUser.printAccountsSummary();

        int choice;

        do {
            System.out.printf("Welcome %s, what would you like to do?", curUser.getFirstName());
            System.out.println(" 1) Show account transaction history");
            System.out.println(" 2) Withdraw");
            System.out.println(" 3) Deposit");
            System.out.println(" 4) Transfer");
            System.out.println(" 5) Quit");
            System.out.println("Enter choice: ");
            choice = scanner.nextInt();

            if (choice < 1 || choice > 5)
                System.out.println("Invalid choice. Please choose 1-5");

        } while (choice < 1 || choice > 5);

        switch (choice) {
            case 1:
                ATM.showTransHistory(curUser, scanner);
                break;

            case 2:
                ATM.withdrawFunds(curUser, scanner);
                break;
        }

        // redisplay menu
        if (choice != 5)
            ATM.printUserMenu(curUser, scanner);
    }

    private static void showTransHistory(User curUser, Scanner scanner) {
        int theAcct;

    
    }

    private static User mainMenuPrompt(Bank theBank, Scanner scanner) {
        String userID;
        String pin;
        User authUser;

        do {
            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.println("Enter userId: ");
            userID = scanner.nextLine();
            System.out.print("Enter pin: ");
            pin = scanner.nextLine();

            authUser = theBank.userLogin(userID, pin);
            if (authUser == null) {
                System.out.println("Incorrect user ID/ pin combination. " + "Please try again.");
            }
        } while (authUser == null);

        return authUser;
    }
}
