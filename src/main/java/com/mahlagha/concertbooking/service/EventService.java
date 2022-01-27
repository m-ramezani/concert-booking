package com.mahlagha.concertbooking.service;

import com.mahlagha.concertbooking.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
    Page<Event> listEvents(Pageable pageable);
}
