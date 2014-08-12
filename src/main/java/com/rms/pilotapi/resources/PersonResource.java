package com.rms.pilotapi.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.rms.pilotapi.core.Person;
import com.rms.pilotapi.dao.PersonDao;
import io.dropwizard.auth.Auth;

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
    @Path("/getAuthPerson/{id}")
    public Person getAuthPerson(@PathParam("id")int id, @Auth Person user) {

        /* use HTTP header:
        Authorization : Basic dGVzdDpzZWNyZXQ=

        For negative test case, use
        Authorization : Basic dGVzdDoxMjM=
         */

        if(user.getName().isEmpty()) {
            return null;
        }

        return personDao.getPerson(id);
    }

    @GET
    @Timed
    @Path("/{id}")
    public Person getPerson(@PathParam("id") Integer id) {
        return personDao.getPerson(id);
    }

    @POST
    @Timed
    public Person createPerson(@Valid Person person) {
        return personDao.createPerson(person);
    }

    @PUT
    @Timed
    @Path("/{id}")
    public Person updatePerson(@PathParam("id") Integer id, @Valid Person person) {
        return personDao.updatePerson(id, person);
    }

    @DELETE
    @Timed
    @Path("/{id}")
    public boolean deletePerson(@PathParam("id") Integer id) {
        return personDao.deletePerson(id);
    }
}