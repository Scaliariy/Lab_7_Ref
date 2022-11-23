public class CompanyCustomer extends Customer{
    public CompanyCustomer(String name, String email, Account account, double companyOverdraftDiscount) {
        this.setName(name);
        this.setEmail(email);
        this.setCustomerType(CustomerType.COMPANY);
        this.setAccount(account);
        this.setCompanyOverdraftDiscount(companyOverdraftDiscount);
    }

    public void typeCustomerOverdraft(double sum, double sum1) {
        overdraft(sum, sum1);
    }
}
