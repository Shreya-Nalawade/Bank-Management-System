package Accounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrentAccount extends TradeAccount {
    private BigDecimal balance;
    private String accountHolderName;
    private String accountNumber;

    public CurrentAccount(String id, BigDecimal initialBalance, String accountHolderName, String accountNumber) {
        super(id);
        this.balance = initialBalance;
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
    }
    
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit must be positive and not null");
        }
        this.balance = this.balance.add(amount);
    }

    public boolean withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive and not null");
        }
        if (amount.compareTo(balance) > 0) {
            return false;
        }
        this.balance = this.balance.subtract(amount);
        return true;
    }


   public CurrentAccount clone() {
        return new CurrentAccount(this.getId(), this.balance, this.accountHolderName, this.accountNumber);
    }

   
    public String toString() {
        return "\n--- Current Account Details ---" +
               "\nID           : " + getId() +
               "\nHolder Name  : " + getAccountHolderName() +
               "\nAccount No.  : " + getAccountNumber() +
               "\nBalance      : ₹" + balance.toPlainString() +
               "\n------------------------------";
    }
    
}
