package com.rms.pilotapi.dao;

import com.rms.common.Functor;
import com.rms.pilotapi.TestUtils;
import com.rms.pilotapi.core.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class PersonDaoMongoImplTest {

    private static Functor<String, JacksonDBCollection<Person, Long>> getCollection;
    private static long count = 1;


    private static JacksonDBCollection<Person, Long> mockCollection = mock(JacksonDBCollection.class);
    private static WriteResult mockWriteResult = mock(WriteResult.class);
    private static Person outputPerson = TestUtils.getRightDummyPerson(123);

    public PersonDaoMongoImpl personDao;

    @BeforeClass
    public static void setupDB() throws IOException {
        getCollection = (String collectionName) -> mockCollection;
    }

    @Before
    public void SetupDao() {
        doReturn(null).when(mockWriteResult).getError();
        doReturn(count).when(mockWriteResult).getSavedId();

        if (mockCollection == null || mockWriteResult == null) {
            throw new NullPointerException();
        }

        when(mockCollection.getCount()).thenReturn(count);
        when(mockCollection.findOneById(anyLong())).thenReturn(outputPerson);
        when(mockCollection.insert(any(Person.class))).thenReturn(mockWriteResult);
        when(mockCollection.updateById(anyLong(), any(Person.class))).thenReturn(mockWriteResult);
        when(mockCollection.removeById(anyLong())).thenReturn(mockWriteResult);

        personDao = new PersonDaoMongoImpl(getCollection);
    }

    @After
    public void AfterTestRun() {
        ++count;
    }

    @Test
    public void CreatePersonTest() {
        Person person = TestUtils.getRightDummyPerson(0);
        Person p = personDao.createPerson(person);
        assert (p.getId() > 0);
    }

    @Test
    public void UpdatePersonTest() {
        Person person = personDao.createPerson(TestUtils.getRightDummyPerson(0));
        long id = person.getId();
        person.setName("John Doe updated");
        Person p = personDao.updatePerson(id, person);
        assert (p.equals(person));
    }

    @Test
    public void GetPersonTest() {
        Person person = personDao.createPerson(TestUtils.getRightDummyPerson(0));
        long id = person.getId();
        Person p = personDao.getPerson(id);
        assert (p != null);
    }

    @Test
    public void DeletePersonTest() {
        Person person = personDao.createPerson(TestUtils.getRightDummyPerson(0));
        long id = person.getId();
        assert (personDao.deletePerson(id));
    }
}
