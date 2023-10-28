package SKRUM.TicketGuru.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Name cannot be empty")
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 1, max = 200, message = "Name must be between 1 and 200 characters")
	private String name;
	
	@NotEmpty(message = "Place cannot be empty")
	@Size(min = 1, max = 200, message = "Place must be between 1 and 200 characters")
	private String place;
	
	@NotEmpty(message = "City cannot be empty")
	@Size(min = 1, max = 100, message = "City must be between 1 and 100 characters")
	private String city;
	
	@NotNull(message = "Amount cannot be null")
    @PositiveOrZero(message = "Amount need to be a positive number")
	@Column(name = "ticket_amount")
	private Integer ticketAmount;

	@Temporal(TemporalType.DATE)
	@NotNull(message = "Date cannot be null")
	private Date eventDate;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	private List<Ticket> tickets;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	private List<TicketType> ticketTypes;

	public Event() {
		super();
	}

	public Event(String name, String place, String city, Integer ticketAmount, Date eventDate) {
		super();
		this.name = name;
		this.place = place;
		this.city = city;
		this.ticketAmount = ticketAmount;
		this.eventDate = eventDate;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(Integer ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<TicketType> getTicketTypes() {
		return ticketTypes;
	}

	public void setTicketTypes(List<TicketType> ticketTypes) {
		this.ticketTypes = ticketTypes;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + id + ", name=" + name + ", place=" + place + ", city=" + city + ", ticketAmount="
				+ ticketAmount + ", eventDate=" + eventDate + ", tickets=" + tickets + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}