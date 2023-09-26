package SKRUM.TicketGuru.domain;

public class TransactionDTO {
	private Long customerId;
	private String customerName, customerEmail;
	private int ticketAmount;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
	public int getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(int ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	public TransactionDTO(Long customerId, String customerName, String customerEmail, int ticketAmount) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.ticketAmount = ticketAmount;
	}
	public TransactionDTO() {
		super();
	}
	
	
}
