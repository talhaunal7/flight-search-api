package com.talhaunal.flightsearchapi.controller;

import com.talhaunal.flightsearchapi.controller.request.AirportCreateRequest;
import com.talhaunal.flightsearchapi.service.AirportService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/airports")
public class AirportController {

    private static final Logger log = getLogger(AirportController.class);

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public void create(@RequestBody AirportCreateRequest request) {
        log.info("Creating airport: {}", request);
        airportService.create(request);
    }
}
