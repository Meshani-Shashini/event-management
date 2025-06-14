package com.example.eventmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "attendees")
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
}