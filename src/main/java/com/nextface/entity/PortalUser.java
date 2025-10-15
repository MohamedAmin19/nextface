package com.nextface.entity;

import com.nextface.entity.enums.AirportName;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class PortalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String nationality;

    private String phoneNumber;

    private String organizationName;

    private String speciality;

    private String flightDetails;

    @Enumerated(EnumType.STRING)
    private AirportName airportName;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    public void setId(Long id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    public void setAirportName(AirportName airportName) { this.airportName = airportName; }
    public void setFlightDetails(String flightDetails) { this.flightDetails = flightDetails; }

    public String getEmail() {
        return email;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getNationality() {
        return nationality;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getOrganizationName() {
        return organizationName;
    }
    public String getSpeciality() {
        return speciality;
    }
    public AirportName getAirportName() { return airportName; }
    public String getFlightDetails() { return flightDetails; }
}
