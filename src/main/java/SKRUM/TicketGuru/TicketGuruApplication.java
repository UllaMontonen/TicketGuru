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
	public CommandLineRunner demo(CustomerRepository cRepo, EventRepository eRepo, TicketRepository tRepo,
			TicketTypeRepository ttRepo, TransactionRepository trRepo) {
		return (args) -> {
			Date date = new Date();

			// customer
			cRepo.save(new Customer("Testi Pesti", "email@mail.com"));

			// event
			eRepo.save(new Event("TestiTapahtuma", "Apollo", "Helsinki", 100, date));
			eRepo.save(new Event("TestiTapahtuma2", "Maxine", "Helsinki", 100, date));

			// TicketType

			ttRepo.save(new TicketType("Normaali", 20.30, eRepo.findByName("TestiTapahtuma").get(0)));
			ttRepo.save(new TicketType("Opiskelija", 21.32, eRepo.findByName("TestiTapahtuma2").get(0)));

			// ticket ja transaction
			tRepo.save(new Ticket(eRepo.findByName("TestiTapahtuma").get(0),
					ttRepo.findByDescription("Normaali").get(0), trRepo.save(new Transaction(new Date(), 56.34, cRepo.findByName("Testi Pesti").get(0))), "ABC-123", true));
			tRepo.save(new Ticket(eRepo.findByName("TestiTapahtuma2").get(0),
					ttRepo.findByDescription("Opiskelija").get(0), trRepo.save(new Transaction(new Date(), 26.34, cRepo.findByName("Testi Pesti").get(0))), "ABC-123", true));

		};
	}

}
