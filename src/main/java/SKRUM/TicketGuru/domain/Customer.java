package SKRUM.TicketGuru.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
   
    @OneToMany(mappedBy = "customer")
    private List<Transaction> transactions;

    public String getname() {
        return name;
    }
    public void setname(String userName) {
        this.name = userName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Customer(String userName, String email) {
        super();
        this.name = userName;
        this.email = email;
    }
    public Customer(){
        super();
    }

}