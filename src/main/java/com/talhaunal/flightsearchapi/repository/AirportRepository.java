package com.talhaunal.flightsearchapi.repository;

import com.talhaunal.flightsearchapi.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
