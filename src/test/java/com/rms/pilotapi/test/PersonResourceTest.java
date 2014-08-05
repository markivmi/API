package com.rms.pilotapi.test;

import com.rms.pilotapi.core.Address;
import com.rms.pilotapi.core.Coordinates;
import com.rms.pilotapi.core.Person;
import com.rms.pilotapi.dao.PersonDao;
import com.rms.pilotapi.resources.PersonResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class PersonResourceTest {
    private static final PersonDao personDao = mock(PersonDao.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PersonResource(personDao))
            .build();

    private static Person getDummyPerson() {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude("23N");
        coordinates.setLongitude("46S");

        Address address = new Address();
        address.setCity("Fremont");
        address.setStreet("Mowry Blvd");
        address.setZip("94538");
        address.setCoordinates(coordinates);

        Person person = new Person();
        person.setName("John Doe");
        person.setBirthDateTime(new DateTime("2012-11-21T13:01:33.568Z"));
        person.setAge(10);
        person.setAddress(address);
        return person;
    }

    private final Person person = getDummyPerson();

    @Before
    public void setup() {
        when(personDao.getPerson(eq(123))).thenReturn(person);
        // we have to reset the mock after each test because of the
        // @ClassRule, or use a @Rule as mentioned below.
        reset(personDao);
    }

    public void testGetPerson() {
        assertThat(resources.client().resource("/persons/123").get(Person.class))
                .isEqualTo(person);
        verify(personDao).getPerson(123);
    }

    public void testCreatePerson() throws Exception {

    }

    public void testUpdatePerson() throws Exception {

    }

    public void testDeletePerson() throws Exception {

    }
}
