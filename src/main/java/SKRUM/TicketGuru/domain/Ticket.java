package SKRUM.TicketGuru.domain;

import jakarta.persistence.*;


@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ticketId;
	
	@ManyToOne
	@JoinColumn(name = "eventId")
	private Event event;
	
	//Lipputyypin tekijälle terkkuja, muokkaa tarvittaessa nimiä alta 
	@ManyToOne
	@JoinColumn(name = "ticketType")
	private TicketType TicketType;
	
	private double ticketPrice;

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public TicketType getTicketType() {
		return TicketType;
	}

	public void setTicketType(TicketType ticketType) {
		TicketType = ticketType;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Ticket() {
		super();
	}

	public Ticket(Event event, TicketType ticketType, double ticketPrice) {
		super();
		this.event = event;
		TicketType = ticketType;
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", event=" + event + ", TicketType=" + TicketType + ", ticketPrice="
				+ ticketPrice + ", getTicketId()=" + getTicketId() + ", getEvent()=" + getEvent() + ", getTicketType()="
				+ getTicketType() + ", getTicketPrice()=" + getTicketPrice() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
