package com.talhaunal.flightsearchapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talhaunal.flightsearchapi.config.JwtAuthenticationFilter;
import com.talhaunal.flightsearchapi.controller.request.FlightCreateRequest;
import com.talhaunal.flightsearchapi.controller.request.FlightUpdateRequest;
import com.talhaunal.flightsearchapi.controller.response.FlightDto;
import com.talhaunal.flightsearchapi.service.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = FlightController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class))
@AutoConfigureMockMvc(addFilters = false)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FlightService flightService;

    @Test
    void it_should_create() throws Exception {
        FlightCreateRequest request = new FlightCreateRequest();

        mockMvc.perform(post("/v1/flights")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(flightService).create(any(FlightCreateRequest.class));
    }

    @Test
    void it_should_get() throws Exception {
        Long flightId = 1L;
        FlightDto expectedFlightDto = new FlightDto();
        when(flightService.get(flightId)).thenReturn(expectedFlightDto);

        mockMvc.perform(get("/v1/flights/{id}", flightId))
                .andExpect(status().isOk());

        verify(flightService).get(flightId);
    }

    @Test
    void it_should_delete() throws Exception {
        Long flightId = 1L;

        mockMvc.perform(delete("/v1/flights/{id}", flightId))
                .andExpect(status().isOk());

        verify(flightService).delete(flightId);
    }

    @Test
    void it_should_update() throws Exception {
        Long flightId = 1L;
        FlightUpdateRequest request = new FlightUpdateRequest();

        mockMvc.perform(put("/v1/flights/{id}", flightId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(flightService).update(eq(1L), any(FlightUpdateRequest.class));
    }

    @Test
    void it_should_search() throws Exception {
        when(flightService.search(any(), any(), any(), any()))
                .thenReturn(Collections.singletonList(new FlightDto()));

        mockMvc.perform(get("/v1/flights/search")
                        .param("departureAirportId", "1")
                        .param("returnAirportId", "2")
                        .param("departureDate", "2024-01-24")
                        .param("returnDate", "2024-02-23"))
                .andExpect(status().isOk());

        verify(flightService).search(1L, 2L, LocalDate.of(2024, 1, 24), LocalDate.of(2024, 2, 23));
    }
}