package SKRUM.TicketGuru.domain;

import java.util.List;

public class TicketSaleDTO {
	private Long customerId;
	private String customerName, customerEmail;
	private List<TicketDTO> ticketDTO;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerID) {
		this.customerId = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public List<TicketDTO> getTicketsDTO() {
		return ticketDTO;
	}

	public void setTicketsDTO(List<TicketDTO> ticketDTO) {
		this.ticketDTO = ticketDTO;
	}

	public TicketSaleDTO(Long customerId, String customerName, String customerEmail, List<TicketDTO> ticketDTO) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.ticketDTO = ticketDTO;
	}

	public TicketSaleDTO() {
		super();
	}

}
