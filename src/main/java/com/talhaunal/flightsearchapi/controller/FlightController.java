package com.talhaunal.flightsearchapi.controller;

import com.talhaunal.flightsearchapi.controller.request.FlightCreateRequest;
import com.talhaunal.flightsearchapi.controller.request.FlightUpdateRequest;
import com.talhaunal.flightsearchapi.controller.response.FlightDto;
import com.talhaunal.flightsearchapi.service.FlightService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

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

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public FlightDto get(@PathVariable Long id) {
        log.info("Getting flight: {}", id);
        return flightService.get(id);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Deleting flight: {}", id);
        flightService.delete(id);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody FlightUpdateRequest request) {
        log.info("Updating flight: {}", id);
        flightService.update(id, request);
    }


}
