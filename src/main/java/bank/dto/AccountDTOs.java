package bank.dto;

import java.util.ArrayList;
import java.util.Collection;

public class AccountDTOs {
    Collection<AccountDTO> accountDTOS = new ArrayList<>();

    public AccountDTOs(){
    }

    public Collection<AccountDTO> getAccountDTOS() {
        return accountDTOS;
    }

    public void setAccountDTOS(Collection<AccountDTO> accountDTOS) {
        this.accountDTOS = accountDTOS;
    }

    @Override
    public String toString() {
        return "AccountDTOs{" +
                "accountDTOS=" + accountDTOS +
                '}';
    }
}
