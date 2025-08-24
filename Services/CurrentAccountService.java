package Services;
import Accounts.CurrentAccount;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class CurrentAccountService {
	
	 // Max 100 accounts
	 private int currAccountCount = 0;
	 private CurrentAccount[] accounts = new CurrentAccount[100]; 

	 
	 // Create new account 
	 public void createAccount(CurrentAccount account) {
	        if (account != null) {
	            if (currAccountCount < accounts.length) {
	                accounts[currAccountCount++] = account; 
	                System.out.println("\nCurrent Account Created Sucessfully");
	                System.out.println("\nID :" + account.getId() +
	                                   ", Name: " + account.getAccountHolderName() +
	                                   ", Acc#: " + account.getAccountNumber() +
	                                   ", Balance: " + account.getBalance()
	                                   );
	            } else {
	                System.out.println("Cannot create account. Limit reached.");
	            }
	        } else {
	            System.out.println("Account creation failed.");
	        }
	    }
	    
	  public CurrentAccount getAccount(String accountId) {
	        for (int i = 0; i < currAccountCount; i++) {
	            if (accounts[i] != null && accounts[i].getId().equals(accountId)) {
	                return accounts[i];
	            }
	        }
	        return null; // Account not Found
	    }
	    

    // Display Account details
    public void displayAccount(CurrentAccount account) {
        if (account != null) {
        	System.out.println(account.toString());
        } else {
            System.out.println("Account not found.");
        }
    }
    
    // Deposit funds
    public void deposit(CurrentAccount acc, BigDecimal amount) {
        if (acc != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            acc.deposit(amount);
            logTransaction(acc, "Deposit", amount);
            System.out.println("Deposit successful. Updated Balance: ₹" + acc.getBalance());
        } else {
            System.out.println("Invalid account or amount.");
        }
    }
    

    // Withdraw funds
    public void withdraw(CurrentAccount acc, BigDecimal amount) {
        if (acc != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            if (acc.withdraw(amount)) {
                logTransaction(acc, "Withdraw", amount);
                System.out.println("Withdrawal successful. Updated Balance: ₹" + acc.getBalance());
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Invalid account or amount.");
        }
    }

    // Log transaction 
    private void logTransaction(CurrentAccount acc, String transactionType, BigDecimal amount) {
        String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(dateTime + " | Account: " + acc.getAccountNumber() +
                " | Type: " + transactionType + " | Amount: ₹" + amount);
    }

 	
}

