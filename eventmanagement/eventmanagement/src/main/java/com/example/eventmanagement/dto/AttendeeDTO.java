package com.example.eventmanagement.dto;

import jakarta.validation.constraints.*;

public class AttendeeDTO {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    private Long eventId;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
}