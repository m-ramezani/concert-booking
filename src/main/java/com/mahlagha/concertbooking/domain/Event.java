package com.mahlagha.concertbooking.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "of")

public class Event {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "ticket_counts")
    private int ticketCounts;

    @Column(name = "event_date")
    private Instant eventDate;
}
