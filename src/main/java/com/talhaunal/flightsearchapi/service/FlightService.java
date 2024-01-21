package com.talhaunal.flightsearchapi.service;

import com.talhaunal.flightsearchapi.controller.request.FlightCreateRequest;
import com.talhaunal.flightsearchapi.controller.request.FlightUpdateRequest;
import com.talhaunal.flightsearchapi.controller.response.FlightDto;
import com.talhaunal.flightsearchapi.domain.Airport;
import com.talhaunal.flightsearchapi.domain.Flight;
import com.talhaunal.flightsearchapi.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

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

    public void update(Long id, FlightUpdateRequest request) {
        Flight flight = findById(id);

        Airport departureAirport = airportService.findById(request.getDepartureAirportId());
        Airport returnAirport = null;
        if (request.getReturnAirportId() != null) {
            returnAirport = airportService.findById(request.getReturnAirportId());
        }
        flight.setAmount(request.getAmount());
        flight.setDepartureDate(request.getDepartureDate());
        flight.setReturnDate(request.getReturnDate());
        flight.setDepartureAirport(departureAirport);
        flight.setReturnAirport(returnAirport);

        flightRepository.save(flight);
    }

    public FlightDto get(Long id) {
        Flight flight = findById(id);
        return FlightDto.convertToDto(flight);
    }

    public void delete(Long id) {
        flightRepository.deleteById(id);
    }

    public List<FlightDto> search(Long departureAirportId,
                                  Long returnAirportId,
                                  LocalDate departureDate,
                                  LocalDate returnDate) {
        Airport departureAirport = airportService.findById(departureAirportId);
        Airport returnAirport = null;
        if (returnAirportId != null && returnDate != null) {
            returnAirport = airportService.findById(returnAirportId);
        }
        Instant departureDateStartOfDay = departureDate.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant departureDateEndOfDay = departureDateStartOfDay.plus(1, ChronoUnit.DAYS);

        List<Flight> flights = flightRepository.findAllByDepartureAirportAndReturnAirportAndDepartureDateGreaterThanEqualAndDepartureDateLessThan(
                departureAirport,
                returnAirport,
                departureDateStartOfDay,
                departureDateEndOfDay);

        return flights.stream().map(FlightDto::convertToDto).toList();

    }

    private Flight findById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new RuntimeException("flight.not.found"));

    }

}
