package model;

public class Wallet {
    public int balance;

    public Wallet(int startBalance) {
        balance = startBalance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int value) {
        setBalance(getBalance() + value);
    }

    public void withdraw(int value) {
        setBalance(getBalance() - value);
    }
}
