package bank.event;

import bank.dao.TraceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    @Autowired
    TraceDAO traceDAO;

    @EventListener
    public void acountChangeListener(AccountChangeEvent event){
        System.out.println("Sending email for an event: " + event.getEmailMessage());
        traceDAO.save(event.getTraceRecord());
    }



}
