package com.planner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class RoutePlan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;
    @NotNull
    private String planName;
    @NotNull
    private String startLocation;
    @NotNull
    private String endLocation;
    @NotNull
    private Double distance;
    private String description; // optional

    // Constructors
    public RoutePlan() {}

    public RoutePlan(String planName, String startLocation, String endLocation, String description, Double distance) {
        this.planName = planName;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.description = description;
        this.distance = distance;
    } // RoutePlan

    // getters and setters
    public Long getId() {
        return planId;
    } // getId

    public void setId(Long id) {
        this.planId = id;
    } // setId

    public String getName() {
        return planName;
    } // getName

    public void setName(String name) {
        this.planName = name;
    } //setName

    public String getStartLocation() {
        return startLocation;
    } // getStartLocation

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    } // setStartLocation

    public String getEndLocation() {
        return endLocation;
    } // getEndLocation

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    } // setEndLocation

    public Double getDistance() {
        return distance;
    } // getDistance

    public void setDistance(Double distance) {
        this.distance = distance;
    } // setDistance

    public String getDescription() {
        return description;
    } // getDescription

    public void setDescription(String description) {
        this.description = description;
    } // setDescription
} //class
