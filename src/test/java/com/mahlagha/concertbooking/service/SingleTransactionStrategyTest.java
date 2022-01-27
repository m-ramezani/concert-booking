package com.mahlagha.concertbooking.service;

import com.mahlagha.concertbooking.domain.Event;
import com.mahlagha.concertbooking.domain.Ticket;
import com.mahlagha.concertbooking.domain.User;
import com.mahlagha.concertbooking.domain.UserTicket;
import com.mahlagha.concertbooking.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class SingleTransactionStrategyTest {

    @InjectMocks
    private MultipleTransactionStrategy transactionStrategy;

    @Mock
    private TransactionRepository transactionRepository;

    private Long userId;

    @BeforeEach
    void setUp() {
        userId = 100L;
    }

    @Test
    void test_save_transaction() {
        when(transactionRepository.save(any())).thenReturn(createUserTicket());
        transactionStrategy.saveTransaction(List.of(createUserTicket()));
        verify(transactionStrategy, atLeastOnce()).saveTransaction(any());
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