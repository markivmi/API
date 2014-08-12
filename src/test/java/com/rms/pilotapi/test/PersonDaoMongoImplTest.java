package com.rms.pilotapi.test;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.rms.pilotapi.core.Address;
import com.rms.pilotapi.core.Coordinates;
import com.rms.pilotapi.core.Person;
import com.rms.pilotapi.dao.PersonDaoMongoImpl;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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

    private Person getDummyCreatePerson() {
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
        return person;
    }

    @Test
    public void CreatePersonTest() {
        Person person = getDummyCreatePerson();
        Person p = personDao.createPerson(person);
        assert (p.getId() > 0);
    }

    @Test
    public void UpdatePersonTest() {
        Person person = personDao.createPerson(getDummyCreatePerson());
        long id = person.getId();
        person.setName("John Doe updated");
        Person p = personDao.updatePerson(id, person);
        assert (p.equals(person));
    }


    @Test
    public void GetPersonTest() {
        Person person = personDao.createPerson(getDummyCreatePerson());
        long id = person.getId();
        Person p = personDao.getPerson(id);
        assert (p != null);
    }


    @Test
    public void DeletePersonTest() {
        /*Person person = personDao.createPerson(getDummyCreatePerson());
        int id = person.getId();
        assert (personDao.deletePerson(id) == true);*/
    }

}
