package com.rms.pilotapi.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.rms.pilotapi.core.Address;
import com.rms.pilotapi.core.Coordinates;
import com.rms.pilotapi.core.Person;
import org.joda.time.DateTime;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
    public PersonResource() {
    }

    @GET
    @Timed
    public Person getPerson(@QueryParam("name") Optional<String> name) {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude("23° 26′ 21″ S");
        coordinates.setLongitude("46° 52′ 42″ N");

        Address address = new Address();
        address.setCity("Fremont");
        address.setStreet("Mowry Blvd");
        address.setZip("94538");
        address.setCoordinates(coordinates);

        Person person = new Person();
        person.setName("John Doe");
        person.setBirthDateTime(DateTime.now());
        person.setAge(10);
        person.setAddress(address);

        return person;
    }

    @POST
    @Timed
    public Person createPerson(@Valid Person person) {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude("23° 26′ 21″ S");
        coordinates.setLongitude("46° 52′ 42″ N");

        Address address = new Address();
        address.setCity("Fremont");
        address.setStreet("Mowry Blvd");
        address.setZip("94538");
        address.setCoordinates(coordinates);

        Person person1 = new Person();
        person1.setName("John Doe");
        person1.setBirthDateTime(DateTime.now());
        person1.setAge(10);
        person1.setAddress(address);

        return person1;
    }

    @PUT
    @Timed
    public Person updatePerson(@Valid Person person) {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude("23° 26′ 21″ S");
        coordinates.setLongitude("46° 52′ 42″ N");

        Address address = new Address();
        address.setCity("Fremont");
        address.setStreet("Mowry Blvd");
        address.setZip("94538");
        address.setCoordinates(coordinates);

        Person person1 = new Person();
        person1.setName("John Doe");
        person1.setBirthDateTime(DateTime.now());
        person1.setAge(10);
        person1.setAddress(address);

        return person1;
    }

    @DELETE
    @Timed
    public boolean deletePerson(@Valid Person person) {
        return true;
    }
}
