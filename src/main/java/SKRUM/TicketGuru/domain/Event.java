package SKRUM.TicketGuru.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name, place, city;
    private int ticketAmount;
    
    @Temporal(TemporalType.DATE)
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
     

	public Event(String name, String place, String city, int ticketAmount, Date eventDate, List<Ticket> tickets, List<TicketType> ticketTypes) {
		super();
		this.name = name;
		this.place = place;
		this.city = city;
		this.ticketAmount = ticketAmount;
		this.eventDate = eventDate;
		this.tickets = tickets;
		this.ticketTypes = ticketTypes;
	}
	
	

	public Event(String name, String place, String city, int ticketAmount, Date eventDate) {
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
	public void setTicketAmount(int ticketAmount) {
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
		return "Event [eventId=" + id + ", name=" + name + ", place=" + place + ", city=" + city
				+ ", ticketAmount=" + ticketAmount + ", eventDate=" + eventDate + ", tickets=" + tickets + "]";
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	

	


}