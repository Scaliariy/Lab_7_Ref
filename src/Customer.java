public class Customer {

    private String name;
    private String surname;
    private String email;
    private CustomerType customerType;
    private Account account;
    private double companyOverdraftDiscount = 1;

    public void withdraw(double sum, String currency) {
        if (!account.getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
        if (account.getType().isPremium()) {
            typeCustomerOverdraft(sum, sum * account.overdraftFee() * companyOverdraftDiscount / 2);
        } else {
            typeCustomerOverdraft(sum, sum * account.overdraftFee() * companyOverdraftDiscount);
        }
    }

    private void typeCustomerOverdraft(double sum, double sum1) {
        switch (customerType) {
            case COMPANY -> overdraft(sum, sum1);
            case PERSON -> overdraft(sum, sum * account.overdraftFee());
        }
    }

    private void overdraft(double sum, double sum1) {
        // we are in overdraft
        if (account.getMoney() < 0) {
            // 50 percent discount for overdraft for premium account
            account.setMoney((account.getMoney() - sum) - sum1);
        } else {
            account.setMoney(account.getMoney() - sum);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getCompanyOverdraftDiscount() {
        return companyOverdraftDiscount;
    }

    public void setCompanyOverdraftDiscount(double companyOverdraftDiscount) {
        this.companyOverdraftDiscount = companyOverdraftDiscount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public String printCustomerDaysOverdrawn() {
        String fullName = getFullName();

        String accountDescription = "Account: IBAN: " + account.getIban() + ", Days Overdrawn: " + account.getDaysOverdrawn();
        return fullName + accountDescription;
    }

    private String getFullName() {
        return name + " " + surname + " ";
    }

    public String printCustomerMoney() {
        String fullName = getFullName();
        String accountDescription = "";
        accountDescription += "Account: IBAN: " + account.getIban() + ", Money: " + account.getMoney();
        return fullName + accountDescription;
    }

    public String printCustomerAccount() {
        return "Account: IBAN: " + account.getIban() + ", Money: "
                + account.getMoney() + ", Account type: " + account.getType();
    }
}
