package com.example.eventmanagement.controller;

import com.example.eventmanagement.dto.AttendeeDTO;
import com.example.eventmanagement.dto.EventAnalyticsDTO;
import com.example.eventmanagement.entity.Attendee;
import com.example.eventmanagement.service.AttendeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {
    @Autowired
    private AttendeeService attendeeService;

    @PostMapping
    public ResponseEntity<Attendee> createAttendee(@Valid @RequestBody AttendeeDTO attendeeDTO) {
        try {
            Attendee attendee = attendeeService.createAttendee(attendeeDTO);
            return ResponseEntity.ok(attendee);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // Or return error details
        }
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Attendee>> getAttendeesByEvent(@PathVariable Long eventId) {
        try {
            List<Attendee> attendees = attendeeService.getAttendeesByEvent(eventId);
            return ResponseEntity.ok(attendees);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/event/{eventId}/analytics")
    public ResponseEntity<EventAnalyticsDTO> getEventAnalytics(@PathVariable Long eventId) {
        return ResponseEntity.ok(attendeeService.getEventAnalytics(eventId));
    }
}