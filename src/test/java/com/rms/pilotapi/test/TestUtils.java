package com.rms.pilotapi.test;
import com.rms.pilotapi.core.Address;
import com.rms.pilotapi.core.Coordinates;
import com.rms.pilotapi.core.Person;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class TestUtils {

    public static Person getRightDummyPerson(long id) {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude("23N");
        coordinates.setLongitude("46S");

        Address address = new Address();
        address.setCity("Fremont");
        address.setStreet("Mowry Blvd");
        address.setZip(94538);
        address.setCoordinates(coordinates);

        Person person = new Person();
        person.setName("John Doe");
        person.setBirthDateTime(new DateTime("2012-11-21T13:01:33.568Z", DateTimeZone.UTC));
        person.setAge(20);
        person.setAddress(address);
        person.setId(id);
        return person;
    }

    public static Person getWrongDummyPersonHavingWrongAge(long id) {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude("23N");
        coordinates.setLongitude("46S");

        Address address = new Address();
        address.setCity("Fremont");
        address.setStreet("Mowry Blvd");
        address.setZip(94538);
        address.setCoordinates(coordinates);

        Person person = new Person();
        person.setName("John Doe");
        person.setBirthDateTime(new DateTime("2012-11-21T13:01:33.568Z", DateTimeZone.UTC));
        person.setAge(20);
        person.setAddress(address);
        person.setId(id);
        return person;
    }
}
