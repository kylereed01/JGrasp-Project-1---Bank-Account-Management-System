/*
Kyle Reed
Prof. Huang
COS-210-300
5 February 2025
*/

// Class for CheckingAccount
class CheckingAccount extends BankAccount {
    public CheckingAccount(String accountNumber) {
        super(accountNumber);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds in account."); // informs user that they have insufficient funds for a withdraw
        }
    }

    public void processCheck(double amount) {
        deposit(amount);
    }
}
