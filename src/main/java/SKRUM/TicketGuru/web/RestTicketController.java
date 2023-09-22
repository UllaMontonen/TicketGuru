package SKRUM.TicketGuru.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
