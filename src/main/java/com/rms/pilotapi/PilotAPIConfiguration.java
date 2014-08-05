package com.rms.pilotapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.validation.Validated;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;

//TODO : Guice it !!
public class PilotAPIConfiguration extends Configuration {
    @NotEmpty
    private String host = "localhost";

    @Valid
    private int port = 8080;

    @NotEmpty
    private String database = "pilotDb";

    public PilotAPIConfiguration() {
    }

    @JsonProperty
    public String getHost() {
        return host;
    }

    @JsonProperty
    public void setHost(String host) {
        this.host = host;
    }

    @JsonProperty
    public int getPort() {
        return port;
    }

    @JsonProperty
    public void setPort(int port) {
        this.port = port;
    }

    @JsonProperty
    public String getDatabase() {
        return database;
    }

    @JsonProperty
    public void setDatabase(String database) {
        this.database = database;
    }
}
