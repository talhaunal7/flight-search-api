package com.talhaunal.flightsearchapi.controller;

import com.talhaunal.flightsearchapi.controller.request.FlightCreateRequest;
import com.talhaunal.flightsearchapi.controller.request.FlightUpdateRequest;
import com.talhaunal.flightsearchapi.controller.response.FlightDto;
import com.talhaunal.flightsearchapi.service.FlightService;
import org.slf4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    @PreAuthorize("hasAuthority('ADMIN')")
    public void create(@RequestBody FlightCreateRequest request) {
        log.info("Creating flight: {}", request);
        flightService.create(request);
    }

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FlightDto get(@PathVariable Long id) {
        log.info("Getting flight: {}", id);
        return flightService.get(id);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        log.info("Deleting flight: {}", id);
        flightService.delete(id);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void update(@PathVariable Long id, @RequestBody FlightUpdateRequest request) {
        log.info("Updating flight: {}", id);
        flightService.update(id, request);
    }

    @ResponseStatus(OK)
    @GetMapping("/search")
    public List<FlightDto> search(@RequestParam Long departureAirportId,
                                  @RequestParam(required = false) Long returnAirportId,
                                  @RequestParam LocalDate departureDate,
                                  @RequestParam(required = false) LocalDate returnDate) {
        return flightService.search(departureAirportId, returnAirportId, departureDate, returnDate);
    }


}
