package com.talhaunal.flightsearchapi.repository;

import com.talhaunal.flightsearchapi.domain.Airport;
import com.talhaunal.flightsearchapi.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    
    List<Flight> findAllByDepartureAirportAndReturnAirportAndDepartureDateGreaterThanEqualAndDepartureDateLessThan(
            Airport departureAirport,
            Airport returnAirport,
            Instant departureDateStart,
            Instant departureDateEnd
    );
}
