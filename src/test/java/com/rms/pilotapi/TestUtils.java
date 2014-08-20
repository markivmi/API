package com.rms.pilotapi;
import com.rms.pilotapi.core.Address;
import com.rms.pilotapi.core.Coordinates;
import com.rms.pilotapi.core.Person;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.ws.rs.core.Response;
import java.util.Random;

public class TestUtils {

    public static final int INTERNAL_SERVER_ERROR = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
    public static final int OK                    = Response.Status.OK.getStatusCode();
    public static final int CREATED               = Response.Status.CREATED.getStatusCode();
    public static final int NOT_FOUND             = Response.Status.NOT_FOUND.getStatusCode();
    public static final int ACCEPTED              = Response.Status.ACCEPTED.getStatusCode();
    public static final int NO_CONTENT            = Response.Status.NO_CONTENT.getStatusCode();
    public static final int NOT_MODIFIED          = Response.Status.NOT_MODIFIED.getStatusCode();
    public static final int UNAUTHORIZED          = Response.Status.UNAUTHORIZED.getStatusCode();

    public enum WRONG {
        AGE,
        ZIP,
        CITY,
        STREET,
        LATITUDE,
        LONGITUDE,
        NAME,
        BIRTHDAY,
        ID
    }

    private static Random randomGenerator = new Random();

    private static int getWrongAge() {
        return 15;
    }

    private static String getWrongName() {
        return "";
    }

    private static int getWrongZip() {
        boolean positive = randomGenerator.nextBoolean();
        if(positive) {
            return 99999 + randomGenerator.nextInt();
        } else {
            return -1 * randomGenerator.nextInt();
        }
    }

    private static String getWrongCity() {
        return "";
    }

    private static String getWrongStreet() {
        return "";
    }

    private static DateTime getWrongBirthday() {
        return new DateTime().plusDays(100);
    }

    private static Coordinates getRightCoordinates() {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude("23N");
        coordinates.setLongitude("46S");

        return coordinates;
    }

    private static Coordinates getWrongCoordinates(WRONG wrongCoordinate) {

        Coordinates coordinates = getRightCoordinates();

        boolean longitude = randomGenerator.nextBoolean();
        boolean empty = randomGenerator.nextBoolean();
        String coordinate;
        if(empty) {
            coordinate = "";
        } else {
            coordinate = "123435S";
        }

        switch (wrongCoordinate) {
            case LATITUDE:
                coordinates.setLongitude(coordinate);
                break;
            case LONGITUDE:
                coordinates.setLongitude(coordinate);
                break;
        }

        return coordinates;
    }

    private static Address getRightAddress() {
        Coordinates coordinates = getRightCoordinates();

        Address address = new Address();
        address.setCity("Fremont");
        address.setStreet("Mowry Blvd");
        address.setZip(94538);
        address.setCoordinates(coordinates);

        return address;
    }

    private static Address getWrongAddress(WRONG wrongAddressType) {

        Address address = getRightAddress();

        switch (wrongAddressType) {
            case ZIP:
                address.setZip(getWrongZip());
                break;
            case CITY:
                address.setCity(getWrongCity());
                break;
            case STREET:
                address.setStreet(getWrongStreet());
                break;
            case LONGITUDE:
            case LATITUDE:
                address.setCoordinates(getWrongCoordinates(wrongAddressType));
                break;
        }

        return address;
    }

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

    public static Person getWrongDummyPerson(WRONG wrongValue) {

        Person person = getRightDummyPerson(123);

        switch (wrongValue) {
            case AGE:
                person.setAge(getWrongAge());
                break;
            case ZIP:
            case CITY:
            case STREET:
            case LATITUDE:
            case LONGITUDE:
                person.setAddress(getWrongAddress(wrongValue));
                break;
            case NAME:
                person.setName(getWrongName());
                break;
            case BIRTHDAY:
                person.setBirthDateTime(getWrongBirthday());
                break;
            case ID:
                person.setId(-1);
                break;
        }

        return person;
    }
}
