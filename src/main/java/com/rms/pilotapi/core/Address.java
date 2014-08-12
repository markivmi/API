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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (coordinates != null ? !coordinates.equals(address.coordinates) : address.coordinates != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        return !(zip != null ? !zip.equals(address.zip) : address.zip != null);

    }

    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
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
