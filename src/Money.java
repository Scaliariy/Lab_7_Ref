public class Money {
    private double money;
    private String currency;

    public Money(double money, String currency) {
        this.money = money;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public double getMoney() {
        return money;
    }
}
