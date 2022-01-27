package com.mahlagha.concertbooking.service;

import com.mahlagha.concertbooking.domain.Receipt;
import com.mahlagha.concertbooking.domain.Ticket;
import com.mahlagha.concertbooking.domain.UserTicket;
import com.mahlagha.concertbooking.enumeration.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketService {
    UserTicket reserve(Ticket ticket, Long userId);

    List<UserTicket> reserve(List<Ticket> tickets, Long userId);

    Receipt pay(UserTicket userTicket);

    Receipt pay(List<UserTicket> userTickets, TransactionType transactionType);

    Receipt refund(UserTicket userTicket);

    Page<Ticket> listByUserId(Long userId, Pageable pageable);
}
