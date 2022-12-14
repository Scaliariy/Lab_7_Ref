import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    @Test
    public void testWithdrawPersonWithNormalAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(false, 34.0);
        Customer customer = getPersonCustomer(account);
        customer.account.withdraw(10, "EUR", customer);
        assertThat(account.getMoney(), is(24.0));
    }

    @Test
    public void testWithdrawPersonWithNormalAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(false, -10.0);
        Customer customer = getPersonCustomer(account);
        customer.account.withdraw(10, "EUR", customer);
        assertThat(account.getMoney(), is(-22.0));
    }

    @Test
    public void testWithdrawPersonWithPremiumAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(true, 34.0);
        Customer customer = getPersonCustomer(account);
        customer.account.withdraw(10, "EUR", customer);
        assertThat(account.getMoney(), is(24.0));
    }

    @Test
    public void testWithdrawPersonWithPremiumAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(true, -10.0);
        Customer customer = getPersonCustomer(account);
        customer.account.withdraw(10, "EUR", customer);
        assertThat(account.getMoney(), is(-21.0));
    }

    @Test
    public void testWithdrawCompanyWithNormalAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(false, 34);
        Customer customer = getCompanyCustomer(account);
        customer.account.withdraw(10, "EUR", customer);
        assertThat(account.getMoney(), is(24.0));
    }

    @Test
    public void testWithdrawCompanyWithNormalAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(false, -10);
        Customer customer = getCompanyCustomer(account);
        customer.account.withdraw(10, "EUR", customer);
        assertThat(account.getMoney(), is(-21.0));
    }

    @Test
    public void testWithdrawCompanyWithPremiumAccount() throws Exception {
        Account account = getAccountByTypeAndMoney(true, 34);
        Customer customer = getCompanyCustomer(account);
        customer.account.withdraw(10, "EUR", customer);
        assertThat(account.getMoney(), is(24.0));
    }

    @Test
    public void testWithdrawCompanyWithPremiumAccountAndOverdraft() throws Exception {
        Account account = getAccountByTypeAndMoney(true, -10);
        Customer customer = getCompanyCustomer(account);
        customer.account.withdraw(10, "EUR", customer);
        assertThat(account.getMoney(), is(-20.25));
    }

    @Test
    public void testPrintCustomerDaysOverdrawn() throws Exception {
        Customer customer = getPersonWithAccount(false);
        assertThat(customer.account.printCustomerDaysOverdrawn(customer), is("danix dan Account: IBAN: RO023INGB434321431241, Days Overdrawn: 9"));
    }

    @Test
    public void testPrintCustomerMoney() throws Exception {
        Customer customer = getPersonWithAccount(false);
        assertThat(customer.printCustomerMoney(), is("danix dan Account: IBAN: RO023INGB434321431241, Money: 34.0"));
    }

    @Test
    public void testPrintCustomerAccountNormal() throws Exception {
        Customer customer = getPersonWithAccount(false);
        assertThat(customer.account.printCustomerAccount(customer), is("Account: IBAN: RO023INGB434321431241, Money: 34.0, Account type: normal"));
    }

    @Test
    public void testPrintCustomerAccountPremium() throws Exception {
        Customer customer = getPersonWithAccount(true);
        assertThat(customer.account.printCustomerAccount(customer), is("Account: IBAN: RO023INGB434321431241, Money: 34.0, Account type: premium"));
    }

    private Customer getPersonWithAccount(boolean premium) {
        Account account = new Account(premium, 9);
        Customer customer = getPersonCustomer(account);
        account.setIban("RO023INGB434321431241");
        account.setMoney(34.0,"EUR");
        return customer;
    }

    private Account getAccountByTypeAndMoney(boolean premium, double money) {
        Account account = new Account(premium, 9);
        account.setIban("RO023INGB434321431241");
        account.setMoney(money,"EUR");
        return account;
    }

    private Customer getPersonCustomer(Account account) {
        PersonCustomer personCustomer = new PersonCustomer("danix", "dan", "dan@mail.com", CustomerType.PERSON, account);
        account.setCustomer(personCustomer);
        return personCustomer;
    }

    private Customer getCompanyCustomer(Account account) {
        CompanyCustomer companyCustomer = new CompanyCustomer("company", "company@mail.com", account, 0.50);
        account.setCustomer(companyCustomer);
        return companyCustomer;
    }
}
