package com.example.eventmanagement.repository;

import com.example.eventmanagement.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    // For searching/filtering
    Page<Event> findByDateAfterAndLocationContainingAndTagsContaining(
            LocalDate date,
            String location,
            String tags,
            Pageable pageable
    );


    List<Event> findAll();
}
