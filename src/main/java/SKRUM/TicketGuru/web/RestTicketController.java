package SKRUM.TicketGuru.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import SKRUM.TicketGuru.domain.*;
import jakarta.validation.Valid;

@RestController
@Validated
public class RestTicketController {

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

	private final TicketSaleMapper tMapper;

	@Autowired
	public RestTicketController(TicketSaleMapper tMapper) {
		this.tMapper = tMapper;
	}

	// Hakee kaikki Tiketit taulusta ja palauttaa ne koodilla 200
	@GetMapping("/api/tickets")
	public ResponseEntity<Iterable<Ticket>> ticketListRest() {
		Iterable<Ticket> tickets = tRepo.findAll();
		return new ResponseEntity<Iterable<Ticket>>(tickets, HttpStatus.OK);

	}

	// Etsii annettulla ID:llä tikettiä, palauttaa löydetyn tiketin ja koodin 200
	// tai tyhjän bodyn koodilla 404
	@GetMapping("/api/tickets/{id}")
	public ResponseEntity<Optional<Ticket>> findTicketRest(@PathVariable("id") Long id) {
		Optional<Ticket> ticket = tRepo.findById(id);
		if (ticket.isPresent()) {
			return new ResponseEntity<Optional<Ticket>>(ticket, HttpStatus.OK);
		} else {
			return new ResponseEntity<Optional<Ticket>>(HttpStatus.NOT_FOUND);
		}
	}

	// Korvaava Post löytyy alempaa
	//
	// Luo tauluun uuden tiketin ja palauttaa sen bodyssä koodilla 201, tyhjä/ ei
	// JSON:ia sisältävä requestbody palauttaa automaattisesti koodin 400
	/*
	 * @PostMapping("/api/tickets") public ResponseEntity<Ticket>
	 * addTicket(@RequestBody Ticket newTicket) { return new
	 * ResponseEntity<Ticket>(tRepo.save(newTicket), HttpStatus.CREATED); }
	 */

	// Muokkaa annetun ID:n tikettiä, palauttaa muokatun tiketin ja koodin 200 tai
	// koodin 404, jos tikettiä ei löydy
	@PutMapping("/api/tickets/{id}")
	public ResponseEntity<Ticket> editTicket(@Valid @RequestBody Ticket editedTicket, @PathVariable("id") Long id) {
		if (tRepo.findById(id).isPresent()) {
			editedTicket.setId(id);
			return new ResponseEntity<Ticket>(tRepo.save(editedTicket), HttpStatus.OK);
		} else {
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}
	}

