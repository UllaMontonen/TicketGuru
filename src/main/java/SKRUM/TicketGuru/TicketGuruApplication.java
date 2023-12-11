package SKRUM.TicketGuru;

import java.util.Calendar;
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
			TicketTypeRepository ttRepo, TransactionRepository trRepo, UserRepository uRepo) {
		return (args) -> {
			Calendar calendar = Calendar.getInstance();
			// Add 1 day to the current date
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			// Convert the Calendar back to a Date
			Date date = calendar.getTime();

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
					ttRepo.findByDescription("Normaali").get(0),
					trRepo.save(new Transaction(new Date(), 56.34, cRepo.findByName("Testi Pesti").get(0))), "ABC-123",
					true));
			tRepo.save(new Ticket(eRepo.findByName("TestiTapahtuma2").get(0),
					ttRepo.findByDescription("Opiskelija").get(0),
					trRepo.save(new Transaction(new Date(), 26.34, cRepo.findByName("Testi Pesti").get(0))), "ABC-123",
					true));

			// Käyttäjän luonti
			User user1 = new User("user", "{bcrypt}$2a$10$cZAbqG8AaRTHSdwuNgPEHunTzr5.M.cAx4u6XwMsDSri.0e6wF8ca",
					"USER");
			uRepo.save(user1);
			User admin = new User("admin", "{bcrypt}$2a$12$wW1l6GyD6.OHZsOZuBaFVu0bFqD0aggEQ9k2vjo1d9.Adn0j0PmGK",
					"ADMIN");
			uRepo.save(admin);
			User scanner = new User("scanner", "{bcrypt}$2a$12$J18yaI/yoy2LYzjbie8Vhus3s3UfbyRJN3BV/y6LxQxwjnGOgXte6",
					"SCANNER");
			uRepo.save(scanner);

		};
	}

}
