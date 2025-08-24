package Services;

import Accounts.SavingAccount;
import java.math.BigDecimal;

public class SavingAccountService {

    // Array to store accounts
    private SavingAccount[] accounts = new SavingAccount[100]; 
    private int currentAccountCount = 0; 

    // Create Account
    public void createAccount(SavingAccount account) {
        if (currentAccountCount < accounts.length) {
            accounts[currentAccountCount] = account;
            currentAccountCount++;
            // Log account creation
            System.out.println("\nSaving Account Created Sucessfully");
            System.out.println("\nID :" + account.getId() +
                               ", Name: " + account.getHolderName() +
                               ", Acc#: " + account.getAccountNumber() +
                               ", Balance: " + account.getBalance()
                               );
        } else {
            System.out.println("Account limit reached. Cannot create more accounts.");
        }
    }
    
    // Get Account by ID
    public SavingAccount getAccount(String accountId) {
        for (int i = 0; i < currentAccountCount; i++) {
            if (accounts[i] != null && accounts[i].getId().equals(accountId)) {
                return accounts[i];
            }
        }
        return null; // Account not found
    }


    // Deposit
    public void deposit(SavingAccount account, BigDecimal amount) {
        account.deposit(amount);
        // Log deposit operation
        System.out.println("Saving | Deposit | ID: " + account.getId() +
                           ", Amount: " + amount+ ", | Balance: " + account.getBalance());
    }

    // Withdraw
    public boolean withdraw(SavingAccount account, BigDecimal amount) {
        boolean success = account.withdraw(amount);
        // Log withdrawal operation
        System.out.println("Saving | Withdraw | ID: " + account.getId() +
                           ", Amount: " + amount +
                           ", Status: " + (success ? "Success" : "Failed") + ", | Balance: " + account.getBalance());
        return success;
    }

    // Display Account Details
    public void displayAccount(SavingAccount account) {
        if (account != null) {
            System.out.println(account.toString());
        } else {
            System.out.println("Account not found.");
        }
    }

    // Calculate Interest
    public BigDecimal calculateInterest(SavingAccount account, int months) {
        return account.getBalance()
                .multiply(BigDecimal.valueOf(0.04)) // Interest rate of 4%
                .multiply(BigDecimal.valueOf(months))
                .divide(BigDecimal.valueOf(12), 2, BigDecimal.ROUND_HALF_UP);
    }

  
}
