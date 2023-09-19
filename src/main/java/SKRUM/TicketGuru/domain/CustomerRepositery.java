package SKRUM.TicketGuru.domain;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepositery extends CrudRepository<Customer, Long> {
    
}