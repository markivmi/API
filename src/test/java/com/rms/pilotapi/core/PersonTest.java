package com.rms.pilotapi.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rms.pilotapi.TestUtils;
import io.dropwizard.jackson.Jackson;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.dropwizard.testing.FixtureHelpers.fixture;

public class PersonTest {

    private static ObjectMapper MAPPER;
    private static Person validDummyPerson = TestUtils.getRightDummyPerson(123);
    private static Logger lOGGER = LoggerFactory.getLogger(PersonTest.class);

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

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
        assert (MAPPER.writeValueAsString(validDummyPerson).equalsIgnoreCase(fixture("fixtures/person.json")));
    }

    @Test
    public void shouldDeserializeFromJSON() throws Exception {
        Person p = MAPPER.readValue(fixture("fixtures/person.json"), Person.class);
        assert (p.equals(validDummyPerson));
    }
    //endregion

    //region Negative tests
    @Test
    public void shouldNotSerializeToJSON() throws Exception {
        for (TestUtils.WRONG w : TestUtils.WRONG.values()) {
            Person wrongDummyPerson = TestUtils.getWrongDummyPerson(w);
            assert (!MAPPER.writeValueAsString(wrongDummyPerson).equalsIgnoreCase(fixture("fixtures/person.json")));
        }

    }

    @Test
    public void shouldNotDeserializeFromJSON() throws Exception {
        Person p = MAPPER.readValue(fixture("fixtures/person.json"), Person.class);
        for (TestUtils.WRONG w : TestUtils.WRONG.values()) {
            Person wrongDummyPerson = TestUtils.getWrongDummyPerson(w);
            assert (!p.equals(wrongDummyPerson));
        }


    }
    //endregion

    //region Performance tests
    @Test
    @PerfTest(invocations = 100, threads = 10)
    @Required(average = 100, max = 150)
    public void shouldSerializeToJSONPerf() throws Exception {
        MAPPER.writeValueAsString(validDummyPerson);
    }

    @Test
    @PerfTest(invocations = 100, threads = 10)
    @Required(average = 1500, max = 2000)
    public void shouldDeserializeFromJSONPerf() throws Exception {
        Person p = MAPPER.readValue(fixture("fixtures/person.json"), Person.class);
        assert (p.equals(validDummyPerson));
    }
    //endregion
}