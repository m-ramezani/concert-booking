package com.mahlagha.concertbooking.service;

import com.mahlagha.concertbooking.domain.Receipt;
import com.mahlagha.concertbooking.domain.UserTicket;
import com.mahlagha.concertbooking.enumeration.TransactionType;

import java.util.List;

public interface PaymentService {
    Receipt pay(List<UserTicket> userTickets, TransactionType transactionType);

    Receipt pay(UserTicket userTicket);

    Receipt refund(UserTicket userTicket);
}
