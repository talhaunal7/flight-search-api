package com.talhaunal.flightsearchapi.controller.response;

import com.talhaunal.flightsearchapi.domain.Airport;
import com.talhaunal.flightsearchapi.domain.Flight;

import java.math.BigDecimal;
import java.time.Instant;

public class FlightDto {
    private Long id;

    private Instant departureDate;

    private Instant returnDate;

    private Airport departureAirport;

    private Airport returnAirport;

    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Instant departureDate) {
        this.departureDate = departureDate;
    }

    public Instant getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Instant returnDate) {
        this.returnDate = returnDate;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getReturnAirport() {
        return returnAirport;
    }

    public void setReturnAirport(Airport returnAirport) {
        this.returnAirport = returnAirport;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static FlightDto convertToDto(Flight flight) {
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId());
        flightDto.setAmount(flight.getAmount());
        flightDto.setDepartureAirport(flight.getDepartureAirport());
        flightDto.setReturnAirport(flight.getReturnAirport());
        flightDto.setReturnAirport(flight.getReturnAirport());
        flightDto.setDepartureDate(flight.getDepartureDate());
        flightDto.setReturnDate(flight.getReturnDate());
        return flightDto;

    }
}
