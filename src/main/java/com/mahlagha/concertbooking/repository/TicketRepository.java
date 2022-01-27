package com.mahlagha.concertbooking.repository;


import com.mahlagha.concertbooking.domain.Ticket;
import com.mahlagha.concertbooking.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {
    Page<Ticket> findByUser(User user, Pageable pageable);
}
