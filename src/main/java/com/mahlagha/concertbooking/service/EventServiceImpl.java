package com.mahlagha.concertbooking.service;

import com.mahlagha.concertbooking.domain.Event;
import com.mahlagha.concertbooking.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Page<Event> listEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }
}
