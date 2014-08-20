package com.rms.pilotapi.negativeTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rms.pilotapi.TestUtils;
import com.rms.pilotapi.core.Person;
import io.dropwizard.jackson.Jackson;
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

        lOGGER.info("Person Test End");
    }

    @Test
    public void serializesToJSON() throws Exception {
        for(TestUtils.WRONG w: TestUtils.WRONG.values()) {
            person = TestUtils.getWrongDummyPerson(w);
            assert (!MAPPER.writeValueAsString(person).equalsIgnoreCase(fixture("fixtures/person.json")));
        }

    }

    @Test
    public void deserialiseFromJSON() throws Exception {
        Person p = MAPPER.readValue(fixture("fixtures/person.json"), Person.class);
        for(TestUtils.WRONG w: TestUtils.WRONG.values()) {
            person = TestUtils.getWrongDummyPerson(w);
            assert (!p.equals(person));
        }
    }
}