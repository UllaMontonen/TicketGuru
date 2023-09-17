package SKRUM.TicketGuru.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketTypeId;
    private String name, description;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tickettype")
    private List<Ticket> tickets;

    public Long getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Long ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public TicketType(String name, String description, double price) {
        super();
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "TicketType [ticketTypeId=" + ticketTypeId + ", name=" + name + ", description=" + description
                + ", price=" + "]";
    }
}
