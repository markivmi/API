package com.rms.pilotapi.dao;

import com.google.inject.Inject;
import com.mongodb.DB;
import com.rms.pilotapi.core.Person;
import org.mongojack.JacksonDBCollection;

import java.util.concurrent.atomic.AtomicLong;

public class PersonDaoMongoImpl implements PersonDao {

    JacksonDBCollection<Person, String> personCollection;
    private final AtomicLong counter;

    @Inject
    public PersonDaoMongoImpl(DB mongoDb) {
        personCollection = JacksonDBCollection.wrap(
                mongoDb.getCollection("personCollection"),
                Person.class,
                String.class);
        this.counter = new AtomicLong();
    }

    @Override
    public Person getPerson(Integer id) {
        return personCollection.findOneById(id.toString());
    }

    @Override
    public Person createPerson(Person person) {
        personCollection.insert(person);
        return person;
    }

    @Override
    public Person updatePerson(Integer id, Person person) {
        personCollection.updateById(id.toString(), person);
        return person;
    }

    @Override
    public boolean deletePerson(Integer id) {
        personCollection.removeById(id.toString());
        return true;
    }
}
