/*
Kyle Reed
Prof. Huang
COS-210-300
5 February 2025
*/

// Class for SavingAccount
class SavingAccount extends BankAccount {
    private double interestRate;

    public SavingAccount(String accountNumber, double interestRate) {
        super(accountNumber);
        this.interestRate = interestRate;
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

    public void applyInterest() {
        balance += balance * interestRate;
    }
}
