package SKRUM.TicketGuru.web;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import SKRUM.TicketGuru.domain.*;

@RestController
public class RestEventController {

	@Autowired
	private EventRepository eRepo;
	@Autowired
	private CustomerRepository cRepo;
	@Autowired
	private TicketTypeRepository ttRepo;
	@Autowired
	private TicketRepository tRepo;
	@Autowired
	private TransactionRepository trRepo;

	private final TransactionMapper tMapper;
	
	@Autowired
	public RestEventController(TransactionMapper tMapper) {
		this.tMapper = tMapper;
	}

	// Hakee kaikki eventit taulusta ja palauttaa ne koodilla 200
	@GetMapping("/api/events")
	public ResponseEntity<Iterable<Event>> eventListRest() {
		Iterable<Event> events = eRepo.findAll();
		return new ResponseEntity<Iterable<Event>>(events, HttpStatus.OK);
	}

	// Etsii annettulla ID:llä eventtiä, palauttaa löydetyn eventin ja koodin 200
	// tai tyhjän bodyn koodilla 404
	@GetMapping("/api/events/{id}")
	public ResponseEntity<Optional<Event>> findEventRest(@PathVariable("id") Long id) {
		Optional<Event> event = eRepo.findById(id);
		if (event.isPresent()) {
			return new ResponseEntity<Optional<Event>>(event, HttpStatus.OK);
		} else {
			return new ResponseEntity<Optional<Event>>(HttpStatus.NOT_FOUND);
		}
	}

	// Luo tauluun uuden eventin ja palauttaa sen bodyssä koodilla 201, tyhjä/ ei
	// JSON:ia
	// sisältävä requestbody palauttaa automaattisesti koodin 400
	@PostMapping("/api/events")
	public ResponseEntity<Event> addEvent(@RequestBody Event newEvent) {
		return new ResponseEntity<Event>(eRepo.save(newEvent), HttpStatus.CREATED);
	}

	// Muokkaa annetun ID:n eventtiä, palauttaa muokatun eventin ja koodin 200
	// tai koodin 404, jos eventtiä ei löydy
	@PutMapping("/api/events/{id}")
	public ResponseEntity<Event> editEvent(@RequestBody Event editedEvent, @PathVariable("id") Long id) {
		if (eRepo.findById(id).isPresent()) {
			editedEvent.setId(id);
			return new ResponseEntity<Event>(eRepo.save(editedEvent), HttpStatus.OK);
		} else {
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
		}
	}

	// Poistaa annetun ID:n eventin, palauttaa jäljellä olevat eventin ja koodin 200
	// tai koodin 404 jos customeria ei löydy
	@DeleteMapping("/api/events/{id}")
	public ResponseEntity<Iterable<Event>> deleteEvent(@PathVariable("id") Long id) {
		Optional<Event> targetEvent = eRepo.findById(id);

		if (targetEvent.isPresent()) {
			eRepo.delete(targetEvent.get());
			return new ResponseEntity<Iterable<Event>>(eRepo.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Iterable<Event>>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/api/events/{eventId}/tickettypes/{ticketTypeId}")
	public ResponseEntity<List<Ticket>> saleEvent(@RequestBody TransactionDTO transactionDto,
			@PathVariable("eventId") Long eventId, @PathVariable("ticketTypeId") Long ticketTypeId) {
		List<Ticket> boughtTickets = new ArrayList<>();
		Optional<Event> event = eRepo.findById(eventId);
		Optional<TicketType> ticketType = ttRepo.findById(ticketTypeId);

		if (event.isEmpty()) {
			return new ResponseEntity<List<Ticket>>(HttpStatus.BAD_REQUEST);
		} else if (ticketType.isEmpty()) {
			return new ResponseEntity<List<Ticket>>(HttpStatus.CONFLICT);
		} else {

			if (transactionDto.getCustomerId() == null) {
				Customer customer = tMapper.DtoToCustomerByName(transactionDto);
				cRepo.save(customer);
				Transaction transaction = new Transaction(new Date(),
						transactionDto.getTicketAmount() * ticketType.get().getPrice(), customer);
				trRepo.save(transaction);

				for (int i = 0; i < transactionDto.getTicketAmount(); i++) {
					boughtTickets
							.add(tRepo.save(new Ticket(event.get(), ticketType.get(), transaction, "ABC-" + i, true)));
				}

				return new ResponseEntity<List<Ticket>>(boughtTickets, HttpStatus.OK);

			}
			else if(tMapper.DtoToCustomerById(transactionDto).isPresent()) {
				
				Customer customer = tMapper.DtoToCustomerById(transactionDto).get();
				Transaction transaction = new Transaction(new Date(),
						transactionDto.getTicketAmount() * ticketType.get().getPrice(), customer);
				trRepo.save(transaction);
				
				for (int i = 0; i < transactionDto.getTicketAmount(); i++) {
					boughtTickets
							.add(tRepo.save(new Ticket(event.get(), ticketType.get(), transaction, "ABC-" + i, true)));
				}
				
				return new ResponseEntity<List<Ticket>>(boughtTickets, HttpStatus.OK);
				
			}
			else {
				return new ResponseEntity<List<Ticket>>(HttpStatus.FORBIDDEN);
			}

		}

	}

}
