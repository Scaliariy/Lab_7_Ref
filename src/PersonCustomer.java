public class PersonCustomer extends Customer{
    public PersonCustomer(String name, String surname, String email, CustomerType customerType, Account account) {
        this.setName(name);
        this.setSurname(surname);
        this.setEmail(email);
        this.setCustomerType(customerType);
        this.setAccount(account);
    }

    public void typeCustomerOverdraft(double sum, double sum1) {
        overdraft(sum, sum * getAccount().overdraftFee());
    }
}
