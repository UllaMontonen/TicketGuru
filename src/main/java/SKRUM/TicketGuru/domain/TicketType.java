package SKRUM.TicketGuru.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private double price;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticketType")
    private List<Ticket> tickets;

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketType() {
        super();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TicketType [description=" + description + ", price=" + price + "]";
    }

    public Event getEvent() {
        return event;
    }   

    public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public void setEvent(Event event) {
        this.event = event;
    }

    public TicketType(String description, double price, Event event) {
        super();
        this.description = description;
        this.price = price;
        this.event = event;
    }

}