package SKRUM.TicketGuru.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import SKRUM.TicketGuru.domain.EventRepository;
import SKRUM.TicketGuru.domain.Event;

@Controller
public class TicketGuruController {

	@Autowired
	private EventRepository repository;
	
	
	// *** EVENT CONTROLLER ***
	// EVENT: show all events
	@GetMapping({"/eventlist"})
	public String eventlist(Model model) {
		model.addAttribute("events", repository.findAll());
		return "eventlist";
	}
	// EVENT: add new event
	@GetMapping("/eventlist/add")
	public String addEvent(Model model) {
		model.addAttribute("event", new Event());
		return "addevent";
	}
	// EVENT: save new event
	@PostMapping("/eventlist/save")
	public String save(Event event) {
		repository.save(event);
		return "redirect:eventlist";
	}
	// EVENT: delete event
	@GetMapping("/eventlist/delete/{id}")
	public String deleteEvent(@PathVariable("id") long id, Model model) {
		repository.deleteById(id);
		return "redirect:.../eventlist";
	}
	// EVEMT: edit event
	@GetMapping("/eventlist/edit/{id}")
	public String editEvent(@PathVariable("id") Long id, Model model) {
		model.addAttribute("event", repository.findById(id));
	return "editevent";
	}
	// *** END OF EVENT CONTROLLER ***
	
}
