package com.rms.pilotapi.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.rms.pilotapi.core.Person;
import com.rms.pilotapi.dao.PersonDao;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {
    private final PersonDao personDao;

    @Inject
    public PersonResource(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GET
    @Timed
    public Person getPerson(@QueryParam("id") int id) {
        return personDao.getPerson(id);
    }

    @POST
    @Timed
    public Person createPerson(@Valid Person person) {
        return personDao.createPerson(person);
    }

    @PUT
    @Timed
    public Person updatePerson(@QueryParam("id") int id, @Valid Person person) {
        return personDao.updatePerson(id, person);
    }

    @DELETE
    @Timed
    public boolean deletePerson(@QueryParam("id") int id) {
        return personDao.deletePerson(id);
    }
}