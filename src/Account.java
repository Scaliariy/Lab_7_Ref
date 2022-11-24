public class Account {

    private String iban;
    private int daysOverdrawn;

    private Money money;

    Customer customer;

    public boolean premium;

    public Account(Boolean type, int daysOverdrawn) {
        super();
        this.premium = type;
        this.daysOverdrawn = daysOverdrawn;
    }

    public double bankcharge() {
        double result = 4.5;

        result += overdraftCharge();

        return result;
    }

    private double overdraftCharge() {
        if (isPremium()) {
            double result = 10;
            if (getDaysOverdrawn() > 7)
                result += (getDaysOverdrawn() - 7) * 1.0;
            return result;
        } else
            return getDaysOverdrawn() * 1.75;
    }

    public double overdraftFee() {
        if (isPremium()) {
            return 0.10;
        } else {
            return 0.20;
        }
    }


    public int getDaysOverdrawn() {
        return daysOverdrawn;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setMoney(double money, String currency) {
        this.money = new Money(money, currency);
    }

    public double getMoney() {
        return money.getMoney();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getType() {
        return premium ? "premium" : "normal";
    }

    public String getCurrency() {
        return money.getCurrency();
    }

    public String printCustomerAccount(Customer customer) {
        return "Account: IBAN: " + getIban() + ", Money: "
                + getMoney() + ", Account type: " + getType();
    }

    public void withdraw(double sum, String currency, Customer customer) {
        final boolean isPremium = isPremium();
        if (!getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
        if (isPremium) {
            customer.typeCustomerOverdraft(sum, sum * overdraftFee() * customer.getCompanyOverdraftDiscount() / 2);
        } else {
            customer.typeCustomerOverdraft(sum, sum * overdraftFee() * customer.getCompanyOverdraftDiscount());
        }
    }

    public String printCustomerDaysOverdrawn(Customer customer) {
        String fullName = customer.getFullName();
        String accountDescription = "Account: IBAN: " + getIban() + ", Days Overdrawn: " + getDaysOverdrawn();
        return fullName + accountDescription;
    }

    public boolean isPremium() {
        return premium;
    }
}
