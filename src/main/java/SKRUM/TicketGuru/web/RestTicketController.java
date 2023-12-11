package SKRUM.TicketGuru.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import SKRUM.TicketGuru.domain.*;
import SKRUM.TicketGuru.domain.exceptions.CustomerNotFoundException;
import SKRUM.TicketGuru.domain.exceptions.EventNotFoundException;
import SKRUM.TicketGuru.domain.exceptions.TicketTypeNotFoundException;
import jakarta.persistence.EntityNotFoundException;
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
			throw new EntityNotFoundException("Ticket with ID " + id + " not found");
		}
	}

	// Etsii annetulla koodilla tiketin tiedot, palauttaa löydetyn tiketin ja koodin
	// 200
	@GetMapping("/api/tickets/check/{ticketcode}")
	public ResponseEntity<Optional<Ticket>> getTicketByCode(@PathVariable String ticketcode) {
		Optional<Ticket> ticket = tRepo.findByCode(ticketcode);
		if (ticket.isPresent()) {
			return new ResponseEntity<Optional<Ticket>>(ticket, HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("Ticket with Code " + ticketcode + " not found");
		}
	}

	// Etsii annetulla koodilla tikettiä, tarkistaa onko se käytetty, jos ei niin
	// merkkaa sen käytetyksi ja palauttaa vastauksena tiketin tai koodin 404
	@PatchMapping("/api/tickets/markused/{ticketcode}")
	public ResponseEntity<Optional<Ticket>> markTicketUsedByCode(@PathVariable String ticketcode) {
		Optional<Ticket> ticket = tRepo.findByCode(ticketcode);
		if (ticket.isPresent()) {
			if (ticket.get().isVerified()) {
				throw new RuntimeException("Ticket is already used");
			} else {
				ticket.get().setVerified(true);
				tRepo.save(ticket.get());
				return new ResponseEntity<Optional<Ticket>>(ticket, HttpStatus.OK);
			}
		} else {
			throw new EntityNotFoundException("Ticket with Code " + ticketcode + " not found");
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
			throw new EntityNotFoundException("Ticket with ID " + id + " not found");
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
			throw new EntityNotFoundException("Ticket with ID " + id + " not found");
		}
	}
	

	// Ostotapahtuma, luo tiketteja, transaction tapahtumia ja tarvittaessa ostajan.
	// saa syötteenä customerId:n tai customerName ja customerEmail ja listan
	// haluttujen lippujen määrästä,
	// tapahtumasta ja lipputyypistä
	@PostMapping("/api/tickets")
	public ResponseEntity<Iterable<Ticket>> ticketSale(@RequestBody TicketSaleDTO ticketSale) {
		List<Ticket> boughtTickets = new ArrayList<>();
		double price = 0.0;
		if (ticketSale.getCustomerId() == null && ticketSale.getCustomerName() != null
				&& ticketSale.getCustomerEmail() != null) {
			Customer customer = cRepo.save(tMapper.DtoToCustomerByName(ticketSale));

			for (TicketDTO ticketDto : ticketSale.getTicketsDTO()) {
				if (ticketDto.getTicketAmount() < 1) {
					throw new IllegalArgumentException("Ticket amount must be greater than 0");
				}
				for (int i = 0; i < ticketDto.getTicketAmount(); i++) {
					Optional<Event> event = eRepo.findById(ticketDto.getEventId());
					Optional<TicketType> ticketType = ttRepo.findById(ticketDto.getTicketTypeId());
					if (ticketType.isEmpty()) {
						throw new TicketTypeNotFoundException(
								"No tickettype found with id " + ticketDto.getTicketTypeId());
					} else if (event.isEmpty()) {
						throw new EventNotFoundException("No event found with id " + ticketDto.getEventId());
					} else if (event.get().getId() != ticketType.get().getEvent().getId()) {
						throw new RuntimeException("TicketType doesn't match the given Event");
					} else if (event.get().getTicketAmount() < 1) {
						throw new RuntimeException("Not enough tickets left for the event"); 
					} else {
						event.get().setTicketAmount(event.get().getTicketAmount() - 1);
						eRepo.save(event.get());
						String ticketCode = generateUniqueTicketCode();
						boughtTickets.add(new Ticket(event.get(), ticketType.get(), null, ticketCode, false));
					}
				}
			}
			price = countTotalSumForSale(ticketSale.getTicketsDTO());
			Transaction transaction = trRepo.save(new Transaction(new Date(), price, customer));
			boughtTickets = addTransactionToTickets(boughtTickets, transaction);
			return new ResponseEntity<Iterable<Ticket>>(tRepo.saveAll(boughtTickets), HttpStatus.OK);
		} else if (tMapper.DtoToCustomerById(ticketSale).isPresent()) {
			Customer customer = tMapper.DtoToCustomerById(ticketSale).get();
			for (TicketDTO ticketDto : ticketSale.getTicketsDTO()) {
				if (ticketDto.getTicketAmount() < 1) {
					throw new IllegalArgumentException("Ticket amount must be greater than 0");
				}
				for (int i = 0; i < ticketDto.getTicketAmount(); i++) {
					Optional<Event> event = eRepo.findById(ticketDto.getEventId());
					Optional<TicketType> ticketType = ttRepo.findById(ticketDto.getTicketTypeId());
					if (ticketType.isEmpty()) {
						throw new TicketTypeNotFoundException(
								"No tickettype found with id " + ticketDto.getTicketTypeId());
					} else if (event.isEmpty()) {
						throw new EventNotFoundException("No event found with id " + ticketDto.getEventId());
					} else if (event.get().getId() != ticketType.get().getEvent().getId()) {
						throw new RuntimeException("TicketType doesn't match the given Event");
					} else if (event.get().getTicketAmount() < 1) {
						throw new RuntimeException("Not enough tickets left for the event"); 
					} else {
						event.get().setTicketAmount(event.get().getTicketAmount() - 1);
						eRepo.save(event.get());
						String ticketCode = generateUniqueTicketCode();
						boughtTickets.add(new Ticket(event.get(), ticketType.get(), null, ticketCode, false));
					}
				}
			}
			price = countTotalSumForSale(ticketSale.getTicketsDTO());
			Transaction transaction = trRepo.save(new Transaction(new Date(), price, customer));
			boughtTickets = addTransactionToTickets(boughtTickets, transaction);
			return new ResponseEntity<Iterable<Ticket>>(tRepo.saveAll(boughtTickets), HttpStatus.OK);
		} else if (tMapper.DtoToCustomerById(ticketSale).isEmpty()) {
			throw new CustomerNotFoundException("No Customer found with the given ID");
		} else {
			throw new RuntimeException("No ID given and email and/or name is missing");
		}
	}
	
	@PostMapping("/api/generatetickets")
	public ResponseEntity<List<Ticket>> generateUnsoldTickets(@RequestBody GenerateTicketsDTO generateTicketsDto) {
		Optional<Event> event = eRepo.findById(generateTicketsDto.getEventId());
		Optional<TicketType> ticketType = ttRepo.findById(generateTicketsDto.getTicketTypeId());
		List<Ticket> tickets = new ArrayList<>();
		
		if(event.isPresent() && ticketType.isPresent() && ticketType.get().getEvent().getId() == event.get().getId()) {
			int amount = event.get().getTicketAmount();
			event.get().setTicketAmount(0);
			eRepo.save(event.get());
			
			for(int i = 0; i < amount; i++) {
				tickets.add(tRepo.save(new Ticket(event.get(), ticketType.get(), null, generateUniqueTicketCode(), false)));
			}
			
			return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
		}
		else {
			throw new RuntimeException("No event or tickettype found with ID, or event doesnt match tickettype");
		}
	}

	private String generateUniqueTicketCode() {
		// UUID mukainen täysin random koodi tiketille
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	// Laskee transactionin summan ja palauttaa sen pyöristettynä kahden desimaalin
	// tarkkuudella
	private Double countTotalSumForSale(List<TicketDTO> ticketsDto) {
		Double sum = 0.0;

		for (TicketDTO ticketSale : ticketsDto) {
			Optional<TicketType> ticketType = ttRepo.findById(ticketSale.getTicketTypeId());
			sum += ticketType.get().getPrice() * ticketSale.getTicketAmount();
		}
		return Precision.round(sum, 2);
	}

	// Lisää listan jokaiseen tikettiin transactionin ja palauttaa päivitetyn listan
	private List<Ticket> addTransactionToTickets(List<Ticket> tickets, Transaction transaction) {
		for (Ticket ticket : tickets) {
			ticket.setTransaction(transaction);
		}
		return tickets;
	}

}
