package com.mahlagha.concertbooking.domain;

import com.mahlagha.concertbooking.enumeration.TransactionStatus;
import com.mahlagha.concertbooking.enumeration.TransferType;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "of")

public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "sum_amount")
    private double sumAmount;

    @Column(name = "transaction_date")
    private Instant transactionDate;

    @Column(name = "source_account")
    private String sourceAccount;

    @Column(name = "destination_account")
    private String destinationAccount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Enumerated(EnumType.STRING)
    private TransferType transferType;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "transaction")
    private List<Ticket> tickets;

    @PrePersist
    public void setTransactionDate() {
        this.transactionDate = Instant.now();
    }

}
