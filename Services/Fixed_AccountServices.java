package Services;
import Accounts.Fixed_DepositAccount;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Fixed_AccountServices {

    private int AccountCount = 0;
    private Fixed_DepositAccount[] accounts = new Fixed_DepositAccount[100];

    // Create account method
    public void createAccount(Fixed_DepositAccount newAcc) {
        if (newAcc == null || newAcc.getId() == null) {
            throw new IllegalArgumentException("Invalid account data");
        }

        if (AccountCount < accounts.length) {
            accounts[AccountCount++] = newAcc;
            System.out.println("\nFixed Account Created Sucessfully");
            System.out.println("\nID :" + newAcc.getId() +
                               ", Name: " + newAcc.getAccountHolderName() +
                               ", Acc#: " + newAcc.getAccountNumber() +
                               ", Balance: " + newAcc.getBalance()+
                               ", Duration in Months: "+ newAcc.getDurationMonths() 
                               );
        } else {
            System.out.println("Account creation failed. Storage full.");
        }
    }

    // Retrieve account by ID
    public Fixed_DepositAccount getAccount(String accountId) {
        for (int i = 0; i < AccountCount; i++) {
            if (accounts[i] != null && accounts[i].getId().equals(accountId)) {
                return accounts[i];
            }
        }
        return null;
    }

    // Get Maturity Amount
    public BigDecimal getMaturityAmount(Fixed_DepositAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        return account.calculateMaturityAmount();
    }

    // Display account details
    public void displayAccount(Fixed_DepositAccount account) {
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("\n---Fixed Deposit Account Details---");
        System.out.println("\nAccount ID: " + account.getId() +
                "\nAccount Holder: " + account.getAccountHolderName() +
                "\nAccount Number: " + account.getAccountNumber() +
                "\nPrincipal: ₹" + account.getPrincipal() +
                "\nDuration (months): " + account.getDurationMonths());
        System.out.println("Status: " + (account.isActive() ? "Active" : "Closed"));
        if (account.isActive()) {
            System.out.println("Maturity Amount: ₹" + getMaturityAmount(account));
        }
        System.out.println("-----------------------------");
    }
    
    // Deposit
    public void deposit(Fixed_DepositAccount account, BigDecimal amount) {
        if (account == null || !account.isActive()) {
            throw new IllegalStateException("Account is null or inactive");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        BigDecimal newPrincipal = account.getPrincipal().add(amount);
        account.setPrincipal(newPrincipal);

        System.out.println("Deposit successful. New principal: ₹" + newPrincipal);
    }

    // Withdraw
    public void withdraw(Fixed_DepositAccount account, BigDecimal amount) {
        if (account == null || !account.isActive()) {
            throw new IllegalStateException("Account is null or inactive");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        if (amount.compareTo(account.getPrincipal()) > 0) {
            throw new IllegalArgumentException("Insufficient balance in fixed deposit.");
        }

        BigDecimal updatedPrincipal = account.getPrincipal().subtract(amount);
        account.setPrincipal(updatedPrincipal);

        System.out.println("Withdrawal successful. New principal: ₹" + updatedPrincipal);
    }

   
}
