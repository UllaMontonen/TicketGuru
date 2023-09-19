package SKRUM.TicketGuru;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import SKRUM.TicketGuru.domain.*;

@SpringBootApplication
public class TicketGuruApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketGuruApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(CustomerRepository cRepo, EventRepository eRepo, TicketRepository tRepo, TicketTypeRepository ttRepo, TransactionRepository trRepo) {
		return (args) -> {
			//customer
			Customer customer = new Customer("Testi Pesti", "email@mail.com");
			cRepo.save(customer);
			
			//transaction
			Date date = new Date();
			Transaction transaction = new Transaction(date, 56.34, customer);
			trRepo.save(transaction);
			trRepo.save(transaction);
			
			//event
			Event event = new Event("TestiTapahtuma", "Apollo", "Helsinki", 100, date);
			Event event1 = new Event("TestiTapahtuma2", "Maxine", "Helsinki", 100, date);
			eRepo.save(event);
			eRepo.save(event1);
			
			//TicketType
			TicketType ticketType = new TicketType("Normaali", 20.30, event);
			TicketType ticketType1 = new TicketType("Opiskelija", 21.32, event1);
			ttRepo.save(ticketType);
			ttRepo.save(ticketType1);
			
			//ticket
			Ticket ticket = new Ticket(event, ticketType, transaction, "ABC-123", true);
			Ticket ticket1 = new Ticket(event1, ticketType1, transaction, "DAC-321", true);
			tRepo.save(ticket);
			tRepo.save(ticket1);
			
		};
	}
	
}
