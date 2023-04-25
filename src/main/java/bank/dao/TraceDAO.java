package bank.dao;

import bank.event.TraceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraceDAO extends JpaRepository<TraceRecord, Integer> {
}
