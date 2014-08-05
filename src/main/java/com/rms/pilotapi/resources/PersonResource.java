package com.rms.pilotapi.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
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
    public Person getPerson(@QueryParam("name") Optional<String> name) {
        return personDao.getPerson(name);
    }

    @POST
    @Timed
    public Person createPerson(@Valid Person person) {
        return personDao.createPerson(person);
    }

    @PUT
    @Timed
    public Person updatePerson(@Valid Person person) {
        return personDao.updatePerson(person);
    }

    @DELETE
    @Timed
    public boolean deletePerson(@Valid Person person) {
        return personDao.deletePerson(person);
    }
}