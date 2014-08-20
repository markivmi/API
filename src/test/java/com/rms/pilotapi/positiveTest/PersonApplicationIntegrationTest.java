package com.rms.pilotapi.positiveTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rms.pilotapi.PilotAPIApplication;
import com.rms.pilotapi.PilotAPIConfiguration;
import com.rms.pilotapi.TestUtils;
import com.rms.pilotapi.core.Person;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.io.IOException;

public class PersonApplicationIntegrationTest {

    private static ObjectMapper objectMapper;

    @ClassRule
    public static final DropwizardAppRule<PilotAPIConfiguration> RULE =
            new DropwizardAppRule<>(PilotAPIApplication.class, "database-config.yml");

    @BeforeClass
    public static void setObjectMapper() {
        objectMapper = RULE.getEnvironment().getObjectMapper();
    }

    int localPort;
    Person inputPerson, outputPerson;
    Client client;
    ClientResponse response;


    @Before
    public void setup() {
        localPort = RULE.getLocalPort();
        inputPerson = TestUtils.getRightDummyPerson(0);
        client = new Client();
        client.addFilter(new HTTPBasicAuthFilter("user", "secret"));
    }

    @Test
    public void createPersonTest() {
        String url = String.format("http://localhost:%d/persons/", localPort);
        response = client.resource(url).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, inputPerson);

        String jsonString = response.getEntity(String.class);
        try {
            outputPerson = objectMapper.readValue(jsonString, Person.class);
        } catch (IOException e) {
            e.printStackTrace();
            assert (false);
        }
        assert(outputPerson.getId() > 0);
        assert(response.getStatus() == 201);
    }

}
