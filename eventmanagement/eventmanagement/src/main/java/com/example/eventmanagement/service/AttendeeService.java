package com.example.eventmanagement.service;

import com.example.eventmanagement.dto.AttendeeDTO;
import com.example.eventmanagement.dto.EventAnalyticsDTO;
import com.example.eventmanagement.entity.Attendee;
import com.example.eventmanagement.entity.Event;
import com.example.eventmanagement.repository.AttendeeRepository;
import com.example.eventmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private EventRepository eventRepository;

    public Attendee registerAttendee(AttendeeDTO attendeeDTO) {
        Event event = eventRepository.findById(attendeeDTO.getEventId())
                .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + attendeeDTO.getEventId()));
        if (event.getRemainingCapacity() <= 0) {
            throw new IllegalStateException("Event with ID " + attendeeDTO.getEventId() + " is full");
        }
        Attendee attendee = new Attendee();
        attendee.setName(attendeeDTO.getName());
        attendee.setEmail(attendeeDTO.getEmail());
        attendee.setEvent(event);
        event.setRemainingCapacity(event.getRemainingCapacity() - 1);
        try {
            eventRepository.save(event); // Ensure event is saved first
            return attendeeRepository.save(attendee);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register attendee: " + e.getMessage(), e);
        }
    }

    public List<Attendee> getAttendeesByEvent(Long eventId) {
        return attendeeRepository.findByEventId(eventId);
    }

    public EventAnalyticsDTO getEventAnalytics(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + eventId));
        List<Attendee> attendees = attendeeRepository.findByEventId(eventId);
        EventAnalyticsDTO analytics = new EventAnalyticsDTO();
        analytics.setTotalAttendees(attendees.size());
        analytics.setCapacityUtilization(
                event.getCapacity() > 0 ?
                        ((double) (event.getCapacity() - event.getRemainingCapacity()) / event.getCapacity() * 100) : 0.0
        );
        return analytics;
    }

    public Attendee createAttendee(AttendeeDTO attendeeDTO) {
        return registerAttendee(attendeeDTO); // Delegate to registerAttendee
    }
}