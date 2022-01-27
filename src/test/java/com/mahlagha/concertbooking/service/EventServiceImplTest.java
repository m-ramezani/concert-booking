package com.mahlagha.concertbooking.service;

import com.mahlagha.concertbooking.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class EventServiceImplTest {

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventRepository eventRepository;

    private PageRequest pageable;

    @BeforeEach
    void setUp() {
        pageable = PageRequest.of(1, 10);
    }

    @Test
    void testListEvents() {
        eventService.listEvents(pageable);
        when(eventRepository.findAll(pageable)).thenReturn(null);
        verify(eventService, atLeastOnce()).listEvents(pageable);
    }
}