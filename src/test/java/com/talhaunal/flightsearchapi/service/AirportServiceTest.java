package com.talhaunal.flightsearchapi.service;

import com.talhaunal.flightsearchapi.controller.request.AirportCreateRequest;
import com.talhaunal.flightsearchapi.controller.request.AirportUpdateRequest;
import com.talhaunal.flightsearchapi.domain.Airport;
import com.talhaunal.flightsearchapi.repository.AirportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    @InjectMocks
    private AirportService airportService;

    @Mock
    private AirportRepository airportRepository;

    @Test
    void it_should_delete() {
        //given

        //when
        airportService.delete(3L);

        //then
        verify(airportRepository).deleteById(3L);
    }

    @Test
    void it_should_update() {
        //given
        var airport = new Airport();
        var request = new AirportUpdateRequest();
        request.setCity("izmir");
        when(airportRepository.findById(3L)).thenReturn(Optional.of(airport));

        //when
        airportService.update(3L, request);

        //then
        verify(airportRepository).findById(3L);
        verify(airportRepository).save(airport);
        assertThat(airport.getCity()).isEqualTo("izmir");
    }

    @Test
    void it_should_create() {
        //given
        var request = new AirportCreateRequest();
        request.setCity("istanbul");

        //when
        airportService.create(request);

        //then
        verify(airportRepository).save(any(Airport.class));
    }

    @Test
    void it_should_get() {
        //given
        var airport = new Airport();
        airport.setCity("istanbul");
        when(airportRepository.findById(3L)).thenReturn(Optional.of(airport));

        //when
        var result = airportService.get(3L);

        //then
        verify(airportRepository).findById(3L);
        assertThat(result.getCity()).isEqualTo("istanbul");
    }
}