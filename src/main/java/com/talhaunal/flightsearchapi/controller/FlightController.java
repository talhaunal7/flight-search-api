package com.talhaunal.flightsearchapi.controller;

import com.talhaunal.flightsearchapi.controller.request.FlightCreateRequest;
import com.talhaunal.flightsearchapi.service.FlightService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/flights")
public class FlightController {

    private static final Logger log = getLogger(FlightController.class);

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public void create(@RequestBody FlightCreateRequest request) {
        log.info("Creating flight: {}", request);
        flightService.create(request);

    }

}
