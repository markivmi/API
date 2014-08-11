package com.rms.pilotapi.test;

import com.rms.pilotapi.core.Address;
import com.rms.pilotapi.core.Coordinates;
import com.rms.pilotapi.core.Person;
import com.rms.pilotapi.dao.PersonDao;
import com.rms.pilotapi.resources.PersonResource;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.fest.assertions.Assertions;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class PersonResourceTest {
    private static final PersonDao personDao = mock(PersonDao.class);

    private static boolean equals(Address a, Address b) {
        if (a == null && b == null) {
            return true;
        }

        if(!a.getCity().equalsIgnoreCase(b.getCity()) ||
                !a.getStreet().equalsIgnoreCase(b.getStreet()) ||
                !a.getZip().equalsIgnoreCase(b.getZip())) {
            return false;
        }
        if(a.getCoordinates() == null && b.getCoordinates() == null) {
            return true;
        }

        Coordinates c = a.getCoordinates();
        Coordinates d = b.getCoordinates();
        if(!c.getLatitude().equalsIgnoreCase(d.getLatitude()) ||
                !c.getLongitude().equalsIgnoreCase(d.getLongitude())) {
            return false;
        }

        return true;
    }

    private static boolean equals(Person p, Person q) {
        if (p.getId() != q.getId()
                || !p.getName().equalsIgnoreCase(q.getName())
                || !equals(p.getAddress(), q.getAddress())
                || p.getAge() != q.getAge()
                || !p.getBirthDateTime().isEqual(q.getBirthDateTime())) {
            return false;
        }
        return true;
    }

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PersonResource(personDao))
            .build();

    private static Person getDummyPerson(int id) {
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
        person.setId(id);
        person.setAge(10);
        person.setAddress(address);
        return person;
    }

    private final Person person = getDummyPerson(123);
    private final Person authPerson = getDummyPerson(234);

    @Before
    public void setup() {
        when(personDao.getPerson(eq(123))).thenReturn(person);
        when(personDao.getPerson(eq(234))).thenReturn(authPerson);
        //reset(personDao);
    }

    @Test
    public void authenticatedTestGetPositive() {

        Person p = resources
                .client()
                .resource("/persons/getAuthPerson/234")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic dGVzdDpzZWNyZXQ=")
                .get(Person.class);

        assert (equals(p, authPerson));
    }

    @Test
    public void testGetPerson() {
        Person p = resources.client().resource("/persons/123").get(Person.class);
        assert (equals(p, person));
    }
}
