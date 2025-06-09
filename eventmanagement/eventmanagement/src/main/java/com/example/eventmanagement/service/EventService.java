package com.example.eventmanagement.service;

import com.example.eventmanagement.dto.EventDTO;
import com.example.eventmanagement.entity.Event;
import com.example.eventmanagement.repository.AttendeeRepository;
import com.example.eventmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AttendeeRepository attendeeRepository;

    // Get all events (no pagination, no filtering)
    public List<Event> getAllEvents(Pageable pageable) {
        return eventRepository.findAll();
    }

    // Create a new event
    public Event createEvent(EventDTO eventDTO) {
        if (eventDTO.getDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot create past events");
        }
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setDate(eventDTO.getDate());
        event.setLocation(eventDTO.getLocation());
        event.setCreatedBy(eventDTO.getCreatedBy());
        event.setCapacity(eventDTO.getCapacity());
        event.setRemainingCapacity(eventDTO.getCapacity());
        event.setTags(eventDTO.getTags());
        return eventRepository.save(event);
    }

    // Update an existing event
    public Event updateEvent(Long id, EventDTO eventDTO) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));
        if (event.getDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot update past events");
        }
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setDate(eventDTO.getDate());
        event.setLocation(eventDTO.getLocation());
        event.setCapacity(eventDTO.getCapacity());
        event.setRemainingCapacity(eventDTO.getCapacity());
        event.setTags(eventDTO.getTags());
        return eventRepository.save(event);
    }

    // Delete event by id
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));
        attendeeRepository.deleteAll(attendeeRepository.findByEventId(id));
        eventRepository.delete(event);
    }

    // Get event by id
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }


}
