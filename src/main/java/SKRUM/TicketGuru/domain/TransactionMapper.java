package SKRUM.TicketGuru.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionMapper {
	
	private final CustomerRepository cRepo;
	
	@Autowired
	public TransactionMapper(CustomerRepository cRepo) {
		this.cRepo = cRepo;
	}
	
	public Optional<Customer> DtoToCustomerById(TransactionDTO transactionDto) {
		
		Optional<Customer> foundCustomer = cRepo.findById(transactionDto.getCustomerId());
		return foundCustomer;
	}
	
	public Customer DtoToCustomerByName(TransactionDTO transactionDto) {
		Customer customer = new Customer(transactionDto.getCustomerName(), transactionDto.getCustomerEmail());
		return cRepo.save(customer);
	}
	
}
