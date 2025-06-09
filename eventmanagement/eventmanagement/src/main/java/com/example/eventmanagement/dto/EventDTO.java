package com.example.eventmanagement.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class EventDTO {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 255)
    private String name;

    private String description;

    @NotNull(message = "Date is mandatory")
    private LocalDate date;

    private String location;

    private String createdBy;

    @NotNull(message = "Capacity is mandatory")
    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;

    private String tags;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }
}