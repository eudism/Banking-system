package bank;

import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.domain.Customer;
import bank.dto.AccountDTO;
import bank.service.AccountService;
import bank.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.transaction.Transactional;
import java.util.Collection;


@SpringBootApplication
@EnableKafka
@EnableScheduling
public class Application implements CommandLineRunner {
	@Autowired
	IAccountService accountService;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);


	}

	@Override
	public void run(String... args) throws Exception {
		// create 2 accounts;
		accountService.createAccount(1263862, "Frank Brown");
		accountService.createAccount(4253892, "John Doe");
		//use account 1;
		accountService.deposit(1263862, 240);
		accountService.deposit(1263862, 529);
		accountService.withdrawEuros(1263862, 230);
		//use account 2;
		accountService.deposit(4253892, 12450);
		accountService.depositEuros(4253892, 200);
		accountService.transferFunds(4253892, 1263862, 100, "payment of invoice 10232");
		// show balances


	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}


