package SKRUM.TicketGuru.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import SKRUM.TicketGuru.domain.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@Validated
public class RestCustomerController {

	@Autowired
	private CustomerRepository cRepo;
	@Autowired
	private TransactionRepository trRepo;

	// Hakee kaikki customerit taulusta ja palauttaa ne koodilla 200
	@GetMapping("/api/customers")
	public ResponseEntity<Iterable<Customer>> customerListRest() {
		Iterable<Customer> customers = cRepo.findAll();
		return new ResponseEntity<Iterable<Customer>>(customers, HttpStatus.OK);
	}

	// Etsii annettulla ID:llä customeria, palauttaa löydetyn customerin ja koodin
	// 200
	// tai tyhjän bodyn koodilla 404
	@GetMapping("/api/customers/{id}")
	public ResponseEntity<Optional<Customer>> findCustomerRest(@PathVariable("id") Long id) {
		Optional<Customer> customer = cRepo.findById(id);
		if (customer.isPresent()) {
			return new ResponseEntity<Optional<Customer>>(customer, HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("Customer with ID " + id + " not found");
		}
	}

	// Luo tauluun uuden customerin ja palauttaa sen bodyssä koodilla 201, tyhjä/ ei
	// JSON:ia
	// sisältävä requestbody palauttaa automaattisesti koodin 400
	@PostMapping("/api/customers")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer newCustomer) {
		return new ResponseEntity<Customer>(cRepo.save(newCustomer), HttpStatus.CREATED);

	}

	// Muokkaa annetun ID:n customeria, palauttaa muokatun customerin ja koodin 200
	// tai koodin 404, jos customeria ei löydy
	@PutMapping("/api/customers/{id}")
	public ResponseEntity<Customer> editCustomer(@Valid @RequestBody Customer editedCustomer,
			@PathVariable("id") Long id) {
		if (cRepo.findById(id).isPresent()) {
			editedCustomer.setId(id);
			return new ResponseEntity<Customer>(cRepo.save(editedCustomer), HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("Customer with ID " + id + " not found");
		}
	}

	// Poistaa annetun ID:n customerin, palauttaa jäljellä olevat customerin ja
	// koodin 200
	// tai koodin 404 jos customeria ei löydy
	@DeleteMapping("/api/customers/{id}")
	public ResponseEntity<Iterable<Customer>> deleteCustomer(@PathVariable("id") Long id) {
		Optional<Customer> targetCustomer = cRepo.findById(id);

		if (targetCustomer.isPresent()) {
			cRepo.delete(targetCustomer.get());
			return new ResponseEntity<Iterable<Customer>>(cRepo.findAll(), HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("Customer with ID " + id + " not found");
		}
	}

	// Palauttaa annetun customer entityn kaikki transactionit tai virhekoodin 404,
	// jos annetulla ID:llä
	// ei löydy customeria kannasta
	@GetMapping("/api/customers/{id}/transactions")
	public ResponseEntity<List<Transaction>> findCustomersTransactions(@PathVariable("id") Long id) {
		Optional<Customer> customer = cRepo.findById(id);

		if (customer.isPresent()) {
			return new ResponseEntity<List<Transaction>>(trRepo.findByCustomer(customer.get()), HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("Customer with ID " + id + " not found");
		}
	}

}