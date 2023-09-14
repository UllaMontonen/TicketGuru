package SK.R.UM.TicketGuru.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {

        List<Event> findbyName(String name);
}