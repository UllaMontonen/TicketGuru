package SKRUM.TicketGuru.domain;

public class TicketDTO {
	private Long ticketTypeId, eventId;
	private int ticketAmount;

	public Long getTicketTypeId() {
		return ticketTypeId;
	}

	public void setTicketTypeId(Long ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}

	public Integer getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(Integer ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public TicketDTO(Long ticketTypeId, Long eventId, Integer ticketAmount) {
		super();
		this.ticketTypeId = ticketTypeId;
		this.eventId = eventId;
		this.ticketAmount = ticketAmount;
	}

	public TicketDTO() {
		super();
	}

}
