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

import SKRUM.TicketGuru.domain.Transaction;
import SKRUM.TicketGuru.domain.TransactionRepository;

@RestController
public class RestTransactionController {

	@Autowired
	private TransactionRepository trRepo;

	// Hakee kaikki entiteetit taulusta ja palauttaa ne koodilla 200
	@GetMapping("/api/transactions")
	public ResponseEntity<Iterable<Transaction>> transactionListRest() {
		Iterable<Transaction> transactions = trRepo.findAll();
		return new ResponseEntity<Iterable<Transaction>>(transactions, HttpStatus.OK);

	}

	// Etsii annettulla ID:llä tikettiä, palauttaa löydetyn entiteetin ja koodin 200
	// tai tyhjän bodyn koodilla 404
	@GetMapping("/api/transactions/{id}")
	public ResponseEntity<Optional<Transaction>> findTransactionRest(@PathVariable("id") Long id) {
		Optional<Transaction> transaction = trRepo.findById(id);
		if (transaction.isPresent()) {
			return new ResponseEntity<Optional<Transaction>>(transaction, HttpStatus.OK);
		} else {
			return new ResponseEntity<Optional<Transaction>>(HttpStatus.NOT_FOUND);
		}
	}

	// Luo tauluun uuden entiteetin ja palauttaa sen bodyssä koodilla 201, tyhjä/ ei
	// JSON:ia sisältävä requestbody palauttaa automaattisesti koodin 400
	@PostMapping("/api/transactions")
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction newTransaction) {
		return new ResponseEntity<Transaction>(trRepo.save(newTransaction), HttpStatus.CREATED);
	}

	// Muokkaa annetun ID:n entiteetin, palauttaa muokatun entiteetin ja koodin 200 tai
	// koodin 404, jos entiteettiä ei löydy
	@PutMapping("/api/transactions/{id}")
	public ResponseEntity<Transaction> editTransaction(@RequestBody Transaction editedTransaction, @PathVariable("id") Long id) {
		if (trRepo.findById(id).isPresent()) {
			editedTransaction.setId(id);
			return new ResponseEntity<Transaction>(trRepo.save(editedTransaction), HttpStatus.OK);
		} else {
			return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
		}
	}

	// Poistaa annetun ID:n entiteetin, palauttaa jäljellä olevat entiteetit ja koodin 200
	// tai koodin 404 jos entiteettiä ei löydy
	@DeleteMapping("/api/transactions/{id}")
	public ResponseEntity<Iterable<Transaction>> deleteTransaction(@PathVariable("id") Long id) {
		Optional<Transaction> targetTransaction = trRepo.findById(id);

		if (targetTransaction.isPresent()) {
			trRepo.delete(targetTransaction.get());
			return new ResponseEntity<Iterable<Transaction>>(trRepo.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Iterable<Transaction>>(HttpStatus.NOT_FOUND);
		}
	}

}
