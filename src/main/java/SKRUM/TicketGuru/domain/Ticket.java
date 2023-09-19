package SKRUM.TicketGuru.domain;

import jakarta.persistence.*;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "event")
	private Event event;

	@ManyToOne
	@JoinColumn(name = "ticketType")
	private TicketType ticketType;

	@ManyToOne
	@JoinColumn(name = "transaction")
	private Transaction transaction;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String code;

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	private boolean verified;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public Ticket() {
		super();
	}

	public Ticket(Event event, TicketType ticketType, Transaction transaction, String code, boolean verified) {
		super();
		this.event = event;
		this.ticketType = ticketType;
		this.transaction = transaction;
		this.code = code;
		this.verified = verified;
	}

}
