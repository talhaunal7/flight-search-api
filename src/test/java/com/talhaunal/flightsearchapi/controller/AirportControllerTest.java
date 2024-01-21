package com.talhaunal.flightsearchapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talhaunal.flightsearchapi.config.JwtAuthenticationFilter;
import com.talhaunal.flightsearchapi.controller.request.AirportCreateRequest;
import com.talhaunal.flightsearchapi.controller.request.AirportUpdateRequest;
import com.talhaunal.flightsearchapi.controller.response.AirportDto;
import com.talhaunal.flightsearchapi.service.AirportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = AirportController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class))
@AutoConfigureMockMvc(addFilters = false)
public class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AirportService airportService;

    @Test
    void testCreateAirport() throws Exception {
        AirportCreateRequest request = new AirportCreateRequest();

        mockMvc.perform(post("/v1/airports")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(airportService).create(any(AirportCreateRequest.class));
    }

    @Test
    void testGetAirport() throws Exception {
        Long airportId = 1L;
        AirportDto expectedAirportDto = new AirportDto();
        when(airportService.get(airportId)).thenReturn(expectedAirportDto);

        mockMvc.perform(get("/v1/airports/{id}", airportId))
                .andExpect(status().isOk());

        verify(airportService).get(airportId);
    }

    @Test
    void testDeleteAirport() throws Exception {
        Long airportId = 1L;

        mockMvc.perform(delete("/v1/airports/{id}", airportId))
                .andExpect(status().isOk());


        verify(airportService).delete(airportId);
    }

    @Test
    void testUpdateAirport() throws Exception {
        Long airportId = 1L;
        AirportUpdateRequest request = new AirportUpdateRequest();

        mockMvc.perform(put("/v1/airports/{id}", airportId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());


        verify(airportService).update(eq(1L), any(AirportUpdateRequest.class));
    }
}