package com.mahlagha.concertbooking.service;

import com.mahlagha.concertbooking.domain.Receipt;
import com.mahlagha.concertbooking.domain.Transaction;
import com.mahlagha.concertbooking.domain.UserTicket;
import com.mahlagha.concertbooking.enumeration.TransactionStatus;
import com.mahlagha.concertbooking.enumeration.TransactionType;
import com.mahlagha.concertbooking.enumeration.TransferType;
import com.mahlagha.concertbooking.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final TransactionRepository transactionRepository;

    @Override
    public Receipt pay(List<UserTicket> userTickets, TransactionType transactionType) {
        return TransactionStrategyFactory.getTransactionStrategy(transactionType).saveTransaction(userTickets);
    }

    @Override
    public Receipt pay(UserTicket userTicket) {
        transactionRepository.save(createWithdrawTransaction(userTicket));
        //ToDo: create Receipt and return it
        return null;
    }

    @Override
    public Receipt refund(UserTicket userTicket) {
        transactionRepository.save(createDepositTransaction(userTicket));
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
    private Transaction createDepositTransaction(UserTicket userTicket) {
        return Transaction.of()
                .transactionStatus(TransactionStatus.SUCCESSFUL)
                .user(userTicket.getUser())
                .tickets(List.of(userTicket.getTicket()))
                .sumAmount(userTicket.getTicket().getPrice())
                .transferType(TransferType.WITHDRAW)
                .build();
    }
}
