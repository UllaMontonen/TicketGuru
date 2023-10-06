package SKRUM.TicketGuru.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import SKRUM.TicketGuru.domain.TicketType;
import SKRUM.TicketGuru.domain.TicketTypeRepository;

import java.util.Optional;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/tickettypes")
public class TicketTypeController {

    @Autowired
    private TicketTypeRepository ttRepo;

    //Palauttaa kaikkiin MethodArguementNotValidException heittoihin, response entityn jossa
  	//lukee virheilmoitus. Kyseinen heitto tulee @Valid annotaation virheistä
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationExcepetion(MethodArgumentNotValidException e) {
		return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}
    
    // Hakee kaikki tikettityypit taulusta ja palauttaa ne koodilla 200
    @GetMapping
    public ResponseEntity<Iterable<TicketType>> tickettypesListRest() {
        Iterable<TicketType> ticketTypes = ttRepo.findAll();
        return new ResponseEntity<Iterable<TicketType>>(ticketTypes, HttpStatus.OK);
    }

    // Etsii annetulla ID:llä tikettityyppiä, palauttaa löydetyn tikettityypin ja koodin 200 
    // tai tyhjän bodyn koodilla 404, jos ID:llä ei löydy mitään
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TicketType>> findTicketTypeRest(@PathVariable("id") Long id) {
        Optional<TicketType> ticketTypeOptional = ttRepo.findById(id);
        if (ticketTypeOptional.isPresent()) {
            return new ResponseEntity<Optional<TicketType>>(ticketTypeOptional, HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<TicketType>>(HttpStatus.NOT_FOUND);
        }
    }

    // Luo tauluun uuden tikettityypin ja palauttaa sen bodyssä koodilla 201,
    // tyhjä/ei JSON:ia sisältävä requestbody palauttaa automaattisesti koodin 404
    @PostMapping
    public ResponseEntity<TicketType> addTicketType(@Valid @RequestBody TicketType newTicketType) {
        ttRepo.save(newTicketType);
        return new ResponseEntity<TicketType>(HttpStatus.CREATED);
    }

    // Muokkaa annetun ID:n perusteella tikettityyppiä, palauttaa muokatun tikettityypin ja 
    // koodin 200 tai koodin 404 jos tikettityyppiä ei annetulla ID:llä löydy
    @PutMapping("/{id}")
    public ResponseEntity<TicketType> updateTicketType(@Valid @PathVariable("id") Long id,
            @RequestBody TicketType editedTicketType) {
        if (!ttRepo.existsById(id)) {
            return new ResponseEntity<TicketType>(HttpStatus.NOT_FOUND);
        }
        editedTicketType.setId(id);
        ttRepo.save(editedTicketType);
        return new ResponseEntity<TicketType>(HttpStatus.OK);
    }

    // Poistaa annetun ID:n perusteella tikettityypin, palauttaa jäljellä olevat tikettityypit
    // ja koodin 200 tai palauttaa koodin 404 jos tikettityyppiä ei annetulla ID:llä löydy
    @DeleteMapping("/{id}")
    public ResponseEntity<Iterable<TicketType>> deleteTicketType(@PathVariable("id") Long id) {
        if (!ttRepo.existsById(id)) {
            return new ResponseEntity<Iterable<TicketType>>(HttpStatus.NOT_FOUND);
        }
        ttRepo.deleteById(id);
        return new ResponseEntity<Iterable<TicketType>>(HttpStatus.OK);
    }
}