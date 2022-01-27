package com.mahlagha.concertbooking.service;

import com.mahlagha.concertbooking.domain.Receipt;
import com.mahlagha.concertbooking.domain.UserTicket;

import java.util.List;

public interface TransactionStrategy {
    Receipt saveTransaction(List<UserTicket> userTickets);
}
