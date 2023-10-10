package SKRUM.TicketGuru.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import SKRUM.TicketGuru.domain.*;
import jakarta.validation.Valid;

@RestController
@Validated
public class RestEventController {
	
	@Autowired
	private EventRepository eRepo;

	// Palauttaa kaikkiin MethodArguementNotValidException heittoihin, response
	// entityn jossa
	// lukee virheilmoitus. Kyseinen heitto tulee @Valid annotaation virheistä
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleConstraintViolationExcepetion(MethodArgumentNotValidException e) {
		return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
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
	public ResponseEntity<Event> addEvent(@Valid @RequestBody Event newEvent) {
		return new ResponseEntity<Event>(eRepo.save(newEvent), HttpStatus.CREATED);
	}

	// Muokkaa annetun ID:n eventtiä, palauttaa muokatun eventin ja koodin 200
	// tai koodin 404, jos eventtiä ei löydy
	@PutMapping("/api/events/{id}")
	public ResponseEntity<Event> editEvent(@Valid @RequestBody Event editedEvent, @PathVariable("id") Long id) {
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

	// *** Alla koodi, jota ei enää tarvita muuttuneen ostotapahtuman logiikan
	// myötä.***

	// private String generateUniqueTicketCode(Event event) {
	// // Antaa joka lipulle uniikin koodin muodossa: "EVT-{eventId}-{aika}"
	// return "EVT-" + event.getId() + "-" + System.currentTimeMillis();
	// }

	// Ostotaphtuman luominen jossa eventId määrittää tapahtuman ja ticketTypeId
	// määrittää lipputyypin, bodyyn syötetään:
	// {
	// "ticketAmount: " ",
	// "customerId": " "
	// }
	// Kommenttina varastoon varmuuden vuoksi

}
