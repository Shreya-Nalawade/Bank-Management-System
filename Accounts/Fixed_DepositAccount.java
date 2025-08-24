package Accounts;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Fixed_DepositAccount extends TradeAccount {
	
    private BigDecimal principal;
    private int durationMonths;
    private static final double INTEREST_RATE = 0.06; // 6% annual interest
    private String accountHolderName;
    private String accountNumber;
    private boolean isActive;

    public Fixed_DepositAccount(String id, BigDecimal principal2, int durationMonths2,
                                 String accountHolderName2, String accountNumber2) {
        super(id);
        setPrincipal(principal2);
        setDurationMonths(durationMonths2);
        this.accountHolderName = accountHolderName2;
        this.accountNumber = accountNumber2;
        this.isActive = true;
    }

    // Getters and Setters 
    public BigDecimal getPrincipal() {
        return principal;
    }

    public BigDecimal getBalance() {
        return this.principal;
    }


    public int getDurationMonths() {
        return durationMonths;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean isActive() {
        return isActive;
    }
    
    public void setPrincipal(BigDecimal principal2) {
        if (principal2 == null || principal2.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Principal must be a positive amount");
        }
        this.principal = principal2;
    }
    
    public void setDurationMonths(int durationMonths) {
        if (durationMonths <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        this.durationMonths = durationMonths;
    }
    
    
    public BigDecimal calculateMaturityAmount() {
        if (!isActive) {
            throw new IllegalStateException("Account is closed. Cannot calculate maturity amount.");
        }

        BigDecimal r = BigDecimal.valueOf(INTEREST_RATE)
                                             .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
        BigDecimal compoundFactor = BigDecimal.ONE.add(r)
                                                   .pow(durationMonths);
        return principal.multiply(compoundFactor).setScale(2, RoundingMode.HALF_UP);
    }
    
    @Override
    public TradeAccount clone() {
        return new Fixed_DepositAccount(this.getId(), this.principal, this.durationMonths,
                                       this.accountHolderName, this.accountNumber);
    }
}
