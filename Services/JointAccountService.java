package Services;

import java.math.BigDecimal;
import Accounts.Joint_Account;

public class JointAccountService {
	private static final int MAX_ACCOUNTS = 100;
    private Joint_Account[] accounts = new Joint_Account[100];
    private int currentSize = 0;

    //  create account
    public void createAccount(String id, String[] accountHolders, String accountNumber, BigDecimal balance) {
        if (currentSize >= MAX_ACCOUNTS) {
            System.out.println("Account limit reached. Cannot create new account.");
            return;
        }
        if (getAccount(id) != null) {
            System.out.println("Account with this ID already exists.");
            return;
        }
        if (balance.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Initial balance must be positive.");
            return;
        }
        try {
            Joint_Account newAccount = new Joint_Account(id, accountNumber, accountHolders, balance);
            accounts[currentSize++] = newAccount;
            System.out.println("Account created successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }

    // Get an account by ID
    public Joint_Account getAccount(String id) {
        for (int i = 0; i < currentSize; i++) {
            if (accounts[i].getId().equals(id)) {
                return accounts[i];
            }
        }
        return null;
    }

    // Deposit money into an account
    public void deposit(String id, BigDecimal amount) {
        Joint_Account account = getAccount(id);
        if (account != null) {
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("Deposit amount must be positive.");
                return;
            }
            account.deposit(amount);
            System.out.println("Deposit successful. New balance: ₹" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    // Withdraw money from an account
    public boolean withdraw(String id, BigDecimal amount) {
        Joint_Account account = getAccount(id);
        if (account != null) {
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("Withdrawal amount must be positive.");
                return false;
            }
            boolean success = account.withdraw(amount);
            if (success) {
                System.out.println("Withdrawal successful. New balance: ₹" + account.getBalance());
            } else {
                System.out.println("Insufficient balance or amount below minimum balance.");
            }
            return success;
        } else {
            System.out.println("Account not found.");
            return false;
        }
    }

    // Display account details
    public void displayAccount(String id) {
        Joint_Account account = getAccount(id);
        if (account != null) {
            System.out.println(account);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Calculate interest 
    public BigDecimal calculateInterest(String id, int months) {
        Joint_Account account = getAccount(id);
        if (account == null) {
            System.out.println("Account not found.");
            return BigDecimal.ZERO;
        }
        if (months <= 0) {
            System.out.println("Months should be greater than zero.");
            return BigDecimal.ZERO;
        }

        BigDecimal interestRate = new BigDecimal("0.05"); // 5% annual
        BigDecimal annualInterest = account.getBalance().multiply(interestRate);
        BigDecimal monthlyInterest = annualInterest.divide(new BigDecimal("12"), 2, BigDecimal.ROUND_HALF_UP);
        return monthlyInterest.multiply(new BigDecimal(months));
    }
}
