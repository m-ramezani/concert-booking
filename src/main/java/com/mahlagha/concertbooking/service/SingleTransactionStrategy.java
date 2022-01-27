package com.mahlagha.concertbooking.service;

import com.mahlagha.concertbooking.domain.Receipt;
import com.mahlagha.concertbooking.domain.Ticket;
import com.mahlagha.concertbooking.domain.Transaction;
import com.mahlagha.concertbooking.domain.UserTicket;
import com.mahlagha.concertbooking.enumeration.TransactionStatus;
import com.mahlagha.concertbooking.enumeration.TransferType;
import com.mahlagha.concertbooking.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SingleTransactionStrategy implements TransactionStrategy {
    private final TransactionRepository transactionRepository;

    @Override
    public Receipt saveTransaction(List<UserTicket> userTickets) {
        transactionRepository.save(createWithdrawTransaction(userTickets));
        //ToDo: create Receipt and return it
        return null;
    }

    private Transaction createWithdrawTransaction(List<UserTicket> userTickets) {
        double sumAmount = userTickets.stream().mapToDouble(userTicket -> userTicket.getTicket().getPrice()).sum();
        List<Ticket> tickets = userTickets.stream().map(UserTicket::getTicket).collect(Collectors.toList());

        return Transaction.of()
                .transactionStatus(TransactionStatus.SUCCESSFUL)
                .user(userTickets.get(0).getUser())
                .tickets(tickets)
                .sumAmount(sumAmount)
                .transferType(TransferType.WITHDRAW)
                .build();
    }
}
