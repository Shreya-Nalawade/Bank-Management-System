package Accounts;

public abstract class TradeAccount {

    private String id;

    public TradeAccount(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract TradeAccount clone();
}
