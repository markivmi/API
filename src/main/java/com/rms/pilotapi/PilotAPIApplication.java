package com.rms.pilotapi;

import com.rms.pilotapi.health.DatabaseHealthCheck;
import com.rms.pilotapi.resources.PersonResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PilotAPIApplication extends Application<PilotAPIConfiguration> {
    public static void main(String[] args) throws Exception {
        new PilotAPIApplication().run(args);
    }

    @Override
    public String getName() {        
        return "pilot-api";
    }

    @Override
    public void initialize(Bootstrap<PilotAPIConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(PilotAPIConfiguration configuration, Environment environment) {
        final PersonResource personResource = new PersonResource();
        final DatabaseHealthCheck databaseHealthCheck = new DatabaseHealthCheck(configuration.getConnectionString());
        environment.healthChecks().register("database", databaseHealthCheck);
        environment.jersey().register(personResource);

        //TODO
        //Set datetime serialization format to ISO-8601
        //environment.getObjectMapper().configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}
