package SKRUM.TicketGuru.domain;

import jakarta.persistence.*;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;

	@ManyToOne
	@JoinColumn(name = "ticketType_id")
	private TicketType ticketType;

	@ManyToOne
	@JoinColumn(name = "transaction_id")
	private Transaction transaction;

	private boolean verified;
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
