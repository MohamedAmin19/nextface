package com.nextface.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class PortalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    @NotBlank(message = "PhoneNumber is required")
    @Pattern(regexp = "^01\\d{9}$", message = "Phone number must be 11 digits and start with 01")
    private String phoneNumber;

    @NotBlank(message = "Organization Name is required")
    private String organizationName;

    @NotBlank(message = "Speciality is required")
    private String speciality;


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
}
