package Accounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SavingAccount extends TradeAccount {

    private BigDecimal balance;
    private String holderName;
    private String accountNumber;
    private static final double INTEREST_RATE = 0.04; // 4% annual

    // Constructor 
    public SavingAccount(String id, BigDecimal balance, String holderName, String accountNumber) {
        super(id);  // Initialize the parent class with the account ID
        this.balance = balance.setScale(2, RoundingMode.HALF_UP);  // Ensure balance has 2 decimal places
        this.holderName = holderName;
        this.accountNumber = accountNumber;
    }

    

    // Getters and setters 
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2, RoundingMode.HALF_UP); // Ensure balance has 2 decimal places
    }
    
    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public void deposit(BigDecimal amount) {
        if (amount != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.add(amount).setScale(2, RoundingMode.HALF_UP); // Update balance and ensure 2 decimal places
        }
    }

    public boolean withdraw(BigDecimal amount) {
        if (amount != null && amount.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(balance) <= 0) {
            balance = balance.subtract(amount).setScale(2, RoundingMode.HALF_UP); // Update balance and ensure 2 decimal places
            return true;
        }
        return false;
    }

    // Interest calculation for the saving account
    public BigDecimal calculateInterest(int months) {
        return balance.multiply(BigDecimal.valueOf(INTEREST_RATE))
                      .multiply(BigDecimal.valueOf(months))
                      .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP); // Return interest with 2 decimal places
    }
    
    
    public TradeAccount clone() {
        
        return new SavingAccount(this.getId(), this.balance, this.holderName, this.accountNumber);
    }
    
    
    public String toString() {
        return "\n--- Saving Account Details ---" +
               "\nID           : " + getId() +
               "\nHolder Name  : " + holderName +
               "\nAccount No.  : " + accountNumber +
               "\nBalance      : ₹" + balance.setScale(2, RoundingMode.HALF_UP) +
               "\n------------------------------";
    }
}
