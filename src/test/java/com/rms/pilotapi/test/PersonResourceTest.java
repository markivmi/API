package com.rms.pilotapi.test;

import com.rms.auth.BasicAuthenticator;
import com.rms.pilotapi.core.Person;
import com.rms.pilotapi.dao.PersonDao;
import com.rms.pilotapi.resources.PersonResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static org.mockito.Mockito.*;

public class PersonResourceTest {

    private static final PersonDao personDao = mock(PersonDao.class);
    private final Person person = TestUtils.getDummyPerson(123);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PersonResource(personDao))
            .addProvider(new BasicAuthProvider<>(new BasicAuthenticator(), "TestAuthenticator"))
            .build();

    @Before
    public void setup() {
        when(personDao.getPerson(anyInt())).thenReturn(person);
        when(personDao.createPerson(any(Person.class))).thenReturn(person);
        when(personDao.updatePerson(anyInt(), any(Person.class))).thenReturn(person);
        when(personDao.deletePerson(anyInt())).thenReturn(true);
    }

    @Test
    public void testGetPerson() {
        Person personFromAPI = resources.client().resource("/persons/123").get(Person.class);
        assert (personFromAPI.equals(person));
    }

    @Test
    public void testCreatePerson() throws Exception {
        Person personFromAPI = resources.client().resource("/persons").type(MediaType.APPLICATION_JSON_TYPE).post(Person.class, person);
        assert (personFromAPI.equals(person));
    }

    @Test
    public void testUpdatePerson() throws Exception {
        Person personFromAPI = resources.client().resource("/persons/123").type(MediaType.APPLICATION_JSON_TYPE).put(Person.class, person);
        assert (personFromAPI.equals(person));
    }

    @Test
    public void testDeletePerson() throws Exception {
        Boolean booleanFromAPI = resources.client().resource("/persons/123").delete(Boolean.class);
        assert (booleanFromAPI);
    }

    @Test
    public void testAuthPersonPositive() throws Exception {
        Client client = resources.client();
        client.addFilter(new HTTPBasicAuthFilter("user", "secret"));
        Person personFromAPI = client.resource("/persons/getAuthPerson/123").get(Person.class);
        assert (personFromAPI.equals(person));
    }

    @Test
    public void testAuthPersonNegative() throws Exception {
        Client client = resources.client();
        client.addFilter(new HTTPBasicAuthFilter("user", "wrong-secret"));
        try {
            client.resource("/persons/getAuthPerson/123").get(Person.class);
        } catch (UniformInterfaceException e) {
            assert (e.getResponse().getStatus() == 401);
        }
    }

    @Test
    public void testAuthPersonNegativeNoHeader() throws Exception {
        Client client = resources.client();
        try {
            client.resource("/persons/getAuthPerson/123").get(Person.class);
        } catch (UniformInterfaceException e) {
            assert (e.getResponse().getStatus() == 401);
        }
    }
}
