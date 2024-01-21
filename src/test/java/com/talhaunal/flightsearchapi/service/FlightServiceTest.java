package com.talhaunal.flightsearchapi.service;

import com.talhaunal.flightsearchapi.controller.request.FlightCreateRequest;
import com.talhaunal.flightsearchapi.controller.request.FlightUpdateRequest;
import com.talhaunal.flightsearchapi.domain.Airport;
import com.talhaunal.flightsearchapi.domain.Flight;
import com.talhaunal.flightsearchapi.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @InjectMocks
    private FlightService flightService;

    @Mock
    private AirportService airportService;

    @Mock
    private FlightRepository flightRepository;

    @Test
    void it_should_update() {
        //given
        var request = new FlightUpdateRequest();
        request.setAmount(BigDecimal.valueOf(500));
        request.setDepartureAirportId(105L);
        var flight = Flight.Builder.aFlight().build();
        var departureAirport = new Airport();
        when(flightRepository.findById(3L)).thenReturn(Optional.of(flight));
        when(airportService.findById(105L)).thenReturn(departureAirport);

        //when
        flightService.update(3L, request);

        //then
        verify(airportService).findById(105L);
        verify(flightRepository).findById(3L);
        verify(flightRepository).save(flight);
        assertThat(flight.getAmount()).isEqualTo(BigDecimal.valueOf(500));
        assertThat(flight.getDepartureAirport()).isEqualTo(departureAirport);
    }

    @Test
    void it_should_delete() {
        //given

        //when
        flightService.delete(3L);

        //then
        verify(flightRepository).deleteById(3L);
    }

    @Test
    void it_should_create() {
        //given
        var request = new FlightCreateRequest();

        //when
        flightService.create(request);

        //then
        verify(flightRepository).save(any(Flight.class));
    }

    @Test
    void it_should_get() {
        //given
        var flight = Flight.Builder.aFlight().id(3L).build();
        when(flightRepository.findById(3L)).thenReturn(Optional.of(flight));

        //when
        var result = flightService.get(3L);

        //then
        verify(flightRepository).findById(3L);
        assertThat(result.getId()).isEqualTo(3L);
    }
}