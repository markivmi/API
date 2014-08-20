package com.rms.pilotapi.positiveTest;

public class PersonApplicationTest {
/*

    private static final PersonDao personDao = mock(PersonDao.class);
    private final Person inputPerson = TestUtils.getRightDummyPerson(123);

    //   @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PersonResource(personDao))
            .build();

    *//*@Before*//*
    public void setup() {
       *//* when(personDao.getPerson(anyInt())).thenReturn(inputPerson);
        when(personDao.createPerson(any(Person.class))).thenReturn(inputPerson);
        when(personDao.updatePerson(anyInt(), any(Person.class))).thenReturn(inputPerson);
        when(personDao.deletePerson(anyInt())).thenReturn(true);*//*
    }

    //   @Test
    public void testGetPerson() {
        Person personFromAPI = resources.client().resource("/persons/123").get(Person.class);
        assertThat(personFromAPI.equals(inputPerson));
        verify(personDao).getPerson(123);
    }

    //   @Test
    public void testCreatePerson() throws Exception {
        Person personFromAPI = resources.client().resource("/persons").type(MediaType.APPLICATION_JSON_TYPE).post(Person.class, inputPerson);
        assertThat(personFromAPI.equals(inputPerson));
        verify(personDao).createPerson(inputPerson);
    }

    //   @Test
    public void testUpdatePerson() throws Exception {
        Person personFromAPI = resources.client().resource("/persons/123").type(MediaType.APPLICATION_JSON_TYPE).put(Person.class, inputPerson);
        assertThat(personFromAPI.equals(inputPerson));
        verify(personDao).updatePerson(123, inputPerson);
    }

    //  @Test
    public void testDeletePerson() throws Exception {
        Boolean booleanFromAPI = resources.client().resource("/persons/123").delete(Boolean.class);
        assertThat(booleanFromAPI);
        verify(personDao).deletePerson(123);
    }*/
}
