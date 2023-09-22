package SKRUM.TicketGuru.web;

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
public class RestTicketController {

	@Autowired
	private TicketRepository tRepo;
	
	//Hakee kaikki Tiketit taulusta ja palauttaa ne koodilla 200
	@GetMapping("/api/tickets")
	public ResponseEntity<Iterable<Ticket>> ticketListRest() {
		Iterable<Ticket> tickets = tRepo.findAll();
		return new ResponseEntity<Iterable<Ticket>>(tickets, HttpStatus.OK);
		
	}
	
	//Etsii annettulla ID:llä tikettiä, palauttaa löydetyn tiketin ja koodin 200 tai tyhjän bodyn koodilla 404
	@GetMapping("/api/tickets/{id}")
	public ResponseEntity<Optional<Ticket>> findTicketRest(@PathVariable("id") Long id) {
		Optional<Ticket> ticket = tRepo.findById(id);
		if(ticket.isPresent()) {
			return new ResponseEntity<Optional<Ticket>>(ticket, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Optional<Ticket>>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Luo tauluun uuden tiketin ja palauttaa sen bodyssä koodilla 201, tyhjä/ ei JSON:ia sisältävä requestbody palauttaa automaattisesti koodin 400
	@PostMapping("/api/tickets")
	public ResponseEntity<Ticket> addTicket(@RequestBody Ticket newTicket) {
		return new ResponseEntity<Ticket>(tRepo.save(newTicket), HttpStatus.CREATED);
	}
	
	//Muokkaa annetun ID:n tikettiä, palauttaa muokatun tiketin ja koodin 200 tai koodin 404, jos tikettiä ei löydy
	@PutMapping("/api/tickets/{id}")
	public ResponseEntity<Ticket> editTicket(@RequestBody Ticket editedTicket, @PathVariable("id") Long id) {
		if(tRepo.findById(id).isPresent()) {
			editedTicket.setId(id);
			return new ResponseEntity<Ticket>(tRepo.save(editedTicket), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Poistaa annetun ID:n tiketin, palauttaa jäljellä olevat tiketit ja koodin 200 tai koodin 404 jos tikettiä ei löydy
	@DeleteMapping("/api/tickets/{id}")
	public ResponseEntity<Iterable<Ticket>> deleteTicket(@PathVariable("id") Long id) {
		Optional<Ticket> targetTicket = tRepo.findById(id);
		
		if(targetTicket.isPresent()) {
			tRepo.delete(targetTicket.get());
			return new ResponseEntity<Iterable<Ticket>>(tRepo.findAll(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Iterable<Ticket>>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
