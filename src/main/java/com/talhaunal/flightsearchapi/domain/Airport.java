package com.talhaunal.flightsearchapi.domain;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "airport")
public class Airport {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city", nullable = false, unique = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Objects.equals(id, airport.id) && Objects.equals(city, airport.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city);
    }

    @Override
    public String toString() {
        return "Airport{" +
               "id=" + id +
               ", city='" + city + '\'' +
               '}';
    }
}
