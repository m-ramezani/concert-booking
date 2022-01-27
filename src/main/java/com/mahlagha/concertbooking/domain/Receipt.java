package com.mahlagha.concertbooking.domain;

import com.mahlagha.concertbooking.enumeration.TransactionStatus;
import lombok.Data;

@Data
public class Receipt {
    private String description;
    private double price;
    private TransactionStatus status;
}
