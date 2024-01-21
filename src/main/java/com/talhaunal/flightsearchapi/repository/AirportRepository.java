package com.talhaunal.flightsearchapi.repository;

import com.talhaunal.flightsearchapi.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByCity(String city);

}
