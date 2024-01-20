package com.talhaunal.flightsearchapi.controller.request;

public class AirportCreateRequest {
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "AirportCreateRequest{" +
               "city='" + city + '\'' +
               '}';
    }
}
