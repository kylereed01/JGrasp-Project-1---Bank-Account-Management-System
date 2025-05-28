/*
Kyle Reed
Prof. Huang
COS-210-300
5 February 2025
*/

// Class for BankAccount
abstract class BankAccount implements Account {
    protected double balance;
    protected String accountNumber;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getAccountType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }
}
