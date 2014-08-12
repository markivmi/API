package com.rms.pilotapi.test;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.rms.pilotapi.core.Person;
import com.rms.pilotapi.dao.PersonDaoMongoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

public class PersonDaoMongoImplTest {

    public PersonDaoMongoImpl personDao;
    MongoClient mongoClient;
    DB mongoDb;

    public PersonDaoMongoImplTest() throws UnknownHostException {
        mongoClient = new MongoClient("localhost", 27017);
        mongoDb = mongoClient.getDB("local");
    }

    @Before
    public void SetupDao() {
        personDao = new PersonDaoMongoImpl(mongoDb);
    }

    @After
    public void AfterTestRun() {
        personDao = null;
    }

    @Test
    public void CreatePersonTest() {
        Person person = TestUtils.getDummyPerson(0);
        Person p = personDao.createPerson(person);
        assert (p.getId() > 0);
    }

    @Test
    public void UpdatePersonTest() {
        Person person = personDao.createPerson(TestUtils.getDummyPerson(0));
        long id = person.getId();
        person.setName("John Doe updated");
        Person p = personDao.updatePerson(id, person);
        assert (p.equals(person));
    }

    @Test
    public void GetPersonTest() {
        Person person = personDao.createPerson(TestUtils.getDummyPerson(0));
        long id = person.getId();
        Person p = personDao.getPerson(id);
        assert (p != null);
    }

    @Test
    public void DeletePersonTest() {
        Person person = personDao.createPerson(TestUtils.getDummyPerson(0));
        long id = person.getId();
        assert (personDao.deletePerson(id));
    }

}
