package com.rms.pilotapi.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rms.pilotapi.TestUtils;
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

    //region Positive tests
    @Test
    public void shouldSerializeToJSON() throws Exception {
        person = TestUtils.getRightDummyPerson(123);
        assert (MAPPER.writeValueAsString(person).equalsIgnoreCase(fixture("fixtures/person.json")));
    }

    @Test
    public void shouldDeserializeFromJSON() throws Exception {
        person = TestUtils.getRightDummyPerson(123);
        Person p = MAPPER.readValue(fixture("fixtures/person.json"), Person.class);
        assert (p.equals(person));
    }
    //endregion

    //region Negative tests
    @Test
    public void shouldNotSerializeToJSON() throws Exception {
        for (TestUtils.WRONG w : TestUtils.WRONG.values()) {
            person = TestUtils.getWrongDummyPerson(w);
            assert (!MAPPER.writeValueAsString(person).equalsIgnoreCase(fixture("fixtures/person.json")));
        }

    }

    @Test
    public void shouldNotDeserializeFromJSON() throws Exception {
        Person p = MAPPER.readValue(fixture("fixtures/person.json"), Person.class);
        for (TestUtils.WRONG w : TestUtils.WRONG.values()) {
            person = TestUtils.getWrongDummyPerson(w);
            assert (!p.equals(person));
        }
    }
    //endregion
}