package com.mahlagha.concertbooking.service;

import com.mahlagha.concertbooking.domain.Receipt;
import com.mahlagha.concertbooking.domain.Transaction;
import com.mahlagha.concertbooking.domain.UserTicket;
import com.mahlagha.concertbooking.enumeration.TransactionStatus;
import com.mahlagha.concertbooking.enumeration.TransferType;
import com.mahlagha.concertbooking.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MultipleTransactionStrategy implements TransactionStrategy {
    private final TransactionRepository transactionRepository;

    @Override
    public Receipt saveTransaction(List<UserTicket> userTickets) {
        userTickets.forEach(userTicket -> {
            transactionRepository.save(createWithdrawTransaction(userTicket));
        });
        //ToDo: create Receipt and return it
        return null;
    }

    private Transaction createWithdrawTransaction(UserTicket userTicket) {
        return Transaction.of()
                .transactionStatus(TransactionStatus.SUCCESSFUL)
                .user(userTicket.getUser())
                .tickets(List.of(userTicket.getTicket()))
                .sumAmount(userTicket.getTicket().getPrice())
                .transferType(TransferType.WITHDRAW)
                .build();
    }
}
