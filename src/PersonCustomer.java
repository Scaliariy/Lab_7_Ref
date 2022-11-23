public class PersonCustomer extends Customer{
    public PersonCustomer(String name, String surname, String email, CustomerType customerType, Account account) {
        this.setName(name);
        this.setSurname(surname);
        this.setEmail(email);
        this.setCustomerType(customerType);
        this.setAccount(account);
    }
}
