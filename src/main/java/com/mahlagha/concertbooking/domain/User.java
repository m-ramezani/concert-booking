package com.mahlagha.concertbooking.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "of")

public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_ticket",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id"))
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
    }
}
