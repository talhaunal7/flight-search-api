package com.talhaunal.flightsearchapi.controller.request;

import java.math.BigDecimal;
import java.time.Instant;

public class FlightUpdateRequest {
    private Instant departureDate;
    private Instant returnDate;
    private Long departureAirportId;
    private Long returnAirportId;
    private BigDecimal amount;

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

    public Long getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(Long departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public Long getReturnAirportId() {
        return returnAirportId;
    }

    public void setReturnAirportId(Long returnAirportId) {
        this.returnAirportId = returnAirportId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
