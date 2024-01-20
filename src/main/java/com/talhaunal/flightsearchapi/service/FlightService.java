package com.talhaunal.flightsearchapi.service;

import com.talhaunal.flightsearchapi.controller.request.FlightCreateRequest;
import com.talhaunal.flightsearchapi.domain.Airport;
import com.talhaunal.flightsearchapi.domain.Flight;
import com.talhaunal.flightsearchapi.repository.FlightRepository;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportService airportService;

    public FlightService(FlightRepository flightRepository,
                         AirportService airportService) {
        this.flightRepository = flightRepository;
        this.airportService = airportService;
    }

    public void create(FlightCreateRequest request) {
        Airport departureAirport = airportService.findById(request.getDepartureAirportId());
        Airport returnAirport = null;
        if (request.getReturnAirportId() != null) {
            returnAirport = airportService.findById(request.getReturnAirportId());
        }
        Flight flight = Flight.Builder.aFlight()
                .departureDate(request.getDepartureDate())
                .returnDate(request.getReturnDate())
                .amount(request.getAmount())
                .departureAirport(departureAirport)
                .returnAirport(returnAirport)
                .build();

        flightRepository.save(flight);
    }

}
