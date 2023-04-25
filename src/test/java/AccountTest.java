import bank.domain.Account;

import bank.domain.Customer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(JUnit4.class)
public class AccountTest {


    @Test
    public void testDeposit(){
        Account account = new Account();
        account.deposit(3000);
        assertThat(account.getBalance(), closeTo(3000, 0.01));
    }
    @Test
    public void testWithdraw(){
        Account account = new Account();
        account.deposit(3000);
        account.withdraw(1000);
        assertThat(account.getBalance(), closeTo(2000, 0.01));
    }

    @Test
    public void transferTest(){
        Account account1 = new Account();
        account1.deposit(2000);

        Account account2 = new Account();
        Customer customer = new Customer("Michael");

        account1.setCustomer(customer);
        account2.setCustomer(customer);

        account1.transferFunds(account2, 1000, "He is broke!");
        assertThat(account1.getBalance(), closeTo(1000.0, 0.01));
        assertThat(account2.getBalance(), closeTo(1000.0, 0.01));
    }
}
