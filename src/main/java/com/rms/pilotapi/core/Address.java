package com.rms.pilotapi.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    private String street;
    private String city;
    private String zip;
    private Coordinates coordinates;

    @JsonProperty
    public String getStreet() {
        return street;
    }

    @JsonProperty
    public void setStreet(String street) {
        this.street = street;
    }

    @JsonProperty
    public String getCity() {
        return city;
    }

    @JsonProperty
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty
    public String getZip() {
        return zip;
    }

    @JsonProperty
    public void setZip(String zip) {
        this.zip = zip;
    }

    @JsonProperty
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @JsonProperty
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
