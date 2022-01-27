package com.mahlagha.concertbooking.repository;


import com.mahlagha.concertbooking.domain.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
}
