package com.rms.pilotapi;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rms.auth.BasicAuthentication;
import com.rms.pilotapi.dao.PersonDao;
import com.rms.pilotapi.health.MongoHealthCheck;
import com.rms.pilotapi.resources.PersonResource;
import io.dropwizard.Application;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.net.UnknownHostException;

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
    public void run(PilotAPIConfiguration configuration, Environment environment) throws UnknownHostException {
        // Create new injector instance
        PilotAPIModule pilotAPIModule = new PilotAPIModule(configuration);
        Injector injector = Guice.createInjector(pilotAPIModule);

        // Register resources
        final PersonResource personResource = new PersonResource(injector.getInstance(PersonDao.class));
        environment.jersey().register(personResource);

        // Register database health check
        final MongoHealthCheck mongoHealthCheck = new MongoHealthCheck(pilotAPIModule.getDB());
        environment.healthChecks().register("database", mongoHealthCheck);

        //Set datetime serialization format to ISO-8601
        environment.getObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        environment.getObjectMapper().registerModule(new JodaModule());

        //Register basic authenticator
        environment.jersey().register(new BasicAuthProvider<Boolean>(new BasicAuthentication(), "Authentication"));
    }
}
