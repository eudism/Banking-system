package bank.service;

import bank.dao.IAccountDAO;
import bank.domain.Account;
import bank.domain.Customer;
import bank.dto.AccountDTO;
import bank.event.AccountChangeEvent;
import bank.event.TraceRecord;
import bank.integration.IJMSSender;
import bank.logging.ILogger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class AccountService implements IAccountService {
	@Autowired
	private IAccountDAO accountDAO;
	@Autowired
	private ICurrencyConverter currencyConverter;
	@Autowired
	private IJMSSender jmsSender;
	@Autowired
	private ILogger logger;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ApplicationEventPublisher eventPublisher;


	public AccountDTO createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountDAO.save(account);
		logger.logInfo("createAccount with parameters accountNumber= "+accountNumber+" , customerName= "+customerName);
		return modelMapper.map(account, AccountDTO.class);
	}

	public AccountDTO deposit(long accountNumber, double amount) {
		Account account = accountDAO.findByAccountnumber(accountNumber);
		account.deposit(amount);
		accountDAO.save(account);
		logger.logTrace("deposit with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
		eventPublisher.publishEvent(new AccountChangeEvent("Sending an email to accountNumber"+ accountNumber +
				" about a deposit of "+amount, new TraceRecord(LocalDateTime.now(), accountNumber, "Deposit", amount)));
		return modelMapper.map(account, AccountDTO.class);
	}

	public AccountDTO getAccount(long accountNumber) {
		Account account = accountDAO.findByAccountnumber(accountNumber);
		return modelMapper.map(account, AccountDTO.class);
	}

	public Collection<AccountDTO> getAllAccounts() {
		Collection<AccountDTO> dtos = new ArrayList<>();
		for (Account acc: accountDAO.findAll()){
			dtos.add(modelMapper.map(acc, AccountDTO.class));
		}
		return dtos;
	}

	public AccountDTO withdraw(long accountNumber, double amount) {
		Account account = accountDAO.findByAccountnumber(accountNumber);
		account.withdraw(amount);
		accountDAO.save(account);
		logger.logDebug("withdraw with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		eventPublisher.publishEvent(new AccountChangeEvent("Sending an email to accountNumber"+ accountNumber +
				" about a withdraw of "+amount, new TraceRecord(LocalDateTime.now(), accountNumber, "Withdraw", amount)));

		return modelMapper.map(account, AccountDTO.class);
	}

	public void depositEuros(long accountNumber, double amount) {
		Account account = accountDAO.findByAccountnumber(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountDAO.save(account);
		logger.logError("depositEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		if (amountDollars > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber);
		}
		eventPublisher.publishEvent(new AccountChangeEvent("Sending an email to accountNumber"+ accountNumber +
				" about a deposit euros of "+amount, new TraceRecord(LocalDateTime.now(), accountNumber, "Deposit Euros", amount)));

	}

	public void withdrawEuros(long accountNumber, double amount) {
		Account account = accountDAO.findByAccountnumber(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountDAO.save(account);
		logger.logWarn("withdrawEuros with parameters accountNumber= "+accountNumber+" , amount= "+amount);
		eventPublisher.publishEvent(new AccountChangeEvent("Sending an email to accountNumber"+ accountNumber +
				" about a withdraw euros of "+amount, new TraceRecord(LocalDateTime.now(), accountNumber, "Withdraw Euros", amount)));

	}

	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		Account fromAccount = accountDAO.findByAccountnumber(fromAccountNumber);
		Account toAccount = accountDAO.findByAccountnumber(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.save(fromAccount);
		accountDAO.save(toAccount);
		logger.logTrace("transferFunds with parameters fromAccountNumber= "+fromAccountNumber+" , toAccountNumber= "+toAccountNumber+" , amount= "+amount+" , description= "+description);
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount);
		}

	}
}
