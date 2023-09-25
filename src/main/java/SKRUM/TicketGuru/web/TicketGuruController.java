package SKRUM.TicketGuru.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import SKRUM.TicketGuru.domain.*;

@Controller
public class TicketGuruController {

	@Autowired
	private EventRepository eRepo;
	
	@Autowired
	private CustomerRepository cRepo;
	
	
	// *** EVENT CONTROLLER ***
	// EVENT: show all events
	@GetMapping({"/eventlist"})
	public String eventlist(Model model) {
		model.addAttribute("events", eRepo.findAll());
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
		eRepo.save(event);
		return "redirect:eventlist";
	}
	// EVENT: delete event
	@GetMapping("/eventlist/delete/{id}")
	public String deleteEvent(@PathVariable("id") long id, Model model) {
		eRepo.deleteById(id);
		return "redirect:.../eventlist";
	}
	// EVEMT: edit event
	@GetMapping("/eventlist/edit/{id}")
	public String editEvent(@PathVariable("id") Long id, Model model) {
		model.addAttribute("event", eRepo.findById(id));
	return "editevent";
	}
	// *** END OF EVENT CONTROLLER ***
	
	
	
	
	// *** CUSTOMER CONTROLLER ***
	// CUSTOMER: show all customers
	@GetMapping({"/customerlist"})
	public String customerlist(Model model) {
		model.addAttribute("customer", cRepo.findAll());
		return "customerlist";
	}
	// CUSTOMER: add new customer
	@GetMapping("/customerlist/add")
	public String addCustomer(Model model) {
		model.addAttribute("customer", new Event());
		return "addcustomer";
	}
	// CUSTOMER: save new customer
	@PostMapping("/customerlist/save")
	public String save(Customer customer) {
		cRepo.save(customer);
		return "redirect:customerlist";
	}
	// CUSTOMER: delete customer
	@GetMapping("/customerlist/delete/{id}")
	public String deleteCustomer(@PathVariable("id") long id, Model model) {
		cRepo.deleteById(id);
		return "redirect:.../customerlist";
	}
	// CUSTOMER: edit customer
	@GetMapping("/customerlist/edit/{id}")
	public String editCustomer(@PathVariable("id") Long id, Model model) {
		model.addAttribute("customer", cRepo.findById(id));
	return "editcustomer";
	}
	// *** END OF CUSTOMER CONTROLLER ***
	
}
