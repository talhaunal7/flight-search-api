package com.talhaunal.flightsearchapi.controller;

import com.talhaunal.flightsearchapi.controller.request.AirportCreateRequest;
import com.talhaunal.flightsearchapi.controller.request.AirportUpdateRequest;
import com.talhaunal.flightsearchapi.controller.response.AirportDto;
import com.talhaunal.flightsearchapi.service.AirportService;
import org.slf4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

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
    @PreAuthorize("hasAuthority('ADMIN')")
    public void create(@RequestBody AirportCreateRequest request) {
        log.info("Creating airport: {}", request);
        airportService.create(request);
    }

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AirportDto get(@PathVariable Long id) {
        log.info("Getting airport: {}", id);
        return airportService.get(id);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        log.info("Deleting airport: {}", id);
        airportService.delete(id);
    }

    @ResponseStatus(OK)
    @PutMapping(("/{id}"))
    @PreAuthorize("hasAuthority('ADMIN')")
    public void update(@PathVariable Long id, @RequestBody AirportUpdateRequest request) {
        log.info("Updating airport: {}", request);
        airportService.update(id, request);
    }

}
