package SKRUM.TicketGuru;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import SKRUM.TicketGuru.domain.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//addFilters poistaa spring securityn filterit testeistä
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegrationTest {

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
	@Autowired
	private MockMvc mockMvc;

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
				ttRepo.findByDescription("Normaali").get(0),
				trRepo.save(new Transaction(new Date(), 56.34, cRepo.findByName("Testi Pesti").get(0))), "TESTI1",
				false));
		tRepo.save(new Ticket(eRepo.findByName("TestiTapahtuma2").get(0),
				ttRepo.findByDescription("Opiskelija").get(0),
				trRepo.save(new Transaction(new Date(), 26.34, cRepo.findByName("Testi Pesti").get(0))), "TESTI2",
				true));

	}

	@Test
	@Transactional
	public void findEventByIdTest() throws Exception {
		this.mockMvc.perform(get("/api/events/1")).andExpect(status().isOk());
	}

	@Test
	@Transactional
	public void checkTicketByCodeTest() throws Exception {
		this.mockMvc.perform(get("/api/tickets/check/TESTI1")).andExpect(status().isOk());
	}

	@Test
	@Transactional
	public void markTicketUsedByCodeTest() throws Exception {
		this.mockMvc.perform(patch("/api/tickets/markused/TESTI1")).andExpect(status().isOk());
	}

	@Test
	@Transactional
	public void postTicketTest() throws Exception {
		Ticket newTicket = new Ticket(eRepo.findByName("TestiTapahtuma").get(0),
				ttRepo.findByDescription("Normaali").get(0),
				trRepo.save(new Transaction(new Date(), 56.34, cRepo.findByName("Testi Pesti").get(0))), "TESTI3",
				false);

		ObjectMapper mapper = new ObjectMapper();
		String newTicketAsJson = mapper.writeValueAsString(newTicket);

		MvcResult result = this.mockMvc.perform(post("/api/tickets")
				.contentType(MediaType.APPLICATION_JSON)
				.content(newTicketAsJson))
				.andReturn();

		if (result.getResponse().getStatus() != 200) {
			System.out.println(result.getResponse().getContentAsString());
		}
	}

}
