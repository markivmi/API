package com.rms.pilotapi.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rms.pilotapi.core.Address;
import com.rms.pilotapi.core.Coordinates;
import com.rms.pilotapi.core.Person;
import io.dropwizard.jackson.Jackson;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.dropwizard.testing.FixtureHelpers.fixture;


public class PersonTest {

    private static ObjectMapper MAPPER;
    private static Person person;
    private static Logger lOGGER = LoggerFactory.getLogger(PersonTest.class);

    @BeforeClass
    public static void setup() {
        lOGGER.info("Person Test Started");
        MAPPER = Jackson.newObjectMapper();
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude("23N");
        coordinates.setLongitude("46S");

        Address address = new Address();
        address.setCity("Fremont");
        address.setStreet("Mowry Blvd");
        address.setZip("94538");
        address.setCoordinates(coordinates);

        person = new Person();
        person.setName("John Doe");
        person.setBirthDateTime(new DateTime("2012-11-21T13:01:33.568Z", DateTimeZone.UTC));
        person.setAge(10);
        person.setAddress(address);
        person.setId(123);

        lOGGER.info("Person Test End");
    }

    @Test
    public void serializesToJSON() throws Exception {
        assert (MAPPER.writeValueAsString(person).equalsIgnoreCase(fixture("fixtures/person.json")));
    }

    @Test
    public void deserialiseFromJSON() throws Exception {
        Person p = MAPPER.readValue(fixture("fixtures/person.json"), Person.class);
        assert (p.equals(person));
    }
}