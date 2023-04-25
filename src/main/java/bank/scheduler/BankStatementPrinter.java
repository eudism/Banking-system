package bank.scheduler;

import bank.domain.AccountEntry;
import bank.domain.Customer;
import bank.dto.AccountDTO;
import bank.dto.AccountDTOs;
import bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class BankStatementPrinter {

    @Autowired
    private AccountService accountService;

    //The following receipt is printed whenever a transaction is madestatus
    @Scheduled(fixedRate = 20000)
    public void printer(){

        Collection<AccountDTO> accountlist = accountService.getAllAccounts();
        Customer customer = null;
        for (AccountDTO account : accountlist) {
            customer = account.getCustomer();
            System.out.println("Statement for Account: " + account.getAccountnumber());
            System.out.println("Account Holder: " + customer.getName());
            System.out.println("-Date-------------------------"
                    + "-Description------------------"
                    + "-Amount-------------");
            for (AccountEntry entry : account.getEntryList()) {
                System.out.printf("%30s%30s%20.2f\n", entry.getDate()
                        .toString(), entry.getDescription(), entry.getAmount());
            }
            System.out.println("----------------------------------------"
                    + "----------------------------------------");
            System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:",
                    account.getBalance());
        }
    }
}
