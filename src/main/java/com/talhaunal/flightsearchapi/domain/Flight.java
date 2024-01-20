package com.talhaunal.flightsearchapi.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure_date", nullable = false)
    private Instant departureDate;

    @Column(name = "return_date")
    private Instant returnDate;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id", nullable = false)
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "return_airport_id")
    private Airport returnAirport;

    @Column(name = "amount", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(id, flight.id) && Objects.equals(departureDate, flight.departureDate) && Objects.equals(returnDate, flight.returnDate) &&
               Objects.equals(departureAirport, flight.departureAirport) && Objects.equals(returnAirport, flight.returnAirport) &&
               Objects.equals(amount, flight.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departureDate, returnDate, departureAirport, returnAirport, amount);
    }

    @Override
    public String toString() {
        return "Flight{" +
               "id=" + id +
               ", departureDate=" + departureDate +
               ", returnDate=" + returnDate +
               ", departureAirport=" + departureAirport +
               ", returnAirport=" + returnAirport +
               ", amount=" + amount +
               '}';
    }
}
