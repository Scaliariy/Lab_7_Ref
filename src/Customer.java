abstract class Customer {

    private String name;
    private String surname;
    private String email;
    private CustomerType customerType;
    Account account;
    private double companyOverdraftDiscount = 1;

    abstract void typeCustomerOverdraft(double sum, double sum1);

    public void overdraft(double sum, double sum1) {
        if (account.getMoney() < 0) {
            account.setMoney((account.getMoney() - sum) - sum1, account.getCurrency());
        } else {
            account.setMoney(account.getMoney() - sum, account.getCurrency());
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

    public String getFullName() {
        return name + " " + surname + " ";
    }

    public String printCustomerMoney() {
        String fullName = getFullName();
        String accountDescription = "";
        accountDescription += "Account: IBAN: " + account.getIban() + ", Money: " + account.getMoney();
        return fullName + accountDescription;
    }

    public String printCustomer() {
        return getName() + " " + getEmail();
    }
}
