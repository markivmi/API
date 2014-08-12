package com.rms.pilotapi.test;

import com.rms.pilotapi.core.Person;
import com.rms.pilotapi.dao.PersonDao;
import com.rms.pilotapi.resources.PersonResource;
import io.dropwizard.testing.junit.ResourceTestRule;

import javax.ws.rs.core.MediaType;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PersonApplicationTest {


    private static final PersonDao personDao = mock(PersonDao.class);
    private final Person person = TestUtils.getDummyPerson(123);

 //   @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PersonResource(personDao))
            .build();

    /*@Before*/
    public void setup() {
       /* when(personDao.getPerson(anyInt())).thenReturn(person);
        when(personDao.createPerson(any(Person.class))).thenReturn(person);
        when(personDao.updatePerson(anyInt(), any(Person.class))).thenReturn(person);
        when(personDao.deletePerson(anyInt())).thenReturn(true);*/
    }

 //   @Test
    public void testGetPerson() {
        Person personFromAPI = resources.client().resource("/persons/123").get(Person.class);
        assertThat(personFromAPI.equals(person));
        verify(personDao).getPerson(123);
    }

 //   @Test
    public void testCreatePerson() throws Exception {
        Person personFromAPI = resources.client().resource("/persons").type(MediaType.APPLICATION_JSON_TYPE).post(Person.class, person);
        assertThat(personFromAPI.equals(person));
        verify(personDao).createPerson(person);
    }

 //   @Test
    public void testUpdatePerson() throws Exception {
        Person personFromAPI = resources.client().resource("/persons/123").type(MediaType.APPLICATION_JSON_TYPE).put(Person.class, person);
        assertThat(personFromAPI.equals(person));
        verify(personDao).updatePerson(123, person);
    }

  //  @Test
    public void testDeletePerson() throws Exception {
        Boolean booleanFromAPI = resources.client().resource("/persons/123").delete(Boolean.class);
        assertThat(booleanFromAPI);
        verify(personDao).deletePerson(123);
    }
}
