package SKRUM.TicketGuru;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import SKRUM.TicketGuru.domain.*;

@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eRepo;
    /*
    // Luo uusi tapahtuma ja tarkista, saako se ID:n
    @Test
    public void createNewEvent() {
        Event event = new Event();
        Event savedEvent = eRepo.save(event);

        assertThat(savedEvent.getId()).isNotNull();
    }

    // Luo tapahtuma, laske tapahtumien lukumäärä, poista tapahtuma ja varmista lukumäärän muutos
    @Test
    @Transactional
    public void deleteEvent() {
        Event event = eRepo.save(new Event("name", "place", "city", 1, null));
        Long eventId = event.getId();
        
        long initialEventCount = eRepo.count();

        eRepo.deleteById(eventId);

        long newEventCount = eRepo.count();

        assertThat(initialEventCount).isEqualTo(newEventCount + 1);
    }

    // Luo tapahtuma, etsi se ID:n perusteella ja tarkista löydetyn tapahtuman tiedot
    @Test
    public void createAndFindEventById() {
        Event event = eRepo.save(new Event());
        Long eventId = event.getId();

        Event foundEvent = eRepo.findById(eventId).orElse(null);

        assertThat(foundEvent).isNotNull();
        assertThat(foundEvent.getName()).isEqualTo(event.getName());
    } */
}