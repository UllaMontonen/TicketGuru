package SKRUM.TicketGuru.domain;

public class GenerateTicketsDTO {
	private Long eventId, ticketTypeId;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getTicketTypeId() {
		return ticketTypeId;
	}

	public void setTicketTypeId(Long ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}

	public GenerateTicketsDTO(Long eventId, Long ticketTypeId) {
		super();
		this.eventId = eventId;
		this.ticketTypeId = ticketTypeId;
	}

	public GenerateTicketsDTO() {
		super();
	}
	
	

}
