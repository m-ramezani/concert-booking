package com.mahlagha.concertbooking.service;

import com.mahlagha.concertbooking.domain.Event;
import com.mahlagha.concertbooking.domain.Ticket;
import com.mahlagha.concertbooking.domain.User;
import com.mahlagha.concertbooking.domain.UserTicket;
import com.mahlagha.concertbooking.repository.UserRepository;
import com.mahlagha.concertbooking.repository.UserTicketRepository;
import com.mahlagha.concertbooking.validator.TicketValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class TicketServiceImplTest {

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserTicketRepository userTicketRepository;
    @Mock
    private TicketValidator ticketValidator;
    @Mock
    private PaymentService paymentService;

    private Long userId;

    private PageRequest pageable;


    @BeforeEach
    void setUp() {
        userId = 100L;
        pageable = PageRequest.of(1, 10);
    }


    @Test
    void testReserve() {
        Ticket ticket = createTicket();

        doNothing().when(ticketValidator).validateTicket(any());
        when(userRepository.findById(userId)).thenReturn(createUser());
        when(userTicketRepository.save(any())).thenReturn(createUserTicket());
        ticketService.reserve(ticket, userId);

        verify(ticketService, atLeastOnce()).reserve(ticket, userId);
    }

    @Test
    void testPay() {
        UserTicket userTicket = createUserTicket();

        when(paymentService.pay(userTicket)).thenReturn(null);
        ticketService.pay(createUserTicket());
        verify(ticketService, atLeastOnce()).pay(userTicket);
    }

    @Test
    void testRefund() {
        UserTicket userTicket = createUserTicket();

        when(paymentService.refund(userTicket)).thenReturn(null);
        ticketService.refund(userTicket);
        verify(ticketService, atLeastOnce()).refund(userTicket);
    }

    @Test
    void listByUserId() {
        when(userRepository.findById(userId)).thenReturn(createUser());
        ticketService.listByUserId(userId, pageable);
        verify(ticketService, atLeastOnce()).listByUserId(userId, pageable);
    }

    private Ticket createTicket() {
        return Ticket.of()
                .event(new Event())
                .id(1L)
                .price(200)
                .build();
    }

    private Optional<User> createUser() {
        return Optional.of(User.of()
                .id(userId)
                .build());
    }

    private UserTicket createUserTicket() {
        return UserTicket.of()
                .user(createUser().get())
                .ticket(createTicket())
                .build();
    }
}