package SKRUM.TicketGuru.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import SKRUM.TicketGuru.domain.Event;
import SKRUM.TicketGuru.domain.EventRepository;
import SKRUM.TicketGuru.domain.TicketType;
import SKRUM.TicketGuru.domain.TicketTypeRepository;
import jakarta.validation.Valid;

@RestController
public class RestTicketTypeController {

	@Autowired
	private EventRepository eRepo;
	@Autowired
	private TicketTypeRepository ttRepo;
	
	
	// Palauttaa kaikkiin MethodArguementNotValidException heittoihin, response
	// entityn jossa
	// lukee virheilmoitus. Kyseinen heitto tulee @Valid annotaation virheistä
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleConstraintViolationExcepetion(MethodArgumentNotValidException e) {
		return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	// Hakee eventint kaikki tickettypet kannasta ja palauttaa listan tai koodin 404
	// Jos eventtiä ei löydy kannasta
	@GetMapping("api/events/{id}/tickettypes")
	private ResponseEntity<List<TicketType>> findTicketTypesForEvent(@PathVariable("id") Long id) {
		Optional<Event> event = eRepo.findById(id);

		if (event.isPresent()) {
			List<TicketType> ticketTypes = ttRepo.findByEvent(event.get());
			return new ResponseEntity<List<TicketType>>(ticketTypes, HttpStatus.OK);
		} else {
			HttpHeaders header = new HttpHeaders();
			header.add("ERROR", "Event with id " + id + " not found");
			return new ResponseEntity<List<TicketType>>(header, HttpStatus.NOT_FOUND);
		}
	}
	
	// Lisää saadun tickettypen kantaan annettulle eventille ja palauttaa sen tai
	// koodin 400
	// Jos eventtiä ei löydy kannasta
	@PostMapping("api/events/{id}/tickettypes")
	private ResponseEntity<TicketType> createTicketTypeForEvent(@PathVariable("id") Long id,
			@Valid @RequestBody TicketType newTicketType) {
		Optional<Event> event = eRepo.findById(id);

		if (event.isPresent()) {
			newTicketType.setEvent(event.get());
			return new ResponseEntity<TicketType>(ttRepo.save(newTicketType), HttpStatus.CREATED);
		} else {
			HttpHeaders header = new HttpHeaders();
			header.add("ERROR", "Event with id " + id + " not found");
			return new ResponseEntity<TicketType>(header, HttpStatus.BAD_REQUEST);
		}
	}
}
