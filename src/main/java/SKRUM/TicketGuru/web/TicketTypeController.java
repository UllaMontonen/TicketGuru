package SKRUM.TicketGuru.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import SKRUM.TicketGuru.domain.TicketType;
import SKRUM.TicketGuru.domain.TicketTypeRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/tickettypes")
public class TicketTypeController {

    @Autowired
    private TicketTypeRepository ttRepo;

    // Hakee kaikki tikettityypit
    @GetMapping
    public ResponseEntity<Iterable<TicketType>> tickettypesListRest() {
        Iterable<TicketType> ticketTypes = ttRepo.findAll();
        return new ResponseEntity<Iterable<TicketType>>(ticketTypes, HttpStatus.OK);
    }

    // Etsii annetulla ID:llä tikettityyppiä, palauttaa joko olemassa olevan
    // tikettityypin tai 404, jos ID:llä ei löydy mitään
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TicketType>> findTicketTypeRest(@PathVariable("id") Long id) {
        Optional<TicketType> ticketTypeOptional = ttRepo.findById(id);
        if (ticketTypeOptional.isPresent()) {
            return new ResponseEntity<Optional<TicketType>>(ticketTypeOptional, HttpStatus.OK);
        } else {
            return new ResponseEntity<Optional<TicketType>>(HttpStatus.NOT_FOUND);
        }
    }

    // Luo uuden tikettityypin
    @PostMapping
    public ResponseEntity<TicketType> addTicketType(@RequestBody TicketType newTicketType) {
        ttRepo.save(newTicketType);
        return new ResponseEntity<TicketType>(HttpStatus.CREATED);
    }

    // Muokkaa ID:n perusteella tikettityyppiä, tai 404 jos tikettityyppiä ei
    // annetulla ID:llä löydy
    @PutMapping("/{id}")
    public ResponseEntity<TicketType> updateTicketType(@PathVariable("id") Long id,
            @RequestBody TicketType editedTicketType) {
        if (!ttRepo.existsById(id)) {
            return new ResponseEntity<TicketType>(HttpStatus.NOT_FOUND);
        }
        editedTicketType.setId(id);
        ttRepo.save(editedTicketType);
        return new ResponseEntity<TicketType>(HttpStatus.OK);
    }

    // Poistaa ID:n perusteella tikettityypin tai palauttaa 404 jos tikettityyppiä
    // ei annetulla ID:llä löydy
    @DeleteMapping("/{id}")
    public ResponseEntity<Iterable<TicketType>> deleteTicketType(@PathVariable("id") Long id) {
        if (!ttRepo.existsById(id)) {
            return new ResponseEntity<Iterable<TicketType>>(HttpStatus.NOT_FOUND);
        }
        ttRepo.deleteById(id);
        return new ResponseEntity<Iterable<TicketType>>(HttpStatus.NO_CONTENT);
    }
}