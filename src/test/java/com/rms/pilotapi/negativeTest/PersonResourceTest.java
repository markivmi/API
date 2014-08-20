package com.rms.pilotapi.negativeTest;

import com.rms.auth.BasicAuthenticator;
import com.rms.interceptor.RequestFilter;
import com.rms.interceptor.ResponseFilter;
import com.rms.pilotapi.TestUtils;
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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ws.rs.core.MediaType;

import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class PersonResourceTest {

    private static final PersonDao personDao = mock(PersonDao.class);
    private static final long correctId = 123;
    private final Person correctDummyPerson = TestUtils.getRightDummyPerson(correctId);
    private Person wrongDummyPerson;

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PersonResource(personDao))
            .addProvider(new BasicAuthProvider<>(new BasicAuthenticator(), "TestAuthenticator"))
            .addProvider(new RequestFilter())
            .addProvider(new ResponseFilter())
            .build();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        when(personDao.getPerson(eq(correctId))).thenReturn(correctDummyPerson);
        when(personDao.createPerson(any(Person.class))).thenReturn(correctDummyPerson);
        when(personDao.updatePerson(eq(correctId), any(Person.class))).thenReturn(correctDummyPerson);
        when(personDao.deletePerson(eq(correctId))).thenReturn(true);
        expectedException.expect(UniformInterfaceException.class);
    }

    @Test
    public void testGetNonExistingPerson() {
        resources.client().resource("/persons/1").get(Person.class);
        expectedException.expectMessage(String.valueOf(TestUtils.NOT_FOUND));
    }

    @Test
    public void testGetInvalidIdPerson() {
        Person personFromAPI = resources.client().resource("/persons/0").get(Person.class);
        assert (personFromAPI.equals(correctDummyPerson));
    }

    @Test
    public void testCreatePersonWithIncorrectInputs() throws Exception {
        Person personFromAPI = resources.client().resource("/persons").type(MediaType.APPLICATION_JSON_TYPE).post(Person.class, correctDummyPerson);
        assert (personFromAPI.equals(correctDummyPerson));
    }

    @Test
    public void testUpdatePersonWithIncorrectInput() throws Exception {
        Person personFromAPI = resources.client().resource("/persons/123").type(MediaType.APPLICATION_JSON_TYPE).put(Person.class, correctDummyPerson);
        assert (personFromAPI.equals(correctDummyPerson));
    }

    @Test
    public void testUpdateNonExistingPerson() throws Exception {
        Person personFromAPI = resources.client().resource("/persons/123").type(MediaType.APPLICATION_JSON_TYPE).put(Person.class, correctDummyPerson);
        assert (personFromAPI.equals(correctDummyPerson));
    }

    @Test
    public void testDeleteNonExistingPerson() throws Exception {
        Boolean booleanFromAPI = resources.client().resource("/persons/123").delete(Boolean.class);
        assert (booleanFromAPI);
    }

    @Test
    public void testAuthPersonWithWrongPassword() throws Exception {
        Client client = resources.client();
        client.addFilter(new HTTPBasicAuthFilter("user", "wrong-secret"));
        try {
            client.resource("/persons/getAuthPerson/123").get(Person.class);
        } catch (UniformInterfaceException e) {
            assert (e.getResponse().getStatus() == 401);
        }
    }

    @Test
    public void testAuthPersonWithNoAuthHeader() throws Exception {
        Client client = resources.client();
        try {
            client.resource("/persons/getAuthPerson/123").get(Person.class);
        } catch (UniformInterfaceException e) {
            assert (e.getResponse().getStatus() == 401);
        }
    }
}
