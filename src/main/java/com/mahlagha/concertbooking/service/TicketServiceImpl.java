package com.mahlagha.concertbooking.service;

import com.mahlagha.concertbooking.domain.Receipt;
import com.mahlagha.concertbooking.domain.Ticket;
import com.mahlagha.concertbooking.domain.User;
import com.mahlagha.concertbooking.domain.UserTicket;
import com.mahlagha.concertbooking.enumeration.TicketStatus;
import com.mahlagha.concertbooking.enumeration.TransactionStatus;
import com.mahlagha.concertbooking.enumeration.TransactionType;
import com.mahlagha.concertbooking.exception.AllTicketsAreSoldOutException;
import com.mahlagha.concertbooking.exception.TicketNotFoundException;
import com.mahlagha.concertbooking.exception.UserNotFoundException;
import com.mahlagha.concertbooking.repository.TicketRepository;
import com.mahlagha.concertbooking.repository.UserRepository;
import com.mahlagha.concertbooking.repository.UserTicketRepository;
import com.mahlagha.concertbooking.validator.TicketValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);
    private final UserRepository userRepository;
    private final UserTicketRepository userTicketRepository;
    private final TicketValidator ticketValidator;
    private final TicketRepository ticketRepository;
    private final PaymentService paymentService;

    @Override
    public UserTicket reserve(Ticket ticket, Long userId) {
        ticketValidator.validateTicket(ticket);

        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.addTicket(ticket);

        UserTicket persistedUserTicket = userTicketRepository.save(createUserTicket(ticket, user));
        ticket.getEvent().setTicketCounts(ticket.getEvent().getTicketCounts() - 1);
        return persistedUserTicket;
    }

    @Override
    public List<UserTicket> reserve(List<Ticket> tickets, Long userId) {

        List<UserTicket> userTickets = new ArrayList<>();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        tickets.forEach(ticket -> {
            try {
                ticketValidator.validateTicket(ticket);
            } catch (TicketNotFoundException ex) {
                LOGGER.error("ticket with id {} not found", ticket.getId());
            } catch (AllTicketsAreSoldOutException ex) {
                LOGGER.error("ticket with id {} has been sold out", ticket.getId());
            }
            user.addTicket(ticket);
            userTickets.add(userTicketRepository.save(createUserTicket(ticket, user)));
            ticket.getEvent().setTicketCounts(ticket.getEvent().getTicketCounts() - 1);
        });
        return userTickets;
    }

    @Override
    public Receipt pay(UserTicket userTicket) {
        Receipt receipt = paymentService.pay(userTicket);

        if (receipt.getStatus() == TransactionStatus.SUCCESSFUL) {
            userTicket.setTicketStatus(TicketStatus.PAID);
        }
        return receipt;
    }

    @Override
    public Receipt pay(List<UserTicket> userTickets, TransactionType transactionType) {
        Receipt receipt = paymentService.pay(userTickets, transactionType);
        if (receipt.getStatus() == TransactionStatus.SUCCESSFUL) {
            userTickets.forEach(userTicket -> userTicket.setTicketStatus(TicketStatus.PAID));
        }
        return receipt;
    }

    @Override
    public Receipt refund(UserTicket userTicket) {
        Receipt receipt = paymentService.refund(userTicket);
        if (receipt.getStatus() == TransactionStatus.SUCCESSFUL) {
            userTicket.setTicketStatus(TicketStatus.CANCELED);
        }
        return receipt;
    }

    @Override
    public Page<Ticket> listByUserId(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return ticketRepository.findByUser(user, pageable);
    }

    private UserTicket createUserTicket(Ticket ticket, User user) {
        return UserTicket.of()
                .user(user)
                .ticket(ticket)
                .ticketStatus(TicketStatus.RESERVED)
                .build();
    }
}
