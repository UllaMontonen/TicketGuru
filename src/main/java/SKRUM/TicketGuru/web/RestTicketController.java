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
public class RestTicketController {

	@Autowired
	private TicketRepository tRepo;
	
	@GetMapping("/api/tickets")
	public List<Ticket> ticketListRest() {
		return(List<Ticket>)tRepo.findAll();
	}
	
	@GetMapping("/api/tickets/{id}")
	public Optional<Ticket> findTicketRest(@PathVariable("id") Long id) {
		return tRepo.findById(id);
	}
	
	@PostMapping("/api/tickets")
	public Ticket newTicket(@RequestBody Ticket newTicket) {
		return tRepo.save(newTicket);
	}
	
	@PutMapping("/api/tickets/{id}")
	public Ticket editTicket(@RequestBody Ticket editedTicket, @PathVariable("id") Long id) {
		editedTicket.setId(id);
		return tRepo.save(editedTicket);
	}
	
	@DeleteMapping("/api/tickets/{id}")
	public List<Ticket> deleteTicket(@PathVariable("id") Long id) {
		tRepo.deleteById(id);
		return(List<Ticket>) tRepo.findAll();
	}
	
	
}
