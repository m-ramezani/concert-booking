package com.mahlagha.concertbooking.service;

import com.mahlagha.concertbooking.BaseTestClass;
import com.mahlagha.concertbooking.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class EventServiceImplTest extends BaseTestClass {

    @Mock
    private EventServiceImpl eventService;

    @Mock
    private EventRepository eventRepository;

    private AutoCloseable closeable;

    private PageRequest pageable;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        pageable = PageRequest.of(1, 10);
    }

    @Test
    void testListEvents() {
        when(eventRepository.findAll(pageable)).thenReturn(null);
        eventService.listEvents(pageable);
        verify(eventService, atLeastOnce()).listEvents(pageable);
    }
}