package com.talhaunal.flightsearchapi.controller.response;

import com.talhaunal.flightsearchapi.domain.Airport;

public class AirportDto {
    private Long id;
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static AirportDto convertToDto(Airport airport) {
        AirportDto airportDto = new AirportDto();
        airportDto.setId(airport.getId());
        airportDto.setCity(airport.getCity());
        return airportDto;
    }
}
