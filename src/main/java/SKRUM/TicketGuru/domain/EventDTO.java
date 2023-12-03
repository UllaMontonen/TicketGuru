package SKRUM.TicketGuru.domain;

import java.util.Date;
import java.util.List;

public class EventDTO {
	
	private Long id;
	private String name, place, city;
	private Integer ticketAmount, ticketsSold;
	private Date eventDate;
	private List<Ticket> tickets;
	private List<TicketType> ticketTypes;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(Integer ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	public Integer getTicketsSold() {
		return ticketsSold;
	}
	public void setTicketsSold(Integer ticketsSold) {
		this.ticketsSold = ticketsSold;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
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
	public EventDTO(Long id, String name, String place, String city, Integer ticketAmount, Integer ticketsSold,
			Date eventDate, List<Ticket> tickets, List<TicketType> ticketTypes) {
		super();
		this.id = id;
		this.name = name;
		this.place = place;
		this.city = city;
		this.ticketAmount = ticketAmount;
		this.ticketsSold = ticketsSold;
		this.eventDate = eventDate;
		this.tickets = tickets;
		this.ticketTypes = ticketTypes;
	}
	public EventDTO() {
		super();
	}
	
	

}
