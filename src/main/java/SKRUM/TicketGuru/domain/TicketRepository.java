package SKRUM.TicketGuru.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;


public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Optional<Ticket> findByCode(String code);
}
