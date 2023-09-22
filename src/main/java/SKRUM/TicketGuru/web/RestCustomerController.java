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
public class RestCustomerController {
	
	@Autowired
	private CustomerRepository cRepo;
	
	// get a list of customers
		@GetMapping("/api/customers")
		public List<Customer> customerListRest() {
			return (List<Customer>) cRepo.findAll();
		}
		
		// show customer based on the given id
		@GetMapping("/api/customers/{id}")
		public Optional<Customer> findCustomerRest(@PathVariable("id") Long id) {
			return cRepo.findById(id);
		}
		
		// create a new customer
		@PostMapping("/api/customers")
		public Customer newCustomer(@RequestBody Customer newCustomer) {
			return cRepo.save(newCustomer);
		}
		
		// update an existing customer based on given id
		@PutMapping("/api/customers/{id}")
		public Customer editCustomer(@RequestBody Customer editedCustomer, @PathVariable("id") Long id) {
			editedCustomer.setId(id);
			return cRepo.save(editedCustomer);
		}
		
		// delete an customer based on given id
		@DeleteMapping("/api/customers/{id}")
		public List<Customer> deleteCustomer(@PathVariable("id") Long id) {
			cRepo.deleteById(id);
			return(List<Customer>) cRepo.findAll();
		}

}
