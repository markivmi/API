package com.rms.pilotapi.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinates {
    private String latitude;
    private String longitude;

    @JsonProperty
    public String getLatitude() {
        return latitude;
    }

    @JsonProperty
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;

        Coordinates that = (Coordinates) o;

        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = latitude != null ? latitude.hashCode() : 0;
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }

    @JsonProperty
    public String getLongitude() {
        return longitude;
    }

    @JsonProperty
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