	// Poistaa annetun ID:n tiketin, palauttaa jäljellä olevat tiketit ja koodin 200
	// tai koodin 404 jos tikettiä ei löydy
	@DeleteMapping("/api/tickets/{id}")
	public ResponseEntity<Iterable<Ticket>> deleteTicket(@PathVariable("id") Long id) {
		Optional<Ticket> targetTicket = tRepo.findById(id);

		if (targetTicket.isPresent()) {
			tRepo.delete(targetTicket.get());
			return new ResponseEntity<Iterable<Ticket>>(tRepo.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Iterable<Ticket>>(HttpStatus.NOT_FOUND);
		}
	}

	//Kommenttien ja validaation tarkempi lisäys myöhemmin
	@PostMapping("/api/tickets")
	public ResponseEntity<Iterable<Ticket>> ticketSale(@Valid @RequestBody TicketSaleDTO ticketSale) {
		HttpHeaders header = new HttpHeaders();
		List<Ticket> boughtTickets = new ArrayList<>();
		double price = 0.0;

		if (ticketSale.getCustomerId() == null && ticketSale.getCustomerName() != null
				&& ticketSale.getCustomerEmail() != null) {
			Customer customer = cRepo.save(tMapper.DtoToCustomerByName(ticketSale));

			for (TicketDTO ticketDto : ticketSale.getTicketsDTO()) {
				for (int i = 0; i < ticketDto.getTicketAmount(); i++) {
					Optional<Event> event = eRepo.findById(ticketDto.getEventId());
					Optional<TicketType> ticketType = ttRepo.findById(ticketDto.getTicketTypeId());
					if (ticketType.isEmpty()) {
						header.add("ERROR", "No tickettype found with id " + ticketDto.getTicketTypeId());
						return new ResponseEntity<Iterable<Ticket>>(header, HttpStatus.BAD_REQUEST);
					} 
					else if (event.isEmpty()) {
						header.add("ERROR", "No event found with id " + ticketDto.getEventId());
						return new ResponseEntity<Iterable<Ticket>>(header, HttpStatus.BAD_REQUEST);
					}
					else if (event.get().getId() != ticketType.get().getEvent().getId()) {
						header.add("ERROR", "TicketType, doesn't match given Event");
						return new ResponseEntity<Iterable<Ticket>>(header, HttpStatus.BAD_REQUEST);
					}
					else {
						String ticketCode = generateUniqueTicketCode(event.get());
						boughtTickets.add(new Ticket(event.get(), ticketType.get(), null, ticketCode, true));
					}
				}
			}
			price = countTotalSumForSale(ticketSale.getTicketsDTO());
			Transaction transaction = trRepo.save(new Transaction(new Date(), price, customer));
			boughtTickets = addTransactionToTickets(boughtTickets, transaction);
			return new ResponseEntity<Iterable<Ticket>>(tRepo.saveAll(boughtTickets), HttpStatus.OK);

		} else if(ticketSale.getCustomerId() == null && ticketSale.getCustomerEmail() == null || ticketSale.getCustomerName() == null) {
			header.add("ERROR", "No ID given and email and/or name is missing");
			return new ResponseEntity<Iterable<Ticket>>(header, HttpStatus.BAD_REQUEST);
		}
		
		else if (tMapper.DtoToCustomerById(ticketSale).isPresent()) {
			Customer customer = tMapper.DtoToCustomerById(ticketSale).get();

			for (TicketDTO ticketDto : ticketSale.getTicketsDTO()) {
				for (int i = 0; i < ticketDto.getTicketAmount(); i++) {
					Optional<Event> event = eRepo.findById(ticketDto.getEventId());
					Optional<TicketType> ticketType = ttRepo.findById(ticketDto.getTicketTypeId());
					if (ticketType.isEmpty()) {
						header.add("ERROR", "No tickettype found with id " + ticketDto.getTicketTypeId());
						return new ResponseEntity<Iterable<Ticket>>(header, HttpStatus.BAD_REQUEST);
					} 
					else if (event.isEmpty()) {
						header.add("ERROR", "No event found with id " + ticketDto.getEventId());
						return new ResponseEntity<Iterable<Ticket>>(header, HttpStatus.BAD_REQUEST);
					} 
					else if (event.get().getId() != ticketType.get().getEvent().getId()) {
						header.add("ERROR", "TicketType, doesn't match given Event");
						return new ResponseEntity<Iterable<Ticket>>(header, HttpStatus.BAD_REQUEST);
					}
					else {
						String ticketCode = generateUniqueTicketCode(event.get());
						boughtTickets.add(new Ticket(event.get(), ticketType.get(), null, ticketCode, true));
					}
				}
			}
			price = countTotalSumForSale(ticketSale.getTicketsDTO());
			Transaction transaction = trRepo.save(new Transaction(new Date(), price, customer));
			boughtTickets = addTransactionToTickets(boughtTickets, transaction);
			return new ResponseEntity<Iterable<Ticket>>(tRepo.saveAll(boughtTickets), HttpStatus.OK);

		} else {
			header.add("ERROR", "No Customer found with given ID");
			return new ResponseEntity<Iterable<Ticket>>(header, HttpStatus.BAD_REQUEST);
		}
	}

	private String generateUniqueTicketCode(Event event) {
		// Antaa joka lipulle uniikin koodin muodossa: "EVT-{eventId}-{aika}"
		return "EVT-" + event.getId() + "-" + System.currentTimeMillis();
	}

	//Laskee transactionin summan ja palauttaa sen pyöristettynä kahden desimaalin tarkkuudella
	private Double countTotalSumForSale(List<TicketDTO> ticketsDto) {
		Double sum = 0.0;

		for (TicketDTO ticketSale : ticketsDto) {
			Optional<TicketType> ticketType = ttRepo.findById(ticketSale.getTicketTypeId());
			sum += ticketType.get().getPrice() * ticketSale.getTicketAmount();
		}
		return Precision.round(sum, 2);
	}
	//Lisää listan jokaiseen tikettiin transactionin ja palauttaa päivitetyn listan
	private List<Ticket> addTransactionToTickets(List<Ticket> tickets, Transaction transaction) {
		for (Ticket ticket : tickets) {
			ticket.setTransaction(transaction);
		}
		return tickets;
	}

}
