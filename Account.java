/*
Kyle Reed
Prof. Huang
COS-210-300
5 February 2025
*/

// Account interface
interface Account {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
    String getAccountType();
    String getAccountNumber();
}
