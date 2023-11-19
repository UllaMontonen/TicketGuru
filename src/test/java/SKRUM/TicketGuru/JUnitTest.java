package SKRUM.TicketGuru;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import SKRUM.TicketGuru.domain.*;

@DataJpaTest
public class JUnitTest {
	
	@Autowired
	private TicketRepository tRepo;
	@Autowired
	private EventRepository eRepo;
	@Autowired
	private TicketTypeRepository ttRepo;
	@Autowired
	private TransactionRepository trRepo;
	@Autowired
	private CustomerRepository cRepo;
	
	@BeforeEach
	public void setUp() {
		Date date = new Date();

		// customer
		cRepo.save(new Customer("Testi Pesti", "email@mail.com"));

		// event
		eRepo.save(new Event("TestiTapahtuma", "Apollo", "Helsinki", 100, date));
		eRepo.save(new Event("TestiTapahtuma2", "Maxine", "Helsinki", 100, date));

		// TicketType

		ttRepo.save(new TicketType("Normaali", 20.30, eRepo.findByName("TestiTapahtuma").get(0)));
		ttRepo.save(new TicketType("Opiskelija", 21.32, eRepo.findByName("TestiTapahtuma2").get(0)));

		// ticket ja transaction 
		
		tRepo.save(new Ticket(eRepo.findByName("TestiTapahtuma").get(0),
				ttRepo.findByDescription("Normaali").get(0), trRepo.save(new Transaction(new Date(), 56.34, cRepo.findByName("Testi Pesti").get(0))), "TESTI123", false));	
	}
	
	@Test
	public void findByCodeTest() {
		//Luodaan tiketti koodilla
		String code = "TESTI-123";
		Ticket madeTicket = tRepo.save(new Ticket(eRepo.findByName("TestiTapahtuma").get(0),
				ttRepo.findByDescription("Normaali").get(0), trRepo.save(new Transaction(new Date(), 56.34, cRepo.findByName("Testi Pesti").get(0))), code, true));
		
		Ticket foundTicket = tRepo.findByCode(code).get();
		
		//Vertaa luodun tiketin ja löydetyn tiketin ID:tä
		assertThat(madeTicket.getId()).isEqualTo(foundTicket.getId());
		
	}

}
