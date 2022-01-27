package com.mahlagha.concertbooking.domain;

import com.mahlagha.concertbooking.enumeration.TicketStatus;
import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "of")
@Table(name = "user_ticket")
public class UserTicket {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @Column(name = "ticket_id")
    private Ticket ticket;

    @Column(name = "user_id")
    private User user;
}
