package bank.controller;

import bank.domain.Account;
import bank.dto.AccountDTO;
import bank.dto.AccountDTOs;
import bank.domain.TranscationOrder;
import bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/accounts")
public class BankController {
    @Autowired
    private IAccountService accountService;

    @PostMapping("/account/{accountNumber}/{customerName}")
    public ResponseEntity<?> createAccount(@PathVariable("accountNumber") long accountNumber,
                                           @PathVariable("customerName") String customerName){
        AccountDTO accountDTO = accountService.createAccount(accountNumber, customerName);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }
    @PutMapping("/daccount")
    public ResponseEntity<?> deposit(@RequestBody TranscationOrder transcationOrder){
        AccountDTO accountDTO = accountService.deposit(transcationOrder.getAccountNumber(), transcationOrder.getAmount());
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @PutMapping("/waccount")
    public ResponseEntity<?> withdraw(@RequestBody TranscationOrder transcationOrder){
       AccountDTO accountDTO =  accountService.withdraw(transcationOrder.getAccountNumber(), transcationOrder.getAmount());
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }
    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getAnAccount(@PathVariable("accountNumber") long accountNumber){
        AccountDTO accountDTO = accountService.getAccount(accountNumber);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAllAccounts(){
        Collection<AccountDTO> accountDTOList = accountService.getAllAccounts();
        AccountDTOs accountDTOs = new AccountDTOs();
        accountDTOs.setAccountDTOS(accountDTOList);
        return new ResponseEntity<>(accountDTOs, HttpStatus.OK);
    }
}
