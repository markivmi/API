package com.rms.pilotapi.test;

import com.rms.pilotapi.core.Address;
import com.rms.pilotapi.core.Coordinates;
import com.rms.pilotapi.core.Person;
import com.rms.pilotapi.dao.PersonDao;
import com.rms.pilotapi.resources.PersonResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static org.fest.assertions.api.Assertions.assertThat;
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
        person.setBirthDateTime(new DateTime("2012-11-21T13:01:33.568Z", DateTimeZone.UTC));
        person.setAge(10);
        person.setAddress(address);
        person.setId(123);
        return person;
    }

    private final Person person = getDummyPerson();

    @Before
    public void setup() {
        when(personDao.getPerson(anyInt())).thenReturn(person);
        when(personDao.createPerson(any(Person.class))).thenReturn(person);
        when(personDao.updatePerson(anyInt(), any(Person.class))).thenReturn(person);
        when(personDao.deletePerson(anyInt())).thenReturn(true);
        // we have to reset the mock after each test because of the
        // @ClassRule, or use a @Rule as mentioned below.
        //reset(personDao);
    }

    @Test
    public void testGetPerson() {
        Person personFromAPI = resources.client().resource("/persons/123").get(Person.class);
        assertThat(personFromAPI.equals(person));
        verify(personDao).getPerson(123);
    }

    @Test
    public void testCreatePerson() throws Exception {
        Person personFromAPI = resources.client().resource("/persons").type(MediaType.APPLICATION_JSON_TYPE).post(Person.class, person);
        assertThat(personFromAPI.equals(person));
        verify(personDao).createPerson(person);
    }

    @Test
    public void testUpdatePerson() throws Exception {
        Person personFromAPI = resources.client().resource("/persons/123").type(MediaType.APPLICATION_JSON_TYPE).put(Person.class, person);
        assertThat(personFromAPI.equals(person));
        verify(personDao).updatePerson(123, person);
    }

    @Test
    public void testDeletePerson() throws Exception {
        Boolean booleanFromAPI = resources.client().resource("/persons/123").delete(Boolean.class);
        assertThat(booleanFromAPI);
        verify(personDao).deletePerson(123);
    }
}
