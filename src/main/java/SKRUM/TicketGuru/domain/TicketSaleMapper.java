package SKRUM.TicketGuru.domain;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketSaleMapper {

	private final CustomerRepository cRepo;
	private final EventRepository eRepo;
	
	@Autowired
	public TicketSaleMapper(CustomerRepository cRepo, EventRepository eRepo) {
		this.cRepo = cRepo;
		this.eRepo = eRepo;
	}
	
	public Optional<Customer> DtoToCustomerById(TicketSaleDTO ticketSale) {
		return cRepo.findById(ticketSale.getCustomerId());
	}
	
	public Customer DtoToCustomerByName(TicketSaleDTO ticketSale) {
		Customer customer = new Customer(ticketSale.getCustomerName(), ticketSale.getCustomerEmail());
		return cRepo.save(customer);
	}
	
	public Optional<Event> DtoToEventById(TicketDTO ticketDto) {
		return eRepo.findById(ticketDto.getEventId());
	}
	
}
