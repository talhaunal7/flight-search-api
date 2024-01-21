package com.talhaunal.flightsearchapi.repository;

import com.talhaunal.flightsearchapi.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "SELECT *\n" +
            "FROM flight\n" +
            "WHERE departure_airport_id = :departureAirportId\n" +
            "  AND departure_date >= :departureDateStart\n" +
            "  AND departure_date < :departureDateEnd\n" +
            "  AND return_date is null\n" +
            " AND return_airport_id is null"
            , nativeQuery = true)
    List<Flight> searchOneWay(
            Long departureAirportId,
            Instant departureDateStart,
            Instant departureDateEnd
    );

    @Query(value = "SELECT *\n" +
            "FROM flight\n" +
            "WHERE departure_airport_id = :departureAirportId\n" +
            "  AND return_airport_id = :returnAirportId\n" +
            "  AND departure_date >= :departureDateStart\n" +
            "  AND departure_date < :departureDateEnd\n" +
            "  AND return_date >= :returnDateStart\n" +
            "  AND return_date < :returnDateEnd"
            , nativeQuery = true)
    List<Flight> searchTwoWay(
            Long departureAirportId,
            Long returnAirportId,
            Instant departureDateStart,
            Instant departureDateEnd,
            Instant returnDateStart,
            Instant returnDateEnd
    );

}
