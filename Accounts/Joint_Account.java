package Accounts;

import java.math.BigDecimal;

public class Joint_Account {

    private BigDecimal balance;
    private final String[] holders;
    private boolean isActive;
    private final String accNumber;
    private final String id;
    private static final BigDecimal MIN_BALANCE = new BigDecimal("1000");

    public Joint_Account(String id, String accNumber, String[] holders, BigDecimal balance) 
    {
        if (id == null || accNumber == null || holders == null || balance == null)
            throw new IllegalArgumentException("Inputs cannot be null");
        if (holders.length < 2)
            throw new IllegalArgumentException("At least 2 holders required");
        for (String h : holders) {
            if (h == null || h.trim().isEmpty())
                throw new IllegalArgumentException("Holder name can't be empty");
        }

        this.id = id;
        this.accNumber = accNumber;
        this.holders = holders.clone();
        setBalance(balance);
        this.isActive = true;
    }

    public void deposit(BigDecimal amount) 
    {
        checkActive();
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Invalid deposit amount");
        balance = balance.add(amount);
    }

    public boolean withdraw(BigDecimal amount)
    {
        checkActive();
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Invalid withdrawal amount");
        BigDecimal newBal = balance.subtract(amount);
        if (newBal.compareTo(MIN_BALANCE) >= 0) {
            balance = newBal;
            return true;
        }
        return false;
    }

    private void setBalance(BigDecimal balance) 
    {
        if (balance.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Balance must be non-negative");
        this.balance = balance;
    }

    private void checkActive() 
    {
        if (!isActive) throw new IllegalStateException("Account is closed");
    }

    public String getId() 
    { 
    	return id;
    }
    public String getAccNumber()
    { 
    	return accNumber; 
    }
    public String[] getHolders() 
    { 
    	return holders.clone(); 
    }
    public BigDecimal getBalance() 
    { 
    	return balance; 
    }
    public boolean isActive() 
    { 
    	return isActive; 
    }
    public String getPrimaryHolder() 
    {
    	return holders[0]; 
    }
    
    public String getSecondaryHolder() 
    { 
    	return holders.length > 1 ? holders[1] : null; 
    }

    
    public String toString() {
        return "Joint Account [ID: " + id +
                ", Number: " + accNumber +
                ", Holders: " + String.join(" & ", holders) +
                ", Balance: ₹" + balance +
                ", Status: " + (isActive ? "Active" : "Closed") + "]";
    }
}
