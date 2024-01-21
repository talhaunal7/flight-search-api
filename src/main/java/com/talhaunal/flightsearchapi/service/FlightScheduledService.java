package com.talhaunal.flightsearchapi.service;

import com.talhaunal.flightsearchapi.domain.Airport;
import com.talhaunal.flightsearchapi.domain.Flight;
import com.talhaunal.flightsearchapi.repository.AirportRepository;
import com.talhaunal.flightsearchapi.repository.FlightRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

@Service
public class FlightScheduledService {
    private final FlightRepository flightRepository;

    private final AirportRepository airportRepository;

    public FlightScheduledService(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    @Scheduled(fixedRate = 30000)
    public void schedule() {
        List<Flight> flights = getFlights();
        flightRepository.saveAll(flights);
    }

    private List<Flight> getFlights() {

        Airport airportData1 = new Airport();
        airportData1.setCity("istanbul");
        Airport airportData2 = new Airport();
        airportData2.setCity("ankara");

        Airport airport1 = airportRepository.findByCity(airportData1.getCity()).orElseGet(() -> airportRepository.save(airportData1));
        Airport airport2 = airportRepository.findByCity(airportData2.getCity()).orElseGet(() -> airportRepository.save(airportData2));

        int i = new Random().nextInt(10) + 1;
        Flight flight1 = Flight.Builder.aFlight()
                .amount(BigDecimal.valueOf(130))
                .departureAirport(airport1)
                .departureDate(Instant.now().plus(i, ChronoUnit.HOURS))
                .build();

        Flight flight2 = Flight.Builder.aFlight()
                .amount(BigDecimal.valueOf(170))
                .departureDate(Instant.now().plus(i, ChronoUnit.DAYS))
                .departureAirport(airport1)
                .returnAirport(airport2)
                .returnDate(Instant.now().plus(i + 1, ChronoUnit.DAYS))
                .build();

        return List.of(flight1, flight2);
    }
}
