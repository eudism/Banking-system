package bank.integration;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {

    @KafkaListener(topics = {"createAccount"})
    public void receiveCreateMessage(String message){
        System.out.println("Account created message received: " + message);
    }

    @KafkaListener(topics = {"depositToAccount"})
    public void receiveDepositMessage(String message){
        System.out.println("Deposit message received: " + message);
    }


    @KafkaListener(topics = {"withdrawFromAccount"})
    public void receiveWithdrawalMessage(String message){
        System.out.println("Withdraw message received: " + message);
    }
}
