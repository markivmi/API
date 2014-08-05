package com.rms.pilotapi.dao;

import com.google.common.base.Optional;
import com.rms.pilotapi.core.Address;
import com.rms.pilotapi.core.Coordinates;
import com.rms.pilotapi.core.Person;
import org.joda.time.DateTime;

public class PersonDaoMongoImpl implements PersonDao {
    @Override
    public Person getPerson(Optional<String> name) {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude("23N");
        coordinates.setLongitude("46S");

        Address address = new Address();
        address.setCity("Fremont");
        address.setStreet("Mowry Blvd");
        address.setZip("94538");
        address.setCoordinates(coordinates);

        Person person = new Person("test");
        person.setName("John Doe");
        person.setBirthDateTime(new DateTime("2012-11-21T13:01:33.568Z"));
        person.setAge(10);
        person.setAddress(address);

        return person;
    }

    @Override
    public Person createPerson(Person person) {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude("23N");
        coordinates.setLongitude("46S");

        Address address = new Address();
        address.setCity("Fremont");
        address.setStreet("Mowry Blvd");
        address.setZip("94538");
        address.setCoordinates(coordinates);

        Person person1 = new Person("test");
        person1.setName("John Doe");
        person.setBirthDateTime(new DateTime("2012-11-21T13:01:33.568Z"));
        person1.setAge(10);
        person1.setAddress(address);

        return person1;
    }

    @Override
    public Person updatePerson(Person person) {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude("23N");
        coordinates.setLongitude("46S");

        Address address = new Address();
        address.setCity("Fremont");
        address.setStreet("Mowry Blvd");
        address.setZip("94538");
        address.setCoordinates(coordinates);

        Person person1 = new Person("test");
        person1.setName("John Doe");
        person.setBirthDateTime(new DateTime("2012-11-21T13:01:33.568Z"));
        person1.setAge(10);
        person1.setAddress(address);

        return person1;
    }

    @Override
    public boolean deletePerson(Person person) {
        return true;
    }
}
