package SKRUM.TicketGuru.domain;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long eventId;
    private String name, place, city;
    private int ticketAmount, eventDay, eventMonth, eventYear, eventTime;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<Ticket> tickets;
	
    
    public Event() {
		super();
	}
    
	public Event(int eventYear, String place, int ticketAmount, String name, String city, int eventMonth, int eventDay,
			int eventTime) {
		super();
		this.eventYear = eventYear;
		this.place = place;
		this.ticketAmount = ticketAmount;
		this.name = name;
		this.city = city;
		this.eventMonth = eventMonth;
		this.eventDay = eventDay;
		this.eventTime = eventTime;
	}


	public Long getId() {
		return eventId;
	}
	public void setId(Long id) {
		this.eventId = id;
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
	
	public int getEventDay() {
		return eventDay;
	}
	public void setEventDay(int eventDay) {
		this.eventDay = eventDay;
	}
	
	public int getEventMonth() {
		return eventMonth;
	}
	public void setEventMonth(int eventMonth) {
		this.eventMonth = eventMonth;
	}
	
	public int getEventYear() {
		return eventYear;
	}
	public void setEventYear(int eventYear) {
		this.eventYear = eventYear;
	}
	
	public int getEventTime() {
		return eventTime;
	}
	public void setEventTime(int eventTime) {
		this.eventTime = eventTime;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "Event [id=" + eventId + ", name=" + name + ", place=" + place + ", city=" + city + ", ticketAmount="
				+ ticketAmount + ", eventDay=" + eventDay + ", eventMonth=" + eventMonth + ", eventYear=" + eventYear
				+ ", eventTime=" + eventTime + "]";
	}

	


}