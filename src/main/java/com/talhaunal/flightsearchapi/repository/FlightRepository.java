package com.talhaunal.flightsearchapi.repository;

import com.talhaunal.flightsearchapi.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
