package SKRUM.TicketGuru;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import SKRUM.TicketGuru.domain.*;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository cRepo;
    /*
    // Luo uusi asiakas ja tarkista, saako se ID:n
    @Test
    public void createNewCustomer() {
        Customer customer = new Customer();
        Customer savedCustomer = cRepo.save(customer);

        assertThat(savedCustomer.getId()).isNotNull();
    }

    // Luo asiakas, laske tapahtumien lukumäärä, poista asiakas ja varmista lukumäärän muutos
    @Test
    @Transactional
    public void deleteCustomer() {
        Customer customer = cRepo.save(new Customer("name", "email@testi.fi"));
        Long customerId = customer.getId();
        
        long initialCustomerCount = cRepo.count();

        cRepo.deleteById(customerId);

        long newCustomerCount = cRepo.count();

        assertThat(initialCustomerCount).isEqualTo(newCustomerCount + 1);
    }

    // Luo asiakas, etsi se ID:n perusteella ja tarkista löydetyn asiakaan tiedot
    @Test
    public void createAndFindCustomerById() {
        Customer customer = cRepo.save(new Customer());
        Long customerId = customer.getId();

        Customer foundCustomer = cRepo.findById(customerId).orElse(null);

        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getName()).isEqualTo(customer.getName());
    } */
}