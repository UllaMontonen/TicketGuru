package SKRUM.TicketGuru.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketTypeId;
    private String description;
    private double price;


    public Long getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Long ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public TicketType() {
        super();
    }

    public TicketType(String description, double price) {
        super();
        this.description = description;
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
    
}