package com.mahlagha.concertbooking.validator;

import com.mahlagha.concertbooking.domain.Ticket;
import com.mahlagha.concertbooking.exception.AllTicketsAreSoldOutException;
import com.mahlagha.concertbooking.exception.TicketNotFoundException;
import com.mahlagha.concertbooking.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketValidator {

    private final TicketRepository ticketRepository;

    public void validateTicket(Ticket ticket) throws TicketNotFoundException, AllTicketsAreSoldOutException {
        ticketRepository.findById(ticket.getId()).orElseThrow(TicketNotFoundException::new);
        if (ticket.getEvent().getTicketCounts() <= 0) {
            throw new AllTicketsAreSoldOutException();
        }
    }
}
