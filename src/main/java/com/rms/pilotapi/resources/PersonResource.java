package com.rms.pilotapi.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.rms.pilotapi.core.Person;
import com.rms.pilotapi.dao.PersonDao;
import io.dropwizard.auth.Auth;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//TODO: put response codes in response filters

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
    public Person getAuthPerson(@PathParam("id") int id, @Auth Boolean isAuthenticated) throws WebApplicationException {

        /* use HTTP header:
        Authorization : Basic dGVzdDpzZWNyZXQ=

        For negative test case, use
        Authorization : Basic dGVzdDoxMjM=
         */

        if (!isAuthenticated) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
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
        Person output;

        try {
            output =  personDao.createPerson(person);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        if (output == null) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        return output;
    }

    @PUT
    @Timed
    @Path("/{id}")
    public Person updatePerson(@PathParam("id") Integer id, @Valid Person person) {
        Person output;
        try {
            output = personDao.updatePerson(id, person);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        if (output == null) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        return output;
    }

    @DELETE
    @Timed
    @Path("/{id}")
    public boolean deletePerson(@PathParam("id") Integer id) {
        boolean output;
        try {
            output = personDao.deletePerson(id);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        if (!output) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return true;
    }
}