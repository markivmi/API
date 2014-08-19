package com.rms.pilotapi.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class Address {

    @NotNull
    private String street;

    @NotNull
    private String city;

    @Max(99999)
    private int zip;

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
    public int getZip() {
        return zip;
    }

    @JsonProperty
    public void setZip(int zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (zip != address.zip) return false;
        if (!city.equals(address.city)) return false;
        if (coordinates != null ? !coordinates.equals(address.coordinates) : address.coordinates != null) return false;
        return street.equals(address.street);

    }

    @Override
    public int hashCode() {
        int result = street.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + zip;
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        return result;
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
