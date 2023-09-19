package SKRUM.TicketGuru.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TicketTypeRepository extends CrudRepository<TicketType, Long> {
	
    List<TicketType> findByDescription(String description);
}