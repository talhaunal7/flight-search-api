package com.talhaunal.flightsearchapi.service;

import com.talhaunal.flightsearchapi.controller.request.AirportCreateRequest;
import com.talhaunal.flightsearchapi.controller.request.AirportUpdateRequest;
import com.talhaunal.flightsearchapi.controller.response.AirportDto;
import com.talhaunal.flightsearchapi.domain.Airport;
import com.talhaunal.flightsearchapi.repository.AirportRepository;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public void create(AirportCreateRequest request) {
        Airport airport = new Airport();
        airport.setCity(request.getCity());

        airportRepository.save(airport);
    }

    public AirportDto get(Long id) {
        Airport airport = findById(id);
        return AirportDto.convertToDto(airport);
    }

    public void delete(Long id) {
        airportRepository.deleteById(id);
    }

    public void update(Long id, AirportUpdateRequest request) {
        Airport airport = findById(id);
        airport.setCity(request.getCity());
        airportRepository.save(airport);
    }

    public Airport findById(Long id) {
        return airportRepository.findById(id).orElseThrow(() -> new RuntimeException("airport.not.found"));
    }
}
