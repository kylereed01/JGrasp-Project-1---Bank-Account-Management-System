/*
Kyle Reed
Prof. Huang
COS-210-300
5 February 2025
*/

// Bank Account Management System

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Driver class allowing user to manage bank accounts
public class BankAccountManagementSystem {
    private static final String PASSWORD = "12345"; // password to enter
    private static final int MAX_ATTEMPTS = 3; // max attempts for password set to "3"
    private static List<CheckingAccount> checkingAccounts = new ArrayList<>(); // allows user to create new checking account
    private static List<SavingAccount> savingAccounts = new ArrayList<>(); // allows user to create new saving account
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int attempts = 0;

        // Password verification
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Please enter your password: "); // prompts user for password
            String inputPassword = scanner.nextLine();
            if (inputPassword.equals(PASSWORD)) {
                System.out.println("Welcome!"); 
                break;
            } else {
                attempts++;
                System.out.println("Password is incorrect. Attempts remaining: " + (MAX_ATTEMPTS - attempts)); // informs user that password entered is incorrect and shows # of attempts left
            }
        }

        if (attempts == MAX_ATTEMPTS) {
            System.out.println("Access denied, try again soon."); // informs user that they have reached the max # of password attempts and their access is denied
            return;
        }

        // Main menu of bank account management system allowing user to input each specific # for a different function
        while (true) {
            System.out.println("\n1. Create a new checking account"); // allows creation of new checking account
            System.out.println("2. Create a new saving account"); // allows creation of new saving account
            System.out.println("3. Deposit to checking account"); // allows deposits to specified checking account
            System.out.println("4. Deposit to saving account"); // allows deposits to specified saving account
            System.out.println("5. Withdraw from checking account"); // allows withdraws from specified checking account
            System.out.println("6. Withdraw from saving account"); // allows withdraws from specified saving account
            System.out.println("7. Check balance of checking/saving"); // allows user to check the balance of specified checking and/or saving account
            System.out.println("8. Process a check (checking account ONLY)"); // allows user to process a check ONLY to the specified checking accounts
            System.out.println("9. Apply interest to saving account"); // allows user to enter interest rates ONLY to specified saving accounts
            System.out.println("10. Exit"); // allows user to exit the bank account management screen
            System.out.print("Choose an option: "); // prompts the user for their inputted option
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Please enter checking account number: "); // prompts user to enter # for new checking account
                    String checkingAccountNumber = scanner.nextLine();
                    CheckingAccount newCheckingAccount = new CheckingAccount(checkingAccountNumber);
                    checkingAccounts.add(newCheckingAccount);
                    System.out.println("New checking account created: " + checkingAccountNumber);
                    break;
                case 2:
                    System.out.print("Please enter saving account number: "); // prompts user to enter # for a new saving account
                    String savingAccountNumber = scanner.nextLine();
                    System.out.print("Please enter an interest rate (ex; 0.08 for 8%): "); // prompts user to enter an interest rate for a new saving account
                    double interestRate = scanner.nextDouble();
                    SavingAccount newSavingAccount = new SavingAccount(savingAccountNumber, interestRate);
                    savingAccounts.add(newSavingAccount);
                    System.out.println("New saving account created: " + savingAccountNumber);
                    break;
                case 3:
                    System.out.print("Please enter checking account number to deposit: "); // prompts user for checking account # needed for deposits
                    checkingAccountNumber = scanner.nextLine();
                    CheckingAccount checkingAccount = findCheckingAccount(checkingAccountNumber);
                    if (checkingAccount != null) {
                        System.out.print("Enter an amount to deposit: "); // prompts user to enter amount of deposit
                        double depositAmount = scanner.nextDouble();
                        checkingAccount.deposit(depositAmount);
                        System.out.println("Deposited " + depositAmount + " to checking account " + checkingAccountNumber); // informs user their deposit was successful
                    } else {
                        System.out.println("Checking account is not found."); // informs user that entered account # is invalid
                    }
                    break;
                case 4:
                    System.out.print("Please enter saving account number to deposit: "); // prompts user for saving account # for deposits
                    savingAccountNumber = scanner.nextLine();
                    SavingAccount savingAccount = findSavingAccount(savingAccountNumber);
                    if (savingAccount != null) {
                        System.out.print("Enter an amount to deposit: "); // prompts user to enter amount of deposit
                        double savingDepositAmount = scanner.nextDouble();
                        savingAccount.deposit(savingDepositAmount);
                        System.out.println("Deposited " + savingDepositAmount + " to saving account " + savingAccountNumber); // informs user their deposit was successful
                    } else {
                        System.out.println("Saving account is not found."); // informs user that entered account # is invalid
                    }
                    break;
                case 5:
                    System.out.print("Please enter checking cccount number to withdraw from: "); // prompts user for checking account # for withdraws
                    checkingAccountNumber = scanner.nextLine();
                    checkingAccount = findCheckingAccount(checkingAccountNumber);
                    if (checkingAccount != null) {
                        System.out.print("Enter an amount to withdraw: "); // prompts user to enter amount to withdraw
                        double withdrawAmount = scanner.nextDouble();
                        checkingAccount.withdraw(withdrawAmount);
                        System.out.println("Withdrew " + withdrawAmount + " from checking account " + checkingAccountNumber); // informs user that their withdraw was successful
                    } else {
                        System.out.println("Checking account is not found."); // informs user that entered account # is invalid
                    }
                    break;
                case 6:
                    System.out.print("Please enter saving account number to withdraw from: "); // prompts user for saving account # for withdraws
                    savingAccountNumber = scanner.nextLine();
                    savingAccount = findSavingAccount(savingAccountNumber);
                    if (savingAccount != null) {
                        System.out.print("Enter an amount to withdraw: "); // prompts user to enter amount to withdraw
                        double savingWithdrawAmount = scanner.nextDouble();
                        savingAccount.withdraw(savingWithdrawAmount);
                        System.out.println("Withdrew " + savingWithdrawAmount + " from saving account " + savingAccountNumber); // informs user that their withdraw was successful
                    } else {
                        System.out.println("Saving account is not found."); // informs user that entered account # is invalid
                    }
                    break;
                case 7:
                    System.out.print("Please enter checking account number to check balance: "); // prompts user for checking account # in order to reveal balance of specified account
                    checkingAccountNumber = scanner.nextLine();
                    checkingAccount = findCheckingAccount(checkingAccountNumber);
                    if (checkingAccount != null) {
                        System.out.println("Checking account balance: " + checkingAccount.getBalance()); // provides user with account balance
                    } else {
                        System.out.println("Checking account is not found."); // informs user that entered account # is invalid
                    }
                    System.out.print("Please enter saving account number to check balance: "); // prompts user for saving account # in order to reveal balance of specified account
                    savingAccountNumber = scanner.nextLine();
                    savingAccount = findSavingAccount(savingAccountNumber);
                    if (savingAccount != null) {
                        System.out.println("Saving account Balance: " + savingAccount.getBalance()); // provides user with account balance
                    } else {
                        System.out.println("Saving account is not found."); // informs user that entered account # is invalid
                    }
                    break;
                case 8:
                    System.out.print("Please enter checking account number to process check: "); // prompts user to enter ONLY a checking account # for the ability to process a check
                    checkingAccountNumber = scanner.nextLine();
                    checkingAccount = findCheckingAccount(checkingAccountNumber);
                    if (checkingAccount != null) {
                        System.out.print("Enter check amount: "); // prompts user to enter check amount
                        double checkAmount = scanner.nextDouble();
                        checkingAccount.processCheck(checkAmount);
                        System.out.println("Processed check of " + checkAmount + " from checking account " + checkingAccountNumber); // informs user that check process was successful
                    } else {
                        System.out.println("Checking account is not found."); // informs user that entered account # is invalid
                    }
                    break;
                case 9:
                    System.out.print("Please enter saving account number to apply interest: "); // prompts user to enter saving account # that needs interest rate applied
                    savingAccountNumber = scanner.nextLine();
                    savingAccount = findSavingAccount(savingAccountNumber);
                    if (savingAccount != null) {
                        savingAccount.applyInterest();
                        System.out.println("Applied interest to saving account " + savingAccountNumber); // informs user that interest application was successful
                    } else {
                        System.out.println("Saving account is not found."); // informs user that entered account # is invalid
                    }
                    break;
                case 10:
                    System.out.println("Come back soon!"); // user closes program
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again."); // user enters an invalid option
            }
        }
    }

    private static CheckingAccount findCheckingAccount(String accountNumber) {
        for (CheckingAccount account : checkingAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    private static SavingAccount findSavingAccount(String accountNumber) {
        for (SavingAccount account : savingAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}