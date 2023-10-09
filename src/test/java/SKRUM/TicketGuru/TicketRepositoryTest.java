package SKRUM.TicketGuru;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import SKRUM.TicketGuru.domain.*;

@DataJpaTest
public class TicketRepositoryTest {

	@Autowired
	private TicketRepository tRepo;
	
	//Tarkistestaan että luotu Ticket saa ID:n
	@Test
	public void createNewTicket() {
		Event event = new Event();
		event.setId((long) 1);
		Ticket ticket = tRepo.save(new Ticket(event, null, null, "ABC-132", true));
		
		assertThat(ticket.getId()).isNotNull();
	}
	
	//Luo tiketin ja tarkistaa tikettien määrän, poistetaan lisätty tiketti ja varmistetaan
	//että tikettejä on yksi vähemmän
	@Test
	public void deleteTicket() {
		Ticket ticket = tRepo.save(new Ticket(null, null, null, "ABC-132", true));
		System.out.println(ticket.getId());
		List<Ticket> tickets = (List<Ticket>) tRepo.findAll();
		tRepo.deleteById(ticket.getId());
		List<Ticket> newTickets = (List<Ticket>) tRepo.findAll();
		
		assertThat(tickets.size()).isEqualTo(newTickets.size() + 1);
	}
	
	//Luo tiketin ja etsii sen ID:n perusteella kannasta tiketin
	//tarkistaa tikettien koodien perusteellaa yhtäläisyyden 
	@Test
	public void createAndFindTicketById() {
		Ticket ticket = tRepo.save(new Ticket(null, null, null, "TestiKoodi", true));
		Ticket foundTicket = tRepo.findById(ticket.getId()).get();
		
		assertThat(foundTicket.getCode()).isEqualTo(ticket.getCode());
	}
}
