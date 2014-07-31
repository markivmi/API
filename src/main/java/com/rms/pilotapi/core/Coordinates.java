package com.rms.pilotapi.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinates {
    private String latitude;

    @JsonProperty
    public String getLatitude() {
        return latitude;
    }

    @JsonProperty
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    private String longitude;

    @JsonProperty
    public String getLongitude() {
        return longitude;
    }

    @JsonProperty
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
