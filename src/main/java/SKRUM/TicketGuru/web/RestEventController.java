package SKRUM.TicketGuru.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	// get a list of events
	@GetMapping("/api/events")
	public List<Event> eventListRest() {
		return (List<Event>) eRepo.findAll();
	}
	
	// show event based on the given id
	@GetMapping("/api/events/{id}")
	public Optional<Event> findEventRest(@PathVariable("id") Long id) {
		return eRepo.findById(id);
	}
	
	// create a new event
	@PostMapping("/api/events")
	public Event newEvent(@RequestBody Event newEvent) {
		return eRepo.save(newEvent);
	}
	
	// update an existing event based on given id
	@PutMapping("/api/events/{id}")
	public Event editEvent(@RequestBody Event editedEvent, @PathVariable("id") Long id) {
		editedEvent.setId(id);
		return eRepo.save(editedEvent);
	}
	
	// delete an event based on given id
	@DeleteMapping("/api/events/{id}")
	public List<Event> deleteEvent(@PathVariable("id") Long id) {
		eRepo.deleteById(id);
		return(List<Event>) eRepo.findAll();
	}
	
}
