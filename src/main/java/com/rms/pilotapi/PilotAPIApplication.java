package com.rms.pilotapi;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rms.auth.BasicAuth;
import com.rms.pilotapi.core.Person;
import com.rms.pilotapi.dao.PersonDao;
import com.rms.pilotapi.health.DatabaseHealthCheck;
import com.rms.pilotapi.resources.PersonResource;
import io.dropwizard.Application;
import io.dropwizard.auth.basic.BasicAuthProvider;
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
        // Create new injector instance
        Injector injector = Guice.createInjector(new PilotAPIModule());

        // Register resources
        final PersonResource personResource = new PersonResource(injector.getInstance(PersonDao.class));
        environment.jersey().register(personResource);

        // Register database health check
        final DatabaseHealthCheck databaseHealthCheck = new DatabaseHealthCheck(configuration.getConnectionString());
        environment.healthChecks().register("database", databaseHealthCheck);

        //Set datetime serialization format to ISO-8601
        environment.getObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        //Add authenticator
        environment.jersey().register(new BasicAuthProvider<Person>(new BasicAuth(), "Authentication"));
    }
}
