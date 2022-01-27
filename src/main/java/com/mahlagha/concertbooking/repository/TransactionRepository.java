package com.mahlagha.concertbooking.repository;


import com.mahlagha.concertbooking.domain.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
}
