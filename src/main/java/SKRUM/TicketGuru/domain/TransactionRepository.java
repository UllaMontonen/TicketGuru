package SKRUM.TicketGuru.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	List<Transaction> findByCustomer(Customer customer);
}